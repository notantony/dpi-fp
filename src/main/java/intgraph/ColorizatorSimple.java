package intgraph;

import util.IntMonitor;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class ColorizatorSimple {
    public List<List<Integer>> colorize(IntGraph graph) { // No single-node loops
        HashMap<Integer, Integer> penalty = new HashMap<>();
        PriorityQueue<IntGraph.IntNode> queue = new PriorityQueue<>(20, new Comparator<IntGraph.IntNode>() {
            @Override
            public int compare(IntGraph.IntNode o1, IntGraph.IntNode o2) {
                return -Integer.compare(o1.edges.size() - penalty.getOrDefault(o1.id, 0),
                        o2.edges.size() - penalty.getOrDefault(o2.id, 0));
            }
        });

        List<IntGraph.IntNode> remaining = new ArrayList<>(graph.nodes);

        List<List<Integer>> result = new ArrayList<>();
        while (!remaining.isEmpty()) {
            queue.addAll(remaining);
            remaining.clear();
            HashSet<Integer> inRun = new HashSet<>();
            while (!queue.isEmpty()) {
                IntGraph.IntNode cur = queue.remove();
                List<Integer> toInvolved = cur.edges.stream().filter(inRun::contains).collect(Collectors.toList());
                if (!toInvolved.isEmpty()) {
                    remaining.add(cur);
                    toInvolved.forEach(target -> {
                        graph.nodes.get(target).edges.remove(cur.id);
                        queue.remove(graph.nodes.get(target));
                        queue.add(graph.nodes.get(target));
                    });
                } else {
                    inRun.add(cur.id);
//                    cur.edges.forEach(target -> graph.nodes.get(target).edges.remove(cur.id));
                }
            }
            result.add(new ArrayList<>(inRun));
        }
        return result;
    }
}
