package automaton.dfa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private Collection<Integer> terminal;
    private final Map<Character, Node> edges;

    public Node() {
        edges = new HashMap<>();
    }

    public Collection<Integer> getTerminal() {
        return terminal;
    }

    public boolean isTerminal() {
        return terminal.size() > 0;
    }

    public void setTerminal(Collection<Integer> terminal) {
        this.terminal = terminal;
    }

    public Map<Character, Node> getEdges() {
        return edges;
    }

    public void addEdge(Character c, Node node) {
        edges.put(c, node);
    }
}
