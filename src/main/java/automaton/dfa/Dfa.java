package automaton.dfa;

import automaton.nfa.Nfa;
import util.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Dfa {
    private final Node start;

    public Dfa(Node start) {
        this.start = start;
    }

    public Collection<Integer> test(String string) {
        Set<Integer> result = new HashSet<>();
        Node cur = start;
        for (int i = 0; i < string.length(); i++) {
            result.addAll(cur.getTerminal());
            char c = string.charAt(i);
            Map<Character, Node> edges = cur.getEdges();
            if (edges.containsKey(c)) {
                cur = edges.get(c);
            } else {
                return result;
            }
        }
        result.addAll(cur.getTerminal());
        return result;
    }

    public boolean testAnyImpl(String string) {
        return !test(string).isEmpty();
    }

    public boolean testAny(String string) {
        return Utils.testHeader(this::testAnyImpl, string);
    }

    public Collection<Node> allNodes() {
        return runDfs(start);
    }

    public Collection<Node> runDfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        HashSet<Node> visited = new HashSet<>(queue);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.getEdges().values().stream()
                    .filter(target -> !visited.contains(target))
                    .forEach(target -> {
                        visited.add(target);
                        queue.add(target);
                    });
        }
        return visited;
    }

    public Integer nodesCount() {
        return allNodes().size();
    }

    public Node getStart() {
        return start;
    }

    public long cutCount() {
        Collection<Node> nodes = allNodes();
        return nodes.stream()
                .map(this::runDfs)
                .map(path -> {
                    Set<Integer> visited = new HashSet<>();
                    path.forEach(node -> visited.addAll(node.getTerminal()));
                    return visited.size() > 1;
                }).filter(a -> a).count();
//                .map(path -> path.distinct().count() > 1).count();
//        return nodes.stream()
//                .map(this::runDfs)
//                .map(path -> path.stream()
//                        .flatMap(node -> node.getTerminal().stream()))
//                .map(path -> path.distinct().count() > 1).filter(a -> a).count();
    }
}