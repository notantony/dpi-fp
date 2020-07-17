package automaton.nfa;

import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
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

    public void append(Nfa other) {
        if (this == other) {
            throw new IllegalArgumentException("Cannot connect dfa with itself");
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

    public void union(Nfa other) {
        if (this == other) {
            throw new IllegalArgumentException("Cannot connect dfa with itself");
        }
        State newStart = new State();
        State newTerminal = new State();
        newStart.addEdge(new EpsilonTransition(), start);
        newStart.addEdge(new EpsilonTransition(), other.start);
        for (State terminal : terminals) {
            terminal.addEdge(new EpsilonTransition(), newTerminal);
        }
        for (State terminal : other.getTerminals()) {
            terminal.addEdge(new EpsilonTransition(), newTerminal);
        }
        start = newStart;
        terminals = Collections.singletonList(newTerminal);
    }

    public void closure() {
        State newStart = new State();
        State newTerminal = new State();
        newStart.addEdge(new EpsilonTransition(), start);
        start.addEdge(new EpsilonTransition(), newTerminal);
        newStart.addEdge(new EpsilonTransition(), newTerminal);
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


    private void traverseEpsilons(Queue<State> checkEpsilons, Set<State> newStates) {
        while (!checkEpsilons.isEmpty()) {
            State state = checkEpsilons.poll();
            for (Pair<Transition, State> edge : state.getEdges()) {
                Transition transition = edge.getFirst();
                State target = edge.getSecond();
                if (transition instanceof EpsilonTransition &&
                        !newStates.contains(target)) {
                    newStates.add(target);
                    checkEpsilons.add(target);
                }
            }
        }
    }

    public boolean test(String s) {
        Set<State> states = new HashSet<>();
        states.add(start);

        Queue<State> checkEpsilons = new ArrayDeque<>(states);
        traverseEpsilons(checkEpsilons, states);

        for (int i = 0; i < s.length(); i++) {
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
            traverseEpsilons(checkEpsilons, newStates);

            states = newStates;
        }
        return terminals.stream().anyMatch(states::contains);
    }
}
