package automaton.dfa;

import automaton.algo.AlgoException;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.SingleElementTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;
import util.Utils;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Dfa {
    public enum ParsingMode {
        LETTERS_LIST, SINGLE_EDGE, DESERIALIZE
    }

    public enum PrintingMode {
        VISUALISE, SERIALIZE
    }

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

//    public void print(PrintingMode mode) {
//        print();
//    }

    public String print() {
        return print(PrintingMode.VISUALISE);
    }

    public String print(PrintingMode mode) {
        Collection<Node> nodes = allNodes();
        Map<Node, Integer> map = new HashMap<>(); // TODO: convert map into list?
        int counter = 0;
        for (Node node : nodes) {
            map.put(node, counter++);
        }
        return printImpl(map, mode);
    }

    public String print(Map<Node, Integer> map) { // TODO: strings mapping?
        return printImpl(map, PrintingMode.VISUALISE);
    }

    private String printImpl(Map<Node, Integer> map, PrintingMode mode) {
        StringBuilder out = new StringBuilder();
        Collection<Node> nodes = allNodes();
        for (Node node : nodes) {
            if (node == start) {
//                System.out.println("s " + map.get(node));
                out.append("Graph:\n");
                out.append("s ").append(map.get(node)).append("\n");
            }
        }

        Map<Pair<Integer, Integer>, List<String>> edges = new HashMap<>();
        nodes.forEach(node -> {
            node.getEdges().forEach((c, target) -> {
                Pair<Integer, Integer> pair = new Pair<>(map.get(node), map.get(target));
                edges.putIfAbsent(pair, new ArrayList<>());
                if (mode == PrintingMode.VISUALISE) {
                    edges.get(pair).add("" + (c == 256 ? '$' : (c == 257 ? '^' : c)));
                } else {
                    edges.get(pair).add(Integer.toString(c));
                }
            });
            if (node.isTerminal()) {
                String termStr = node.getTerminal().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));
//                System.out.println(map.get(node) + " " + termStr);
                out.append(map.get(node)).append(" ").append(termStr).append("\n");
            }
//            if (node.isTerminal()) {
//                System.out.print("T ");
//            }
//            System.out.println(node.hashCode() + " " + map.get(node));
        });
        edges.forEach((pair, chars) -> {
            String edgeName;
            if (mode == PrintingMode.VISUALISE) {
                if (chars.size() > 95) {
                    edgeName = "r" + chars.size();
                } else {
                    StringBuilder sb = new StringBuilder();
                    chars.forEach(sb::append);
                    edgeName = sb.toString();
                }
//                System.out.println(pair.getFirst() + " " + pair.getSecond() + " " + edgeName);
                out.append(pair.getFirst()).append(" ").append(pair.getSecond()).append(" ").append(edgeName).append("\n");
            } else {
                String s = String.join(" ", chars);
//                System.out.println(pair.getFirst() + " " + pair.getSecond() + " " + s);
                out.append(pair.getFirst()).append(" ").append(pair.getSecond()).append(" ").append(s).append("\n");
            }
        });
        if (mode == PrintingMode.VISUALISE) {
            System.out.print(out.toString());
            return null;
        }
        return out.toString();
    }

    public void close() {
        allNodes().stream().filter(Node::isTerminal).forEach(terminal -> {
            if (!terminal.getEdges().isEmpty()) {
                if (terminal.getEdges().values().stream().anyMatch(target -> target != terminal) ||
                        terminal.getEdges().size() != Transitions.MAX_CHAR + 1) {
                    throw new AlgoException("Cannot close terminal with edges");
                }
            }
            if (terminal.getTerminal().size() > 1) {
                throw new AlgoException("Cannot close multi-terminal");
            }
            HashMap<Character, Node> mp = new HashMap<>();
            for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                mp.put(c, terminal);
            }
            terminal.setEdges(mp);
        });
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public static Dfa parseDfa(BufferedReader reader) {
        return parseDfa(reader, ParsingMode.LETTERS_LIST);
    }

    public static Dfa parseDfa(BufferedReader reader, ParsingMode mode) {
        return parseDfa(reader.lines().toArray(String[]::new), mode);
    }

    public static Dfa parseDfa(String s) {
        return parseDfa(s.split("\n"), ParsingMode.LETTERS_LIST);
    }

    public static Dfa parseDfa(String[] lines, ParsingMode mode) {
        String[][] dataLines;
        int totalLines = lines.length;
        while (lines[totalLines - 1].equals("")) {
            totalLines--;
        }
        if (!lines[0].equals("Graph:")) {
            dataLines = new String[totalLines][0];
            for (int i = 0; i < totalLines; i++) {
                dataLines[i] = lines[i].split(" ");
            }
        } else {
            dataLines = new String[totalLines - 1][0];
            for (int i = 0; i < totalLines - 1; i++) {
                dataLines[i] = lines[i + 1].split(" ");
            }
        }
        HashMap<Integer, List<Pair<Character, Integer>>> mp = new HashMap<>();

        int i = 0;
        Integer start = null;
        if (dataLines[0][0].equals("s")) {
            start = Integer.parseInt(dataLines[0][1]);
            i++;
        }
        Map<Integer, Integer> terminalIds = new HashMap<>();
        while (dataLines[i].length == 2) {
            terminalIds.put(Integer.parseInt(dataLines[i][0]), Integer.parseInt(dataLines[i][1]));
            i++;
        }
        for (; i < dataLines.length; i++) {
            String[] dataLine = dataLines[i];
            int a = Integer.parseInt(dataLine[0]);
            int b = Integer.parseInt(dataLine[1]);
            mp.putIfAbsent(a, new ArrayList<>());
            if (mode == ParsingMode.LETTERS_LIST) {
                for (char c : dataLine[2].toCharArray()) {
                    assert c < Transitions.MAX_CHAR : "" + c;
                    mp.get(a).add(new Pair<>(c, b));
                }
            } else {
                for (int j = 2; j < dataLine.length; j++) {
                    mp.get(a).add(new Pair<>((char) Integer.parseInt(dataLine[j]), b));
                }
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


        return new Dfa(nodes.get(start == null ? 0 : start));
    }
}