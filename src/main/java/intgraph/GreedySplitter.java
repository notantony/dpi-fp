package intgraph;

import java.util.*;
import java.util.stream.Collectors;

public class GreedySplitter {
    private List<List<Integer>> result = new ArrayList<>();
    private List<List<Integer>> lastSets = new ArrayList<>();
    private HashSet<IntGraph.IntNode> curNodes;

    public List<List<Integer>> split(IntGraph graph) {
        curNodes = new HashSet<>(graph.nodes);
        int sum = 0;
        while (sum < graph.nodes.size()) {
            List<Integer> group = splitIter(graph);
            curNodes.removeAll(group.stream().map(a -> graph.nodes.get(a)).collect(Collectors.toList()));
            sum += group.size();
            result.add(group);
//            Logger.getGlobal().info("Added group");
        }

        return result;
    }

    public List<Integer> splitIter(IntGraph graph) {
        HashMap<Integer, Integer> penalty = new HashMap<>();

        PriorityQueue<IntGraph.IntNode> queue = new PriorityQueue<>(20, new Comparator<IntGraph.IntNode>() {
            @Override
            public int compare(IntGraph.IntNode o1, IntGraph.IntNode o2) {
                return Integer.compare(o1.edges.size() - penalty.getOrDefault(o1.id, 0),
                        o2.edges.size() - penalty.getOrDefault(o2.id, 0));
            }
        });
        queue.addAll(curNodes);

        List<Integer> group = new ArrayList<>();

        List<Integer> resultLastSet = null;
        while (!queue.isEmpty()) {
            IntGraph.IntNode cur = queue.remove();
            group.add(cur.id);

//            System.err.print(cur.edges.size() - penalty.getOrDefault(cur.id, 0) + " ");

            List<Integer> lastSet = new ArrayList<>();

            cur.edges.forEach(neighbour -> {
                if (queue.remove(graph.nodes.get(neighbour))) {
                    lastSet.add(neighbour);
                }
                graph.nodes.get(neighbour).edges.remove(cur.id);
            });

            lastSet.forEach(neighbour -> {
//                if (!queue.contains(graph.nodes.get(neighbour))) {
//                    return;
//                }
                graph.nodes.get(neighbour).edges.forEach(nNeighbour -> {
//                    if (nNeighbour != cur.id) {
                        penalty.put(nNeighbour, penalty.getOrDefault(nNeighbour, 0) + 1);
                        if (queue.remove(graph.nodes.get(nNeighbour))) {
                            queue.add(graph.nodes.get(nNeighbour));
                        }
//                    }
                });
            });

            lastSet.add(cur.id);

//            if (queue.size() == 0) {
//                HashSet<Integer> here = new HashSet<>(lastSet);
//                lastSet.forEach(x -> {
//                    long nn = graph.nodes.get(x).edges.stream()
//                            .filter(here::contains)
//                            .count();
//                    if (nn != here.size()) {
//                        System.err.println(nn);
//                        System.err.println(here.size());
////                        System.err.println(cur.edges.stream().filter(here::contains).count());
////                        System.err.println(cur.id);
////                        System.err.println(x);
////                        System.err.println(cur.edges.size() - penalty.getOrDefault(cur.id, 0));
////                        System.err.println(graph.nodes.get(x).edges.size() - penalty.getOrDefault(x, 0));
//                        throw new RuntimeException("s");
//                    }
//                });
//            }

            resultLastSet = lastSet;
        }
        assert resultLastSet != null;
        lastSets.add(resultLastSet);
        return group;
    }

    public List<List<Integer>> getLastSets() {
        return lastSets;
    }
}
