package automaton.nfa;

import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class State {
    private int terminal = -1;
    private final List<Pair<Transition, State>> edges = new ArrayList<>();

    public State() {}

    public State(int terminal) {
        this.terminal = terminal;
    }

    public void addEdge(Transition t, State n) {
        this.edges.add(new Pair<>(t, n));
    }

    public boolean isTerminal() {
        return terminal != -1;
    }

    public List<Pair<Transition, State>> getEdges() {
        return edges;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getTerminal() {
        return terminal;
    }

    public static void traverseEpsilons(Queue<State> checkEpsilons, Set<State> newStates) {
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

    public static Set<State> traverseEpsilonsSafe(Collection<State> checkEpsilons) {
        Queue<State> queue = new ArrayDeque<>(checkEpsilons);
        Set<State> result = new HashSet<>(queue);
        traverseEpsilons(queue, result);
        return result;
    }

    public Set<State> allChildren() {
        HashSet<State> nodes = new HashSet<>();
        Queue<State> queue = new ArrayDeque<>();
        nodes.add(this);
        queue.add(this);
        while (!queue.isEmpty()) {
            State cur = queue.remove();
            cur.getEdges().forEach(edge -> {
                State target = edge.getSecond();
                if (!nodes.contains(target)) {
                    nodes.add(target);
                    queue.add(target);
                }
            });
        }
        return nodes;
    }


    public void print() {
        Set<State> states = allChildren();
//        for (se)
    }
}
