package automaton.dfa;

import automaton.nfa.Nfa;
import util.Utils;

import java.util.*;

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

    public Integer nodesCount() {
        Queue<Node> queue = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.getEdges().values().stream()
                    .filter(target -> !visited.contains(target))
                    .forEach(target -> {
                        visited.add(target);
                        queue.add(target);
                    });
        }
        return visited.size();
    }
}
