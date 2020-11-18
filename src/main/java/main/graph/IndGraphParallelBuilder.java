package main.graph;

import automaton.nfa.Nfa;
import intgraph.IntGraph;
import main.Main;
import util.Pair;
import util.Triple;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class IndGraphParallelBuilder {
    private int nThreads;
    private static Collection<Triple<Integer, Integer, Boolean>> results = Collections.synchronizedCollection(new ArrayList<>());
    private static final List<Nfa> nfas = new ArrayList<>();
    private static final Set<Pair<Integer, Integer>> nowRunning = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public IndGraphParallelBuilder(int nThreads) {
        this.nThreads = nThreads;
    }

    public IntGraph process(List<String> rules) {
        Logger.getGlobal().info("Building nfas...");
        nfas.addAll(rules.parallelStream().map(Main::buildNfa).collect(Collectors.toList()));
        Logger.getGlobal().info("Starting executor...");
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);


        Thread monitorThread = new Thread(() -> {
            while (true) {
                Logger.getGlobal().info("Processing: " + nowRunning.toString());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Logger.getGlobal().info("Shutting down monitor thread");
                    return;
                }
            }
        });

        try {
            Logger.getGlobal().info("Starting monitor thread");
            monitorThread.start();
            for (int i = 0; i < nfas.size(); i++) {
                for (int j = 0; j < i; j++) {
                    CheckIndependenceTask task = new CheckIndependenceTask(i, j);
                    executor.submit(task);
                }
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            Logger.getGlobal().info("Main executor finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
        } finally {
            monitorThread.interrupt();
        }

        IntGraph resultGraph = new IntGraph(nfas.size());
        results.forEach(entry -> {
            if (!entry.getC()) {
                resultGraph.addEdge(entry.getA(), entry.getB());
                resultGraph.addEdge(entry.getB(), entry.getA());
            }
        });

        return resultGraph;
    }


    private static class CheckIndependenceTask implements Runnable {
        int i, j;
        boolean result;

        public CheckIndependenceTask(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            Pair<Integer, Integer> pair = new Pair<>(i, j);
            nowRunning.add(pair);
            result = Main.runChecker(nfas.get(i), nfas.get(j));
            nowRunning.remove(pair);
            results.add(new Triple<>(i, j, result));
        }
    }
}
