package automaton.algo.compressor.recursive;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transitions;
import main.io.Static;
import util.IntMonitor;
import util.Pair;
import util.Utils;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecursiveCompressorDynamic {
    private Dfa dfa;
    private Map<Node, Integer> index;
    private ArrayList<Node> nodes;
    private byte[][] distinct; // 1 <-> dependent, 0 <-> maybe independent
    private MergeDsu mergeQueue;

    List<HashMap<Character, Set<Integer>>> incident;
    private Set<Integer> nonMerged;
//    private List<Set<Integer>> dependent;  // TODO: dependent -> independent?

    private IntMonitor __debugSizeMonitor = new IntMonitor("Nodes remaining", 100, IntMonitor.Mode.LINEAR);

    private boolean areDependent(int i, int j) {
        if (i < j) {
            return areDependent(j, i);
        }
        return distinct[i][j] == 1;
//        return dependent.get(i).contains(j);
    }

    private void setDependent(int i, int j) {
        if (i < j) {
            setDependent(j, i);
        }
//        dependent.get(i).add(j);
//        dependent.get(j).add(i);
        distinct[i][j] = 1;
//        distinct[j][i] = 1;
    }

    private void clearDependence(int i) { // Non-merged only
        for (int id : nonMerged) {
            distinct[Integer.max(id, i)][Integer.min(id, i)] = 0;
        }
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

        distinct = new byte[nodes.size()][nodes.size()];

        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).isTerminal()) {
                continue;
            }
            assert nodes.get(i).getTerminal().size() == 1;
            for (int j = 0; j < i; j++) {
                if (nodes.get(i).isTerminal() && nodes.get(j).isTerminal()) {
                    assert !nodes.get(i).getTerminal().equals(nodes.get(j).getTerminal());
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

    private void updateMatrix() {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        List<Integer> nonMergedList = new ArrayList<>(nonMerged);
        for (int i = 0; i < nonMergedList.size(); i++) {
            for (int j = 0; j < i; j++) {
                int realI = nonMergedList.get(i);
                int realJ = nonMergedList.get(j);
                if (areDependent(realI, realJ)) {
                    queue.add(new Pair<>(realI, realJ));
                }
            }
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

    private void printInfo() {
        System.out.println("Total " + nodes.size() + " nodes:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(Utils.objCode(nodes.get(i)) + "/" + i + ": ");
            Node node = nodes.get(i);
            if (node == null) {
                System.out.print("null");
            } else {
                Map<Character, Node> edges = node.getEdges();
                if (edges == null) {
                    System.out.print("null");
                } else {
                    if (edges.size() > 10) {
                        System.out.print("r" + edges.size() + " ");
                    }
                    edges.forEach((c, target) -> {
                        if (c >= 'a' && c <= 'f') {
                            System.out.print("<" + c + ", " + Utils.objCode(target) + "/" + index.get(target) + "> ");
                        }
                    });
                }
            }
            System.out.println();
        }
        System.out.println("Incident:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(i + ":");
            if (incident.get(i) == null) {
                System.out.println("    null");
            } else {
                for (char c = 'a'; c <= 'f'; c++) {
                    Set<Integer> incidentCurrent = incident.get(i).get(c);
                    if (incidentCurrent != null) {
                        System.out.print("    " + c + ": <");
                        incidentCurrent.forEach(id -> System.out.print(id + " "));
                        System.out.println(">");
                    }
                }
            }
        }
        System.out.print("Non-merged: ");
        nonMerged.forEach(id -> System.out.print(id + " "));
        System.out.println();
        System.out.println("Start: " + Utils.objCode(dfa.getStart()) + " / " + index.get(dfa.getStart()));

        System.out.print("Index: ");
        index.forEach((node, id) -> {
            System.out.print("<" + Utils.objCode(node) + ", " + id + "> ");
        });
        System.out.println();

        System.out.print("Nodes: ");
        nodes.forEach(node -> System.out.print(Utils.objCode(node) + " "));
        System.out.println();
        System.out.println();
    }


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
            incident.get(i).keySet().stream()
                    .filter(incident.get(j)::containsKey)
                    .forEach(c -> {
                        incident.get(i).get(c).forEach(a -> {
                            incident.get(j).get(c).forEach(b -> {
                                if (!areDependent(a, b)) {
                                    setDependent(a, b);
                                    queue.add(new Pair<>(a, b));
                                }
                            });
                        });
                    });
        }
    }

    private boolean tryShrink() {
        List<Integer> nonMergedList = new ArrayList<>(nonMerged);
        for (int i = 0; i < nonMergedList.size(); i++) {
            for (int j = 0; j < i; j++) { // TODO: FASTER pairs hashset?
                int realI = nonMergedList.get(i);
                int realJ = nonMergedList.get(j);
                if (!areDependent(realI, realJ)) {
                    boolean success = runMergeAt(realI, realJ);
                    if (!success) {
                        setDependent(realI, realJ);
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

        buildMatrix();
        boolean updated = true;
        while (updated) {
            updateMatrix();
            __debugSizeMonitor.update(dfa.nodesCount());
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

            int nodesSavedSize = nodes.size();

            HashMap<Component, Integer> componentsIndex = new HashMap<>();
            HashMap<Component, Integer> componentsNewIndex = new HashMap<>();
            HashMap<Integer, Integer> componentsTransfer = new HashMap<>();
            for (Component component : components) { // Add new nodes
                Node newNode = new Node();
                int newNodeId = nodes.size();
                nodes.add(newNode);
                index.put(newNode, newNodeId);
//                nonMerged.add(newNodeId); // later
                incident.add(new HashMap<>()); // TODO: do not need?
//                dependent.add(new HashSet<>());

                componentsIndex.put(component, newNodeId);
                int newId = component.entries.stream().min(Integer::compareTo).get();
                componentsNewIndex.put(component, newId);
                componentsTransfer.put(newNodeId, newId);
            }

            if (Static.DEBUG_RUN) {
                System.err.println("Merging:");
                for (Component component : components) {
                    System.err.print(componentsIndex.get(component) + ": ");
                    String s = component.entries.stream().map(Objects::toString).collect(Collectors.joining(" "));
                    System.err.println(s);
                }
            }

            // Fill components with edges (orig index)
            for (Component component : components) {
                Node newNode = nodes.get(componentsIndex.get(component));
                newNode.setEdges(component.mergedEdges.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> nodes.get(entry.getValue()))));
            }

            // Fill incident (temporary index)
            for (Component component : components) {
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

            // Update incidents for non-components (final)
            for (Component component : components) {
                component.entries.forEach(entryId -> {
                    nodes.get(entryId).getEdges().forEach((c, node) -> {
                        int nodeId = index.get(node);
                        if (!inComponent(nodeId)) {
                            Map<Character, Set<Integer>> incidents = incident.get(nodeId);
                            incidents.put(c, incidents.get(c).stream()
                                    .map(id -> inComponent(id) ? componentsNewIndex.get(getComponent(id)) : id)
                                    .collect(Collectors.toSet())
                            );
                        }
                    });
                });
            }

            if (Static.DEBUG_RUN) printInfo();
            // Update edges for all (final index)
            for (Component component : components) {
                for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                    char finalC = c;
//                    component.entries.stream()
                    incident.get(componentsIndex.get(component)).getOrDefault(finalC, Collections.emptySet())
//                            .flatMap(id -> incident.get(id).getOrDefault(finalC, Collections.emptySet()).stream())
                            .forEach(id -> {
                                assert nodes.get(id) != null : id + " " + nodes.size();
                                nodes.get(id).addEdge(finalC, nodes.get(componentsIndex.get(component)));
                            });
                }
            }
            if (Static.DEBUG_RUN) printInfo();

            // TODO: remove solo nodes
            // TODO: anything more to remove?
            // TODO: remove mirror dependent

            // Calculate new dependence
            HashMap<Component, Set<Integer>> newDistincts = new HashMap<>();
            for (Component component : components) {
                Set<Integer> newDistinct = component.entries.stream()
                        .flatMap(entry -> nonMerged.stream()
                                .filter(id -> areDependent(entry, id)))
                        .distinct()
                        .map(id -> inComponent(id) ? componentsNewIndex.get(getComponent(id)) : id)
                        .collect(Collectors.toSet());
                newDistincts.put(component, newDistinct);
            }

            // Clear dependence
            for (Component component : components) {
                int newId = componentsNewIndex.get(component);
                clearDependence(newId);
            }

            // Update start
            int startId = index.get(dfa.getStart());
            if (inComponent(startId)) {
                if (Static.DEBUG_RUN) System.err.println("Start transferred: " + startId + " -> " + componentsIndex.get(getComponent(startId)));
                dfa.setStart(nodes.get(componentsIndex.get(getComponent(startId))));
            }

            // Move components
            for (Component component : components) {
                int newId = componentsNewIndex.get(component);
                int oldId = componentsIndex.get(component);
                index.remove(nodes.get(newId));
                newDistincts.get(component).forEach(id -> setDependent(newId, id));
                nodes.set(newId, nodes.get(oldId));
                index.put(nodes.get(oldId), newId);
                incident.set(newId, incident.get(oldId));
            }

            // Remove shrunk from nonMerged
            for (Component component : components) {
                component.entries.forEach(id -> {
                    boolean __nonMergedRemoved = nonMerged.remove(id);
                    assert __nonMergedRemoved;
                });
            }

            // Add component nodes
            for (Component component : components) {
                int newId = componentsNewIndex.get(component);
                nonMerged.add(newId);
            }

            // Update incident (final index)
            for (Component component : components) {
                int newComponentId = componentsNewIndex.get(component);
                HashMap<Character, Set<Integer>> incidentComponent = incident.get(newComponentId);
                for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                    if (incidentComponent.containsKey(c)) {
                        incidentComponent.put(c,
                                incident.get(newComponentId).get(c).stream()
                                        .map(id -> componentsTransfer.getOrDefault(id, id))
                                        .collect(Collectors.toSet()));

                    }
                }
            }

            // Destroy temporary nodes
            while (nodes.size() != nodesSavedSize) {
                int i = nodes.size() - 1;
                nodes.remove(i);
                incident.remove(i);
            }

            HashSet<Integer> newComponents = new HashSet<>(componentsNewIndex.values());
            for (Component component : components) { // Remove shrunk nodes
                component.entries.forEach(id -> {
                    assert !nodes.get(id).isTerminal();
                    if (!newComponents.contains(id)) {
                        nodes.get(id).corrupt();

                        Object __indexPrevious = index.remove(nodes.get(id));
                        assert __indexPrevious != null;

                        Object __nodesPrevious = nodes.set(id, null);
                        assert __nodesPrevious != null;

                        incident.set(id, null);
                    }
                });
            }

            if (Static.DEBUG_RUN) printInfo();
            assert nonMerged.stream().map(nodes::get).noneMatch(Objects::isNull);
            if (Static.DEBUG_RUN) System.err.println(nonMerged.stream().map(Objects::toString).collect(Collectors.joining(" ")));
            if (Static.DEBUG_RUN) System.err.println(dfa.allNodes().stream().map(index::get).map(Objects::toString).collect(Collectors.joining(" ")));
            assert nonMerged.stream().map(nodes::get).collect(Collectors.toSet()).equals(new HashSet<>(dfa.allNodes()));
//            dfa.print(index);
            assert nonMerged.stream().map(nodes::get)
                    .flatMap(node -> node.getEdges().values().stream())
                    .allMatch(node -> nonMerged.contains(index.get(node)));
        }

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
                return;
            }
            queue.add(new Pair<>(i, j));
        }

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
