package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class IntGraph {
    public static class IntNode {
        int id;
        HashSet<Integer> edges = new HashSet<>();
        public IntNode(int id) {
            this.id = id;
        }
    }
    List<IntNode> nodes = new ArrayList<>();

    public static IntGraph parseGraph(Stream<String> lines) {
        IntGraph graph = new IntGraph();
        lines.forEach(line -> {
            String[] data = line.split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            int val = Integer.parseInt(data[2]);
            if (val == 1) {
                return;
            }
            while (Integer.max(a, b) >= graph.nodes.size()) {
                graph.nodes.add(new IntNode(graph.nodes.size()));
            }
            graph.nodes.get(a).edges.add(b);
            graph.nodes.get(b).edges.add(a);
        });
        return graph;
    }
}
