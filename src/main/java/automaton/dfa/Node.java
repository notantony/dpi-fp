package automaton.dfa;

import java.util.*;

public class Node {
    private List<Integer> terminal;
    private Map<Character, Node> edges;

    public Node() {
        edges = new HashMap<>();
        terminal = new ArrayList<>();
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

    public void setEdges(Map<Character, Node> edges) {
        this.edges = edges;
    }
}
