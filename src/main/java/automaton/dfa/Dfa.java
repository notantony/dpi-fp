package automaton.dfa;

import automaton.nfa.Nfa;
import util.Pair;
import util.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Dfa {
    private Node start;

    public Dfa(Node start) {
        this.start = start;
    }

    public Collection<Integer> testImpl(String string) {
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

    public boolean testAny(String string) {
        return !test(string).isEmpty();
    }

    public Collection<Integer> test(String string) {
        return Utils.testHeader(this::testImpl, string);
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

    public void print() {
        Collection<Node> nodes = allNodes();
        Map<Node, Integer> map = new HashMap<>();
        int counter = 0;
        for (Node node : nodes) {
            map.put(node, counter++);
        }
        Map<Pair<Integer, Integer>, List<Character>> edges = new HashMap<>();
        nodes.forEach(node -> {
            node.getEdges().forEach((c, target) -> {
                Pair<Integer, Integer> pair = new Pair<>(map.get(node), map.get(target));
                edges.putIfAbsent(pair, new ArrayList<>());
                edges.get(pair).add(c == 256 ? '$' : (c == 257 ? '^' : c));
            });
            if (node.isTerminal()) {
                System.out.println(map.get(node));
            }
//            if (node.isTerminal()) {
//                System.out.print("T ");
//            }
//            System.out.println(node.hashCode() + " " + map.get(node));
        });
        edges.forEach((pair, chars) -> {
            String edgeName;
            if (chars.size() > 95) {
                edgeName = "r" + chars.size();
            } else {
                StringBuilder sb = new StringBuilder();
                chars.forEach(sb::append);
                edgeName = sb.toString();
            }
            System.out.println(pair.getFirst() + " " + pair.getSecond() + " " + edgeName);
        });
    }

    public void setStart(Node start) {
        this.start = start;
    }
}