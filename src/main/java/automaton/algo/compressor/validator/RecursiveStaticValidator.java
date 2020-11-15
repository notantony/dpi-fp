package automaton.algo.compressor.validator;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transitions;
import main.io.Static;
import util.IntMonitor;
import util.Pair;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecursiveStaticValidator {
    private Dfa dfa;
    private Map<Node, Integer> index;
    private ArrayList<Node> nodes;
    private byte[][] distinct; // 1 <-> dependent, 0 <-> maybe independent

    private List<HashMap<Character, Set<Integer>>> incident;

    //    private Set<Pair<Node, Node>> transferDependent;
    private Map<Integer, Node> transferNodes;

    private IntMonitor __debugSizeMonitor = new IntMonitor("Nodes remaining", 100, IntMonitor.Mode.LINEAR);

    private boolean areDependent(int i, int j) {
        if (i < j) {
            return areDependent(j, i);
        }
        return distinct[i][j] == 1;
    }

    private void setDependent(int i, int j) {
        if (i < j) {
            setDependent(j, i);
        }
        distinct[i][j] = 1;
    }

    private List<Pair<Integer, Integer>> buildMatrix() {
        List<Node> oldNodes = nodes;
        nodes = new ArrayList<>(dfa.allNodes());
        index = new HashMap<>();
        int counter = 0;
        for (Node node : nodes) {
            if (node != null) {
                index.put(node, counter);
            }
            counter++;
        }

        Map<Node, Set<Pair<Character, Node>>> mp = new HashMap<>();
        for (Node node : nodes) {
            mp.put(node, new HashSet<>());
        }
        for (Node node : nodes) {
            if (node != null) {
                node.getEdges().forEach((c, target) -> {
                    mp.get(target).add(new Pair<>(c, node));
                });
            }
        }

        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        ArrayList<Pair<Integer, Integer>> watchList = new ArrayList<>();
        if (distinct == null) {
            distinct = new byte[nodes.size()][nodes.size()];

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
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < i; j++) {
                    watchList.add(new Pair<>(i, j));
                }
            }
        } else {
            byte[][] newDistinct = new byte[nodes.size()][nodes.size()];
            for (int i = 0; i < distinct.length; i++) {
                Node nodeI = transferNodes.containsKey(i) ? transferNodes.get(i) : oldNodes.get(i);
                Integer targetI = index.get(nodeI);
                assert targetI != null;
                for (int j = 0; j < i; j++) {
                    Node nodeJ = transferNodes.containsKey(j) ? transferNodes.get(j) : oldNodes.get(j);
                    Integer targetJ = index.get(nodeJ);
                    assert targetJ != null;
                    if (distinct[i][j] == 1) {
                        newDistinct[targetI][targetJ] = 1;
                        newDistinct[targetJ][targetI] = 1;
                    }
                }
            }
            distinct = newDistinct;
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (distinct[i][j] == 1) {
                        queue.add(new Pair<>(i, j));
                    } else {
                        watchList.add(new Pair<>(i, j));
                    }
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
        return watchList;
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

    private MergeDsu runMergeAt(int i, int j) {
        MergeDsu mergeQueue = new MergeDsu();
        mergeQueue.insertPair(i, j);
        if (!mergeQueue.processQueue()) {
            return null;
        }
        return mergeQueue;
    }

    private void traverseDependence(Queue<Pair<Integer, Integer>> queue) {
//        Set<Pair<Integer, Integer>> delayed = Sets.newSetFromMap(new ConcurrentHashMap<>());
//        LinkedBlockingQueue<Pair<Integer, Integer>> task = new LinkedBlockingQueue<>(queue);
//        queue.clear();
//        AtomicInteger waiting = new AtomicInteger();
//
//        Runnable dfsRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Pair<Integer, Integer> pair;
//                while (true) {
//                    try {
//                        task.isEmpty()
//                        pair = task.poll();
//                        if (pair == null) {
//                            int waitingNow = waiting.();
//                            pair = task.poll(Long.MAX_VALUE, TimeUnit.DAYS);
//                        }
//                    } catch (InterruptedException e) {
//                        return;
//                    }
//                    waiting.c
//                    i = pair.getSecond()
//                }
//            }
//        }

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

    private AtomicReference<Pair<Integer, Integer>> foundPair;
    volatile MergeDsu dsu;

    private boolean tryShrink(List<Pair<Integer, Integer>> watchList) {
        if (watchList.size() == 0) {
            return false;
        }

        foundPair = new AtomicReference<>(null);

        ArrayBlockingQueue<Pair<Integer, Integer>> processingQueue = new ArrayBlockingQueue<>(watchList.size());
        LinkedBlockingQueue<Pair<Integer, Integer>> badPairs = new LinkedBlockingQueue<>();
        processingQueue.addAll(watchList);
        watchList.clear();

        Runnable tryMergeTask = new Runnable() {
            @Override
            public void run() {
//                System.err.println("Started traverse task?");
                while (foundPair.get() == null) {
                    Pair<Integer, Integer> pair;
                    do {
                        pair = processingQueue.poll();
                        if (pair == null) {
                            return;
                        }
                    } while (areDependent(pair.getFirst(), pair.getSecond()));
                    MergeDsu success = runMergeAt(pair.getFirst(), pair.getSecond());
                    if (success != null) {
                        if (foundPair.compareAndSet(null, pair)) {
                            dsu = success;
                        }
                    } else {
                        badPairs.add(pair);
                    }
                }
            }
        };

        Runnable traverseTask = new Runnable() {
            @Override
            public void run() {
                Pair<Integer, Integer> badPair;
                while (true) {
                    try {
                        badPair = badPairs.poll(Long.MAX_VALUE, TimeUnit.DAYS);
                    } catch (InterruptedException e) {
                        return;
                    }
                    setDependent(badPair.getFirst(), badPair.getSecond());
                    Queue<Pair<Integer, Integer>> tmpQueue = new ArrayDeque<>();
                    tmpQueue.add(badPair);
                    traverseDependence(tmpQueue);
                }
            }
        };


        int N_TASKS = 16;
        ExecutorService executor = Executors.newFixedThreadPool(N_TASKS);
        Future<?> traverseFuture = executor.submit(traverseTask);
        for (int i = 0; i < N_TASKS - 1; i++) {
//            System.err.println("i-th task started: " + i);
            executor.submit(tryMergeTask);
        }

        try {
            traverseFuture.cancel(true);
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        assert badPairs.isEmpty();
        Pair<Integer, Integer> result = foundPair.get();
        return result != null;
//        dsu.applyPartition();

//        for (Pair<Integer, Integer> p : watchList) {
//            int i = p.getFirst();
//            int j = p.getSecond();
//            if (!areDependent(i, j)) {
//                boolean success = runMergeAt(i, j);
//                if (!success) {
//                    setDependent(i, j);
//                    Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
//                    queue.add(new Pair<>(i, j));
//                    traverseDependence(queue);
//                } else {
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    public boolean test(Dfa dfa) {
        dfa.close();
        this.dfa = dfa;

        List<Pair<Integer, Integer>> watchList = buildMatrix();
        if (__debugSizeMonitor.update(dfa.nodesCount())) {
//                Utils.writeTo("./output/graph/checkpoints/at" + dfa.nodesCount() + ".txt",
//                        dfa.print(Dfa.PrintingMode.SERIALIZE));
        }
        if (Static.DEBUG_RUN) dfa.print(index);
        if (Static.DEBUG_RUN) printDependence();
        if (Static.DEBUG_RUN)
            System.out.println(watchList.stream().map(Pair::toString).collect(Collectors.joining(" ")));
        return !tryShrink(watchList);
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
            int startingSize = nodes.size();

            List<Component> components = componentsMap.values().stream()
                    .distinct()
                    .collect(Collectors.toList());

            HashMap<Component, Integer> componentsIndex = new HashMap<>();
            for (Component component : components) { // Add new nodes
                Node newNode = new Node();
                int newNodeId = nodes.size();
                nodes.add(newNode);
                index.put(newNode, newNodeId);
                incident.add(new HashMap<>()); // TODO: do not need?
//                dependent.add(new HashSet<>());

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

//            for (Component component : components) { // Update dependent
//                int componentId = componentsIndex.get(component);
//                component.entries.stream()
//                        .flatMap(id -> dependent.get(id).stream())
//                        .distinct()
//                        .forEach(id -> setDependent(componentId, id));
//            }

            int startId = index.get(dfa.getStart());
            if (inComponent(startId)) {
                dfa.setStart(nodes.get(componentsIndex.get(getComponent(startId))));
            }

//            transferDependent = new HashSet<>(); // TODO: streams
//            for (int i = 0; i < startingSize; i++) {
//                for (int j = 0; j < i; j++) {
//                    if (areDependent(i, j)) {
//                        Node a = inComponent(i) ? nodes.get(componentsIndex.get(getComponent(i))) : nodes.get(i);
//                        Node b = inComponent(j) ? nodes.get(componentsIndex.get(getComponent(j))) : nodes.get(j);
//                        transferDependent.add(new Pair<>(a, b));
//                    }
//                }
//            }
            transferNodes = new HashMap<>();
            for (Component component : components) {
                Node componentNode = nodes.get(componentsIndex.get(component));
                for (int nodeId : component.entries) {
                    transferNodes.put(nodeId, componentNode);
                }
            }

//            System.out.println("tDep: " + transferDependent.stream()
//                    .map(p -> new Pair<>(index.get(p.getFirst()), index.get(p.getSecond())))
//                    .map(Pair::toString).collect(Collectors.joining(" ")));

//            // TODO: anything more to remove?
//            // TODO: remove mirror dependent
//            for (Component component : components) { // Remove shrunk nodes
//                component.entries.forEach(id -> {
//                    assert !nodes.get(id).isTerminal();
//
//                    nodes.get(id).corrupt();
//
//                    Object __indexPrevious = index.remove(nodes.get(id));
//                    assert __indexPrevious != null;
//
//                    boolean __nonMergedRemoved = nonMerged.remove(id);
//                    assert __nonMergedRemoved;
//
////                    Object __dependentPrevious = dependent.set(id, null);
////                    assert __dependentPrevious != null;
//
//                    Object __nodesPrevious = nodes.set(id, null);
//                    assert __nodesPrevious != null;
//                });
//            }
//
////            nonMerged.stream().map(nodes::get).filter(Objects::isNull).forEach(System.err::println);
////            dfa.print(index);
//            assert nonMerged.stream().map(nodes::get).collect(Collectors.toSet()).equals(new HashSet<>(dfa.allNodes()));
//            assert nonMerged.stream().map(nodes::get).noneMatch(Objects::isNull);
//            assert nonMerged.stream().map(nodes::get)
//                    .flatMap(node -> node.getEdges().values().stream())
//                    .allMatch(node -> nonMerged.contains(index.get(node)));

//                    .flatMap(node -> node)
//                if (nodes.get(node).getEdges().values().stream().) {
//
//                }
//            }
//            return dfa;
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
                ;
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
                        insertPair(otherTarget, target);
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
