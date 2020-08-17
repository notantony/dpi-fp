package main;

import automaton.nfa.Nfa;
import util.Pair;
import util.Triple;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BuildGraph {
    private static Collection<Triple<Integer, Integer, Boolean>> results = Collections.synchronizedCollection(new ArrayList<>());;
    private static final List<Nfa> nfas = new ArrayList<>();
    private static final Set<Pair<Integer, Integer>> nowRunning = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void main(String[] args) throws IOException {
        List<String> rules = Main.readAllRules();
        Logger.getGlobal().info("Building nfas...");
        nfas.addAll(rules.parallelStream().map(Main::buildNfa).collect(Collectors.toList()));
        Logger.getGlobal().info("Starting executor...");
        ExecutorService executor = Executors.newFixedThreadPool(8);


//        Thread outputThread = new Thread(()-> {
//            int counter = 0;
//            while (true) {
//                try {
//                    Triple<Integer, Integer, Boolean> result = results.take();
//                    System.out.println(result.getA() + " " + result.getB() + " " + (result.getC() ? 1 : 0));
//                    System.out.flush();
//                    counter++;
//                    if (counter == nfas.size() * (nfas.size() - 1) / 2) {
//                        return;
//                    }
//                } catch (InterruptedException e) {
////                    e.printStackTrace();
//                    return;
//                }
//            }
//        });
//        outputThread.start();

        Thread monitorThread = new Thread(() -> {
            while (true) {
                Logger.getGlobal().info("Processing: " + nowRunning.toString());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    return;
                }
            }
        });
        monitorThread.start();

        for (int i = 0; i < nfas.size(); i++) {
            for (int j = 0; j < i; j++) {
                CheckIndependenceTask task = new CheckIndependenceTask(i, j);
                executor.submit(task);
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            Logger.getGlobal().info("Main executor finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
        }
        monitorThread.interrupt();
//        outputThread.interrupt();
        System.out.println(results.size());
        results.stream().forEach(result ->
            System.out.println(result.getA() + " " + result.getB() + " " + (result.getC() ? 1 : 0)));
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
