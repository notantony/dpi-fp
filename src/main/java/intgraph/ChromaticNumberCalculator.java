package intgraph;

import util.IntMonitor;

import java.util.*;
import java.util.stream.Collectors;

public class ChromaticNumberCalculator {
    private IntMonitor __debugNodesSize = new IntMonitor("Remaining nodes", 100, IntMonitor.Mode.LINEAR);
//    private IntMonitor __debugEdgesSize = new IntMonitor("Remaining edges", 100, IntMonitor.Mode.LINEAR);

    public int calculate(IntGraph graph) { // No one-node loops
        int colors = 0;
        while (graph.nodes.size() != 0) {
            graph = processStep(graph);
            colors++;
        }
        return colors;
    }

    private IntGraph processStep(IntGraph graph) {
        int edges = graph.nodes.stream().map(node -> node.edges.size()).reduce(0, Integer::sum) / 2;
        __debugNodesSize.update(graph.nodes.size());

        HashMap<Integer, Integer> penalty = new HashMap<>();
        PriorityQueue<IntGraph.IntNode> queue = new PriorityQueue<>(20, new Comparator<IntGraph.IntNode>() {
            @Override
            public int compare(IntGraph.IntNode o1, IntGraph.IntNode o2) {
                return -Integer.compare(o1.edges.size() - penalty.getOrDefault(o1.id, 0),
                        o2.edges.size() - penalty.getOrDefault(o2.id, 0));
            }
        });

        queue.addAll(graph.nodes);
        IntGraph future = new IntGraph();
        HashMap<Integer, Integer> toFuture = new HashMap<>();
        while (edges != 0) {
            IntGraph.IntNode cur = queue.remove();

            int newId = future.addNode();
            toFuture.put(cur.id, newId);

            assert cur.edges.size() - penalty.getOrDefault(cur.id, 0) > 0 : cur.id;

            for (int target : cur.edges) {
                if (!toFuture.containsKey(target)) {
                    edges--;
                    penalty.put(target, penalty.getOrDefault(target, 0) + 1);
                    boolean __success = queue.remove(graph.nodes.get(target));
                    assert __success;
                    queue.add(graph.nodes.get(target));
                } else {
                    future.addEdge2(newId, toFuture.get(target));
                }
            }
        }

        return future;
    }
}
