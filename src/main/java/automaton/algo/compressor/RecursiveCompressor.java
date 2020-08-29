package automaton.algo.compressor;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class RecursiveCompressor {
    private Dfa dfa;
    private HashMap<Node, Integer> index;
    private ArrayList<Node> nodes;
    private byte[][] distinct; // -1 <-> independent, 1 <-> dependent, 0 <-> not available
    private Map<Node, Set<Pair<Character, Node>>> mp;
    private MergeGraph mergeQueue;

    private byte checkInd(int i, int j) {
        if (distinct[i][j] != 0) {
            return distinct[i][j];
        }
        if (i < j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
        Node a = nodes.get(i);
        Node b = nodes.get(j);
        if (a.getEdges().size() > b.getEdges().size()) {
            Node tmp = a;
            a = b;
            b = tmp;
        }
//        Node finalB = b;
//        a.getEdges().forEach((c, targetA) -> {
        for (Map.Entry<Character, Node> entry : a.getEdges().entrySet()) {
            char c = entry.getKey();
            Node targetA = entry.getValue();
            if (b.getEdges().containsKey(c)) {
                byte result = checkInd(index.get(targetA), index.get(b.getEdges().get(c)));
                if (result == 1) {
                    return distinct[i][j] = 1;
                }
            }
        }
        return distinct[i][j] = -1;
    }

    private void buildMatrix() {
        nodes = new ArrayList<>(dfa.allNodes());
        index = new HashMap<>();
        int counter = 0;
        for (Node node : nodes) {
            index.put(node, counter);
            counter++;
        }

        mp = new HashMap<>();
        for (Node node : nodes) {
            mp.put(node, new HashSet<>());
        }
        for (Node node : nodes) {
            node.getEdges().forEach((c, target) -> {
                mp.get(target).add(new Pair<>(c, node));
            });
        }

        distinct = new byte[nodes.size()][nodes.size()];
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        for (int i = 0; i < nodes.size(); i++) {
            assert nodes.get(i).getTerminal().size() <= 1;
            for (int j = 0; j < i; j++) {
                if (nodes.get(i).isTerminal() && nodes.get(j).isTerminal() &&
                        !nodes.get(i).getTerminal().equals(nodes.get(j).getTerminal())) {
                    distinct[i][j] = 1;
                    queue.add(new Pair<>(i, j));
                }
            }
        }

        HashMap<Character, HashSet<Integer>>[] incident = new HashMap[nodes.size()];
        for (int i = 0; i < incident.length; i++) {
            incident[i] = new HashMap<>();
        }
        for (int i = 0; i < nodes.size(); i++) {
            int finalI = i;
            mp.get(nodes.get(i)).forEach(pair -> {
                incident[finalI].putIfAbsent(pair.getFirst(), new HashSet<>());
                incident[finalI].get(pair.getFirst()).add(index.get(pair.getSecond()));
            });
        }


        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.remove();
            int i = Integer.max(cur.getFirst(), cur.getSecond());
            int j = Integer.min(cur.getFirst(), cur.getSecond());
            incident[i].keySet().stream()
                    .filter(incident[j]::containsKey)
                    .forEach(c -> {
                        incident[i].get(c).forEach(a -> {
                            incident[j].get(c).forEach(b -> {
                                if (distinct[a][b] == 0) {
                                    distinct[a][b] = 1;
                                    queue.add(new Pair<>(a, b));
                                }
                            });
                        });
                    });
        }
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (distinct[i][j] == 0) {
                    distinct[i][j] = -1;
//                    checkInd(i, j);
                }
            }
        }
    }

    private List<Pair<Integer, Integer>> mergePair(int aId, int bId) {
        ArrayList<Pair<Integer, Integer>> needsMerge = new ArrayList<>();
        Node a = nodes.get(aId);
        Node b = nodes.get(bId);
        Map<Character, Node> aEdges = a.getEdges();
        Map<Character, Node> bEdges = b.getEdges();

        aEdges.forEach((c, target) -> {
            if (bEdges.containsKey(c)) {
                Node conflict = bEdges.get(c);
                int conflictId = index.get(conflict);
                needsMerge.add(new Pair<>(index.get(target), conflictId));
            }
            if (target == a) {
                b.addEdge(c, b);
            } else {
                boolean removed = mp.get(target).remove(new Pair<>(c, a));
                assert removed;
                mp.get(target).add(new Pair<>(c, b));
                b.addEdge(c, target);
            }
        });
        for (Pair<Character, Node> pair : mp.get(a)) {
            pair.getSecond().addEdge(pair.getFirst(), b);
        }
        mp.get(b).addAll(mp.get(a).stream().map(pair -> {
            if (pair.getSecond() == a) {
                return new Pair<>(pair.getFirst(), b);
            }
            return pair;
        }).collect(Collectors.toSet()));

        nodes.set(aId, null);
        if (dfa.getStart() == a) {
            dfa.setStart(b);
        }
        return needsMerge;
    }

    private void mergeAll(Set<Integer> mergeSet) { // TODO: faster & parallel implementation using streams?
        int bId = mergeSet.stream().min(Integer::compareTo).get();
        mergeSet.stream()
                .filter(id -> id != bId)
                .flatMap(id -> mergePair(id, bId).stream())
                .peek(pair -> {
                    if (mergeSet.contains(pair.getFirst())) {
                        pair.setFirst(bId);
                    }
                    if (mergeSet.contains(pair.getSecond())) {
                        pair.setSecond(bId);
                    }
                })
                .distinct()
                .forEach(pair -> mergeQueue.addPair(pair.getFirst(), pair.getSecond()));
//        Map<Character, Node> bEdges = b.getEdges();
//        bEdges.
////        Node b = nodes.get(j);
//
////        if (a == null) {
////            while (a)
////        }
//
////        assert distinct[i][j] == -1; // TODO: necessary, but not enough
//
//        for (int i = 1; i < mergeList.size(); i++) {
//            Node a = nodes.get(mergeList.get(i));
//
//            a.getEdges().forEach((c, target) -> {
//                int targetId = index.get(target);
//
//                if (bEdges.containsKey(c)) {
//                    Node conflictTarget = bEdges.get(c);
//                    if (mergeSet.contains(index.get(conflictTarget))) {
//                        conflictTarget = b;
//                    } else {
//
//                    }
//                } else {
//
//                }
//
//                if (mergeSet.contains(targetId)) {
//                    b.addEdge(c, b);
//                } else {
//                    boolean removed = mp.get(target).remove(new Pair<>(c, a));
//                    assert removed; // TODO: add to other implementations?
//                    mp.get(target).add(new Pair<>(c, b));
//                    if (bEdges.containsKey(c)) {
//                        if (bEdges.get(c) == target) {
//                            b.addEdge(c, target);
//                        } else {
//                            mergeQueue.addPair(targetId, a.getEdges().get(c));
//                        }
//                    }
//                }
//            });
//            for (Pair<Character, Node> pair : mp.get(a)) {
//                pair.getSecond().addEdge(pair.getFirst(), b);
//            }
//            mp.get(b).addAll(mp.get(a).stream().map(pair -> {
//                if (pair.getSecond() == a) {
//                    return new Pair<>(pair.getFirst(), b);
//                }
//                return pair;
//            }).collect(Collectors.toSet()));
//        }
//
//        nodes.set(i, null);
//        if (dfa.getStart() == a) {
//            dfa.setStart(b);
//        } // TODO: update queue

        // TODO: resolve conflicts && add to mergeQueue
        // TODO: hashset
    }

    private boolean runMerge() {
        Integer foundI = null, foundJ = null;
        for (int i = 0; foundI == null && i < nodes.size(); i++) {
            for (int j = 0; foundI == null && j < nodes.size(); j++) {
                if (distinct[i][j] == -1 && !nodes.get(i).isTerminal() && !nodes.get(j).isTerminal()) {
                    foundI = i;
                    foundJ = j;
                }
            }
        }
        if (foundI == null) {
            return false;
        }

        mergeQueue = new MergeGraph(nodes.size());
        mergeQueue.addPair(foundI, foundJ);
        while (!mergeQueue.isEmpty()) {
            Set<Integer> cur = mergeQueue.getMergeList();
            mergeAll(cur);
        }
        return true;
    }

    public void compress(Dfa dfa) {
        this.dfa = dfa;

        boolean updated = true;
        while (updated) {
            buildMatrix();
            updated = runMerge();
        }
    }

    private static class MergeGraph {
        private ArrayList<Integer>[] graph;
        private HashSet<Integer> involved = new HashSet<>();
        private HashSet<Integer> collected;

        public MergeGraph(int size) {
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }


        public void addPair(int i, int j) {
            if (i == j) {
                return;
            }
            graph[i].add(j);
            graph[j].add(i);
            involved.add(i);
            involved.add(j);
        }

        private void traverse(int c) {
            collected.add(c);
            graph[c].forEach(x -> {
                if (!collected.contains(x)) {
                    traverse(x);
                }
            });
        }

        public Set<Integer> getMergeList() {
            collected = new HashSet<>();
            int head = involved.iterator().next();
            traverse(head);
            collected.forEach(id -> {
                graph[id].clear();
                involved.remove(id);
            });
            return collected;
        }

        public boolean isEmpty() {
            return involved.isEmpty();
        }
    }
}
