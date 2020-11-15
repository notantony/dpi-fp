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

public class MergePairValidator {
    private Dfa dfa;
    private Map<Node, Integer> index;
    private ArrayList<Node> nodes;

    private IntMonitor __debugSizeMonitor;

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
    }

    private MergeDsu runMergeAt(int i, int j) {
        MergeDsu mergeQueue = new MergeDsu();
        mergeQueue.insertPair(i, j);
        if (!mergeQueue.processQueue()) {
            return null;
        }
        return mergeQueue;
    }

    private AtomicReference<Pair<Integer, Integer>> foundPair;
    volatile MergeDsu dsu;

    private boolean tryShrink() {

        foundPair = new AtomicReference<>(null);

        ArrayBlockingQueue<Pair<Integer, Integer>> processingQueue = new ArrayBlockingQueue<>(nodes.size() * (nodes.size() - 1) / 2);

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < i; j++) {
                processingQueue.add(new Pair<>(i, j));
            }
        }

        Runnable tryMergeTask = new Runnable() {
            @Override
            public void run() {
                while (foundPair.get() == null) {
                    Pair<Integer, Integer> pair;
                    pair = processingQueue.poll();
                    __debugSizeMonitor.update(processingQueue.size());
                    if (pair == null) {
                        return;
                    }
                    MergeDsu success = runMergeAt(pair.getFirst(), pair.getSecond());
                    if (success != null) {
                        if (foundPair.compareAndSet(null, pair)) {
                            dsu = success;
                        }
                    }
                }
            }
        };

        int N_TASKS = 16;
        ExecutorService executor = Executors.newFixedThreadPool(N_TASKS);
        for (int i = 0; i < N_TASKS - 1; i++) {
            executor.submit(tryMergeTask);
        }

        try {
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Pair<Integer, Integer> result = foundPair.get();
        return result != null;
    }

    public boolean test(Dfa dfa) {
//        dfa.close();
        this.dfa = dfa;

        buildMatrix();
        __debugSizeMonitor = new IntMonitor("Pairs remaining", (int) Math.sqrt(nodes.size()) * nodes.size(), IntMonitor.Mode.LINEAR);

        if (__debugSizeMonitor.update(dfa.nodesCount())) {
//                Utils.writeTo("./output/graph/checkpoints/at" + dfa.nodesCount() + ".txt",
//                        dfa.print(Dfa.PrintingMode.SERIALIZE));
        }
        if (Static.DEBUG_RUN) dfa.print(index);
//        if (Static.DEBUG_RUN)
//            System.out.println(watchList.stream().map(Pair::toString).collect(Collectors.joining(" ")));
        return !tryShrink();
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
                        if (nodes.get(id).isTerminal() || nodes.get(otherId).isTerminal()) {
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
