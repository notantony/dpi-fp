package automaton.dfa;

import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.SingleElementTransition;
import automaton.transition.Transitions;
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

    public static Dfa parseDfa(String s) {
        String[] lines = s.split("\n");
        String[][] dataLines = new String[lines.length][0];
        for (int i = 0; i < lines.length; i++) {
            dataLines[i] = lines[i].split(" ");
        }
        HashMap<Integer, List<Pair<Character, Integer>>> mp = new HashMap<>();

        int i = 0;
        Map<Integer, Integer> terminalIds = new HashMap<>();
        while (dataLines[i].length == 2) {
            terminalIds.put(Integer.parseInt(dataLines[i][0]), Integer.parseInt(dataLines[i][1]));
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
        ArrayList<Node> nodes = new ArrayList<>();
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
            nodes.add(new Node());
        }
        mp.forEach((a, b) -> {
            b.forEach(edge -> {
                nodes.get(a).addEdge(edge.getFirst(), nodes.get(edge.getSecond()));
            });
        });

        terminalIds.forEach((id, terminal) -> {
            nodes.get(id).setTerminal(Collections.singletonList(terminal));
        });


        return new Dfa(nodes.get(0));
    }
}