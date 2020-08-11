package automaton.nfa;

import automaton.transition.*;
import util.FutureList;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Nfa {
    private State start;
    private List<State> terminals;

    public Nfa(boolean isTerminal) {
        start = new State();
        terminals = new ArrayList<>(4);
        if (isTerminal) {
            terminals.add(start);
        }
    }

    public Nfa(State start, List<State> terminals) {
        this.start = start;
        this.terminals = terminals;
    }

    public Nfa(Transition t) {
        terminals = new ArrayList<>(4);
        start = new State();
        State fin = new State();
        start.addEdge(t, fin);
        terminals.add(fin);
    }

    public void append(Nfa other) {
        if (this == other) {
            throw new IllegalArgumentException("Cannot connect nfa with itself");
        }
        for (State terminal : terminals) {
            terminal.addEdge(new EpsilonTransition(), other.start);
        }
        terminals = other.terminals;
    }

    public static Nfa concat(Collection<Nfa> items) {
        Nfa result = new Nfa(true);
        for (Nfa nfa : items) {
            result.append(nfa);
        }
        return result;
    }

    public static Nfa union(Collection<Nfa> other) {
        State newStart = new State();
        State newTerminal = new State();
        other.forEach(nfa -> newStart.addEdge(EpsilonTransition.epsilonTransition, nfa.start));
        other.forEach(nfa ->
                nfa.terminals.forEach(terminal ->
                        terminal.addEdge(EpsilonTransition.epsilonTransition, newTerminal)
                )
        );
        return new Nfa(newStart, Collections.singletonList(newTerminal));
    }

    public void closure() {
        State newStart = new State();
        State newTerminal = new State();
        newStart.addEdge(new EpsilonTransition(), start);
        start.addEdge(new EpsilonTransition(), newTerminal);
        terminals.forEach(terminal -> terminal.addEdge(EpsilonTransition.epsilonTransition, newTerminal));
        newTerminal.addEdge(new EpsilonTransition(), newStart);
        start = newStart;
        terminals = Collections.singletonList(newTerminal);
    }

    public Nfa copy() {
        Map<State, State> bijection = new HashMap<>();
        Queue<State> queue = new ArrayDeque<>();
        queue.add(start);
        State newStart = new State();
        bijection.put(start, newStart);
        while (queue.size() > 0) {
            State cur = queue.poll();
            State newCur = bijection.get(cur);
            for (Pair<Transition, State> edge : cur.getEdges()) {
                State target = edge.getSecond();
                State newTarget;
                if (bijection.containsKey(target)) {
                    newTarget = bijection.get(target);
                } else {
                    newTarget = new State();
                    bijection.put(target, newTarget);
                    queue.add(target);
                }
                newCur.addEdge(edge.getFirst(), newTarget);
            }
        }
        List<State> newTerminals = terminals.stream().map(bijection::get).collect(Collectors.toList());
        return new Nfa(newStart, newTerminals);
    }

    public void makeOptional() {
        terminals.forEach(terminal -> start.addEdge(EpsilonTransition.epsilonTransition, terminal));
    }

    public Nfa buildRepeated(int times) {
        Nfa cur = new Nfa(true);
        for (int i = 0; i < times; i++) {
            Nfa next = this.copy();
            cur.append(next);
        }

        return cur;
    }

    public State getStart() {
        return start;
    }

    public List<State> getTerminals() {
        return terminals;
    }

    public boolean test(String s) {
        return testHeader(s, true);
    }

    public boolean testHeader(String s, boolean addHeader) {
        if (addHeader) {
            s = (char) 257 + s + (char) 256;
        }
        // TODO: better solution
        boolean success = false;

        Set<State> states = new HashSet<>();
        states.add(start);

        Queue<State> checkEpsilons = new ArrayDeque<>(states);
        State.traverseEpsilons(checkEpsilons, states);
        success |= states.stream().anyMatch(State::isTerminal);

        for (int i = 0; !success && i < s.length(); i++) {
            HashSet<State> newStates = new HashSet<>();

            for (State state : states) {
                for (Pair<Transition, State> edge : state.getEdges()) {
                    Transition transition = edge.getFirst();
                    State target = edge.getSecond();
                    if (!(transition instanceof EpsilonTransition) &&
                            !newStates.contains(target) &&
                            transition.test(s.charAt(i))) {
                        newStates.add(target);
                    }
                }
            }

            checkEpsilons = new ArrayDeque<>(newStates);
            State.traverseEpsilons(checkEpsilons, newStates);

            states = newStates;
            success |= states.stream().anyMatch(State::isTerminal);
        }
//        return terminals.stream().anyMatch(states::contains);
        if (!success && s.length() > 0) {
            return testHeader(s.substring(1), false);
        }
        return success;
    }

    public void close(int id) {
        terminals.forEach(terminal -> terminal.setTerminal(id));
    }

    public void close() {
        close(1);
    }

    public void setupTail() {
        State fin = new State();
        fin.addEdge(new RangeTransition((char) 0, Transitions.MAX_CHAR), fin);
        fin.setTerminal(0);
        Nfa finNfa = new Nfa(fin, FutureList.of(fin));
        append(finNfa);
    }

    public static Nfa parseNfa(String s) {
        String[] lines = s.split("\n");
        String[][] dataLines = new String[lines.length][0];
        for (int i = 0; i < lines.length; i++) {
            dataLines[i] = lines[i].split(" ");
        }
        HashMap<Integer, List<Pair<Character, Integer>>> mp = new HashMap<>();

        int i = 0;
        List<Integer> terminalIds = new ArrayList<>();
        while (dataLines[i].length == 1) {
            terminalIds.add(Integer.parseInt(dataLines[i][0]));
            i++;
        }
        for (; i < dataLines.length; i++) {
            String line = lines[i];
            String[] dataLine = line.split(" ");
            int a = Integer.parseInt(dataLine[0]);
            int b = Integer.parseInt(dataLine[1]);
            mp.putIfAbsent(a, new ArrayList<>());
            for (char c : dataLine[2].toCharArray()) {
                assert c < Transitions.MAX_CHAR : "" + c;
                mp.get(a).add(new Pair<>(c, b));
            }
        }
        ArrayList<State> states = new ArrayList<>();
        int size = Integer.max(
                mp.keySet().stream()
                        .reduce(Integer::max)
                        .orElse(0),
                mp.values().stream()
                        .flatMap(Collection::stream)
                        .map(Pair::getSecond)
                        .reduce(Integer::max)
                        .orElse(0)) + 1;
        for (int j = 0; j < size; j++) {
            states.add(new State());
        }
        mp.forEach((a, b) -> {
            b.forEach(edge -> {
                states.get(a).addEdge(new SingleElementTransition(edge.getFirst()), states.get(edge.getSecond()));
            });
        });

        for (int id : terminalIds) {
            states.get(id).setTerminal(1);
        }

        return new Nfa(states.get(0), terminalIds.stream().map(states::get).collect(Collectors.toList()));
    }
}
