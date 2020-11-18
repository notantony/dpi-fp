package intgraph;

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

    public IntGraph() {}

    public IntGraph(int nNodes) {
        nodes = new ArrayList<>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            nodes.add(new IntNode(i));
        }
    }

    public int addNode() {
        int id = nodes.size();
        nodes.add(new IntNode(id));
        return id;
    }

    public void addEdge(int i, int j) {
        nodes.get(i).edges.add(j);
    }

    public void addEdge2(int i, int j) {
        nodes.get(i).edges.add(j);
        nodes.get(j).edges.add(i);
    }

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
