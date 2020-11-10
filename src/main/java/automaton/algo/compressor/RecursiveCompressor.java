package automaton.algo.compressor;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import main.io.Static;
import util.Pair;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecursiveCompressor {
    private Dfa dfa;
    private Map<Node, Integer> index;
    private ArrayList<Node> nodes;
    private byte[][] distinct; // 1 <-> dependent, 0 <-> maybe independent
    private MergeDsu mergeQueue;

    List<HashMap<Character, Set<Integer>>> incident;
    private Set<Integer> nonMerged;
    private List<Set<Integer>> dependent;  // TODO: dependent -> independent?

    private boolean areDependent(int i, int j) {
//        if (i < j) {
//            return areDependent(j, i);
//        }
        return dependent.get(i).contains(j);
    }

    private void setDependent(int i, int j) {
//        if (i < j) {
//        }
//        setDependent(j, i);
        dependent.get(i).add(j);
        dependent.get(j).add(i);
    }

    private void buildMatrix() {
        nodes = new ArrayList<>(dfa.allNodes());
        index = new HashMap<>();
        int counter = 0;
        for (Node node : nodes) {
            if (node != null) {
                index.put(node, counter);
            }
            counter++;
        }

        nonMerged = new HashSet<>(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null) {
                nonMerged.add(i);
            }
        }

        Map<Node, Set<Pair<Character, Node>>> mp = new HashMap<>();
        for (Node node : nodes) {
//            if (node != null) {
            mp.put(node, new HashSet<>());
//            }
        }
        for (Node node : nodes) {
            if (node != null) {
                node.getEdges().forEach((c, target) -> {
                    mp.get(target).add(new Pair<>(c, node));
                });
            }
        }

        dependent = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            dependent.add(new HashSet<>());
        }

//        distinct = new byte[nodes.size()][nodes.size()];
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).isTerminal()) {
                continue;
            }
            assert nodes.get(i).getTerminal().size() == 1;
            for (int j = 0; j < i; j++) {
                if (nodes.get(i).isTerminal() && nodes.get(j).isTerminal()) {
                    assert !nodes.get(i).getTerminal().equals(nodes.get(j).getTerminal());
//                    distinct[i][j] = 1;
                    setDependent(i, j);
                    queue.add(new Pair<>(i, j));
                }
            }
        }

        incident = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            incident.add(new HashMap<>());
        }
        for (int i = 0; i < nodes.size(); i++) {
            int finalI = i;
            mp.get(nodes.get(i)).forEach(pair -> {
                incident.get(finalI).putIfAbsent(pair.getFirst(), new HashSet<>());
                incident.get(finalI).get(pair.getFirst()).add(index.get(pair.getSecond()));
            });
        }

        traverseDependence(queue);
    }


    private void printDependence() {
        System.out.print("Dependence:\n ");
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null) {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null) {
                System.out.print(i);
                for (int j = 0; j < nodes.size(); j++) {
                    if (nodes.get(j) != null) {
                        int s = areDependent(i, j) ? 1 : 0;
                        System.out.print(" " + s);
                    }
                }
                System.out.println();
            }
        }
        System.out.println();

    }

//    private void buildMatrixFast() {
//        nodes = new ArrayList<>(dfa.allNodes());
//        index = new HashMap<>();
//        int counter = 0;
//        for (Node node : nodes) {
//            index.put(node, counter);
//            counter++;
//        }
//
//        nonMerged = new HashSet<>(nodes.size());
//        for (int i = 0; i < nodes.size(); i++) {
//            nonMerged.add(i);
//        }
//
//        mp = new HashMap<>();
//        for (Node node : nodes) {
//            mp.put(node, new HashSet<>());
//        }
//        for (Node node : nodes) {
//            node.getEdges().forEach((c, target) -> {
//                mp.get(target).add(new Pair<>(c, node));
//            });
//        }
//
//        dependent = new ArrayList<>();
//        for (int i = 0; i < nodes.size(); i++) {
//            dependent.add(new HashSet<>());
//        }
//
////        distinct = new byte[nodes.size()][nodes.size()];
//        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
//
//        for (int i = 0; i < nodes.size(); i++) {
//            if (!nodes.get(i).isTerminal()) {
//                continue;
//            }
//            assert nodes.get(i).getTerminal().size() == 1;
//            for (int j = 0; j < i; j++) {
//                if (nodes.get(i).isTerminal() && nodes.get(j).isTerminal()) {
//                    assert !nodes.get(i).getTerminal().equals(nodes.get(j).getTerminal());
////                    distinct[i][j] = 1;
//                    setDependent(i, j);
//                    queue.add(new Pair<>(i, j));
//                }
//            }
//        }
//
//        incident = new ArrayList<>();
//        for (int i = 0; i < nodes.size(); i++) {
//            incident.add(new HashMap<>());
//        }
//        for (int i = 0; i < nodes.size(); i++) {
//            int finalI = i;
//            mp.get(nodes.get(i)).forEach(pair -> {
//                incident.get(finalI).putIfAbsent(pair.getFirst(), new HashSet<>());
//                incident.get(finalI).get(pair.getFirst()).add(index.get(pair.getSecond()));
//            });
//        }
//
//        traverseDependence(queue);
//    }

//    private List<Pair<Integer, Integer>> mergePair(int aId, int bId) {
//        // TODO: non-merged
//        // TODO: update distinct
//        // TODO: remove asserts
//        assert !nodes.get(aId).isTerminal() : "Found terminal: " + nodes.get(aId).getTerminal();
//        assert !nodes.get(bId).isTerminal() : "Found terminal: " + nodes.get(bId).getTerminal();
//        ArrayList<Pair<Integer, Integer>> needsMerge = new ArrayList<>();
//        Node a = nodes.get(aId);
//        Node b = nodes.get(bId);
//        Map<Character, Node> aEdges = a.getEdges();
//        Map<Character, Node> bEdges = b.getEdges();
//
//        aEdges.forEach((c, target) -> {
//            if (bEdges.containsKey(c)) {
//                Node conflict = bEdges.get(c);
//                int conflictId = index.get(conflict);
//                needsMerge.add(new Pair<>(index.get(target), conflictId));
//            }
//            if (target == a) {
//                b.addEdge(c, b);
//            } else {
//                boolean removed = mp.get(target).remove(new Pair<>(c, a));
//                assert removed;
//                mp.get(target).add(new Pair<>(c, b));
//                b.addEdge(c, target);
//            }
//        });
//        for (Pair<Character, Node> pair : mp.get(a)) {
//            pair.getSecond().addEdge(pair.getFirst(), b);
//        }
//        mp.get(b).addAll(mp.get(a).stream().map(pair -> {
//            if (pair.getSecond() == a) {
//                return new Pair<>(pair.getFirst(), b);
//            }
//            return pair;
//        }).collect(Collectors.toSet()));
//
//        nodes.set(aId, null);
//        if (dfa.getStart() == a) {
//            dfa.setStart(b);
//        }
//        return needsMerge;
//    }

//    private void mergeAll(Set<Integer> mergeSet) { // TODO: faster & parallel implementation using streams?
//        int bId = mergeSet.stream().min(Integer::compareTo).get();
//        mergeSet.stream()
//                .filter(id -> id != bId)
//                .flatMap(id -> mergePair(id, bId).stream())
//                .peek(pair -> {
//                    if (mergeSet.contains(pair.getFirst())) {
//                        pair.setFirst(bId);
//                    }
//                    if (mergeSet.contains(pair.getSecond())) {
//                        pair.setSecond(bId);
//                    }
//                })
//                .distinct()
//                .forEach(pair -> mergeQueue.insertPair(pair.getFirst(), pair.getSecond()));
//    }

    private boolean runMergeAt(int i, int j) {
        mergeQueue = new MergeDsu();
        mergeQueue.insertPair(i, j);
        if (!mergeQueue.processQueue()) {
            return false;
        }
        mergeQueue.applyPartition();
        return true;
    }

    private void traverseDependence(Queue<Pair<Integer, Integer>> queue) {
        while (!queue.isEmpty()) {  // TODO: replace with function & synchronized queue?
            Pair<Integer, Integer> cur = queue.remove();
            int i = cur.getFirst();
            int j = cur.getSecond();
//            int i = Integer.max(cur.getFirst(), cur.getSecond());
//            int j = Integer.min(cur.getFirst(), cur.getSecond());
            incident.get(i).keySet().stream()
                    .filter(incident.get(j)::containsKey)
                    .forEach(c -> {
                        incident.get(i).get(c).forEach(a -> {
                            incident.get(j).get(c).forEach(b -> {
//                                int targetA = Integer.max(a, b);
//                                int targetB = Integer.min(a, b);
//                                !nodes.get(i).isTerminal() && !nodes.get(j).isTerminal()
                                if (!areDependent(a, b)) {
                                    setDependent(a, b);
                                    queue.add(new Pair<>(a, b));
                                }
//                                if (distinct[targetA][targetB] == 0) {
//                                    distinct[targetA][targetB] = 1;
//                                    queue.add(new Pair<>(targetA, targetB));
//                                }
                            });
                        });
                    });
        }
    }

    private boolean tryShrink() {
        List<Integer> nonMergedList = new ArrayList<>(nonMerged);
        for (int i = 0; i < nonMergedList.size(); i++) {
            for (int j = 0; j < i; j++) { // TODO: FASTER pairs hashset?
//                int realI = Integer.max(nonMergedList.get(i), nonMergedList.get(j));
//                int realJ = Integer.min(nonMergedList.get(i), nonMergedList.get(j));
                int realI = nonMergedList.get(i);
                int realJ = nonMergedList.get(j);
//                if (distinct[realI][realJ] == 0) {
                if (!areDependent(realI, realJ)) {
                    boolean success = runMergeAt(realI, realJ);
                    if (!success) {
                        setDependent(realI, realJ);
//                        distinct[realI][realJ] = 1;
                        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
                        queue.add(new Pair<>(realI, realJ));
                        traverseDependence(queue);
                    } else {
                        // TODO: smart continue?
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void compress(Dfa dfa) {
        dfa.close();
        this.dfa = dfa;

        boolean updated = true;
        while (updated) {
            buildMatrix();
            if (Static.DEBUG_RUN) dfa.print(index);
            if (Static.DEBUG_RUN) printDependence();
            updated = tryShrink();
        }
    }

    private class MergeDsu {
        private Map<Integer, Component> componentsMap = new HashMap<>();
        private Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        public MergeDsu() {
        }

        private Component getComponent(int id) {
            if (componentsMap.containsKey(id)) {
                return componentsMap.get(id);
            }
            Component newComponent = new Component(id);
            componentsMap.put(id, newComponent);
            return newComponent;
        }

        private boolean inComponent(int id) {
            return componentsMap.containsKey(id);
        }

        private void applyPartition() { // TODO: reduce overhead for single-node components?
            List<Component> components = componentsMap.values().stream()
                    .distinct()
                    .collect(Collectors.toList());

            HashMap<Component, Integer> componentsIndex = new HashMap<>();
            for (Component component : components) { // Add new nodes
                Node newNode = new Node();
                int newNodeId = nodes.size();
                nodes.add(newNode);
                index.put(newNode, newNodeId);
                nonMerged.add(newNodeId);
                incident.add(new HashMap<>()); // TODO: do not need?
                dependent.add(new HashSet<>());

                componentsIndex.put(component, newNodeId);
            }

            if (Static.DEBUG_RUN) {
                System.err.println("Merging:");
                for (Component component : components) {
                    System.err.print(componentsIndex.get(component) + ": ");
                    String s = component.entries.stream().map(Objects::toString).collect(Collectors.joining(" "));
                    System.err.println(s);
                }
            }

            for (Component component : components) { // Fill nodes with edges
                Node newNode = nodes.get(componentsIndex.get(component));
                newNode.setEdges(component.mergedEdges.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                            int id = entry.getValue();
                            return nodes.get(inComponent(id) ? componentsIndex.get(getComponent(id)) : id);
                        })));
            }

            // TODO: No need to fix edges?
            for (Component component : components) { // Update incident
                int componentId = componentsIndex.get(component);
                for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                    char finalC = c;
                    Set<Integer> newIncident = component.entries.stream()
                            .flatMap(id -> incident.get(id).getOrDefault(finalC, Collections.emptySet()).stream())
                            .map(id -> inComponent(id) ? componentsIndex.get(getComponent(id)) : id)
                            .collect(Collectors.toSet());
                    if (newIncident.size() > 0) {
                        incident.get(componentId).put(c, newIncident);
                    }
                }
            }

            for (Component component : components) { // Update edges
                for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                    char finalC = c;
                    component.entries.stream()
                            .flatMap(id -> incident.get(id).getOrDefault(finalC, Collections.emptySet()).stream())
                            .forEach(id -> {
                                assert nodes.get(id) != null : id + " " + nodes.size();
                                nodes.get(id).addEdge(finalC, nodes.get(componentsIndex.get(component)));
                            });
                }
            }

            for (Component component : components) { // Update dependent
                int componentId = componentsIndex.get(component);
                component.entries.stream()
                        .flatMap(id -> dependent.get(id).stream())
                        .distinct()
                        .forEach(id -> setDependent(componentId, id));
            }

            int startId = index.get(dfa.getStart());
            if (inComponent(startId)) {
                dfa.setStart(nodes.get(componentsIndex.get(getComponent(startId))));
            }

            // TODO: anything more to remove?
            // TODO: remove mirror dependent
            for (Component component : components) { // Remove shrunk nodes
                component.entries.forEach(id -> {
                    assert !nodes.get(id).isTerminal();

                    Object __indexPrevious = index.remove(nodes.get(id));
                    assert __indexPrevious != null;

                    boolean __nonMergedRemoved = nonMerged.remove(id);
                    assert __nonMergedRemoved;

                    Object __dependentPrevious = dependent.set(id, null);
                    assert __dependentPrevious != null;

                    Object __nodesPrevious = nodes.set(id, null);
                    assert __nodesPrevious != null;
                });
            }

//            nonMerged.stream().map(nodes::get).filter(Objects::isNull).forEach(System.err::println);
//            dfa.print(index);
            assert nonMerged.stream().map(nodes::get).collect(Collectors.toSet()).equals(new HashSet<>(dfa.allNodes()));
            assert nonMerged.stream().map(nodes::get).noneMatch(Objects::isNull);
            assert nonMerged.stream().map(nodes::get)
                    .flatMap(node -> node.getEdges().values().stream())
                    .allMatch(node -> nonMerged.contains(index.get(node)));

//                    .flatMap(node -> node)
//                if (nodes.get(node).getEdges().values().stream().) {
//
//                }
//            }
//            return dfa;
        }

//        private void processPair(int aId, int bId) {
//            Set<Node> aComponent = traverse(aId);
//            // TODO: non-merged
//            // TODO: update distinct
//            // TODO: remove asserts
//            Node a = nodes.get(aId);
//            Node b = nodes.get(bId);
//            Map<Character, Node> aEdges = a.getEdges();
//            Map<Character, Node> bEdges = b.getEdges();
//
//            aEdges.forEach((c, target) -> {
//                if (bEdges.containsKey(c)) {
//                    Node conflict = bEdges.get(c);
//                    int conflictId = index.get(conflict);
//                    queue.add(new Pair<>(index.get(target), conflictId));
//                }
//                if (target == a) {
//                    b.addEdge(c, b);
//                } else {
//                    boolean removed = mp.get(target).remove(new Pair<>(c, a));
//                    assert removed;
//                    mp.get(target).add(new Pair<>(c, b));
//                    b.addEdge(c, target);
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
//
//            nodes.set(aId, null);
//            if (dfa.getStart() == a) {
//                dfa.setStart(b);
//            }
//            return needsMerge;
//        }

        private boolean processQueue() {
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> cur = queue.remove();
                int i = Integer.max(cur.getFirst(), cur.getSecond());
                int j = Integer.min(cur.getFirst(), cur.getSecond());
                if (getComponent(i).merge(getComponent(j)) == null) {
                    return false;
                }
            }
            return true;
        }

        public void insertPair(int i, int j) {
            if (Static.DEBUG_RUN) Logger.getGlobal().info("InsertPair:" + i + " " + j);
            if (i == j || getComponent(i) == getComponent(j)) {
//                return true;
                return;
            }
            queue.add(new Pair<>(i, j));
//            mergeSet.stream()
//                    .filter(id -> id != bId)
//                    .flatMap(id -> mergePair(id, bId).stream())
//                    .peek(pair -> {
//                        if (mergeSet.contains(pair.getFirst())) {
//                            pair.setFirst(bId);
//                        }
//                        if (mergeSet.contains(pair.getSecond())) {
//                            pair.setSecond(bId);
//                        }
//                    })
//                    .distinct()
//                    .forEach(pair -> mergeQueue.insertPair(pair.getFirst(), pair.getSecond()));
//            graph[i].add(j);
//            graph[j].add(i);
//            involved.add(i);
//            involved.add(j);
        }


//        private void traverseImpl(int c, HashSet<Integer> visited) {
//            visited.add(c);
//            graph[c].forEach(x -> {
//                if (!visited.contains(x)) {
//                    traverseImpl(c, visited);
//                }
//            });
//        }
//
//        private HashSet<Integer> traverse(int c) {
//            HashSet<Integer> visited = new HashSet<>();
//            traverseImpl(c, visited);
//            return visited;
//        }

//        public boolean isEmpty() {
//            return involved.isEmpty();
//        }

        private class Component {
            private Component head;
            private HashSet<Integer> entries;
            private Map<Character, Integer> mergedEdges;

            public Component(int n) {
                assert nonMerged.contains(n);
                assert nodes.get(n) != null : n + " " + nodes.size();
                entries = new HashSet<>();
                entries.add(n);
                mergedEdges = nodes.get(n).getEdges().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> index.get(entry.getValue())));
            }

            public Component getHead() {
                if (head == null) {
                    return this;
                }
                return head = head.getHead();
            }

            public Component merge(Component other) {
                if (this == other) {
                    return this;
                }
//                assert this != other;
                if (entries.size() < other.entries.size()) {
                    return other.merge(this);
                }
                if (Static.DEBUG_RUN)
                    System.err.println("Entries 1: " + entries.stream().map(Objects::toString).collect(Collectors.joining(" ")));
                if (Static.DEBUG_RUN)
                    System.err.println("Entries 2: " + other.entries.stream().map(Objects::toString).collect(Collectors.joining(" ")));

                for (Integer id : entries) {
                    for (Integer otherId : other.entries) {
//                        if (distinct[Integer.max(id, otherId)][Integer.min(id, otherId)] == 1) {
                        if (areDependent(id, otherId)) {
                            return null;
                        }
                    }
                }

                for (Map.Entry<Character, Integer> entry : other.mergedEdges.entrySet()) {
                    Character c = entry.getKey();
                    Integer otherTarget = entry.getValue();
                    if (mergedEdges.containsKey(c)) {
                        Integer target = mergedEdges.get(c);
                        mergeQueue.insertPair(otherTarget, target);
                    } else {
                        mergedEdges.put(c, otherTarget);
                    }
                }
                other.entries.forEach(id -> componentsMap.put(id, this));
                entries.addAll(other.entries);
                other.head = this;
                if (Static.DEBUG_RUN)
                    System.err.println("New component: " + entries.stream().map(Objects::toString).collect(Collectors.joining(" ")));
                return this;
            }
        }
    }
}
