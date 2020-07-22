package automaton.dfa;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private List<Integer> terminal;
    private final Map<Character, Node> edges;

    public Node() {
        edges = new HashMap<>();
    }

    public List<Integer> getTerminal() {
        return terminal;
    }

    public boolean isTerminal() {
        return terminal.size() > 0;
    }

    public void setTerminal(List<Integer> terminal) {
        this.terminal = terminal;
    }

    public Map<Character, Node> getEdges() {
        return edges;
    }

    public void addEdge(Character c, Node node) {
        edges.put(c, node);
    }
}
