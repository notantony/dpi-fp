package automaton.nfa;

import automaton.transition.Transition;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

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

    boolean isTerminal() {
        return terminal != -1;
    }

    List<Pair<Transition, State>> getEdges() {
        return edges;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }
}
