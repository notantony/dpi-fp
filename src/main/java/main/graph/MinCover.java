package main.graph;

import automaton.nfa.Nfa;
import graph.GreedySplitter;
import graph.IntGraph;
import main.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MinCover {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("./output/graph/graph.txt"));
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get("./input/filtered.txt"));
        List<String> rules = rulesReader.lines().collect(Collectors.toList());

        IntGraph graph = IntGraph.parseGraph(reader.lines());
        int i = 1;
        GreedySplitter splitter = new GreedySplitter();
        List<List<Integer>> groups = splitter.split(graph);
        System.out.println("Total " + groups.size());
        for (List<Integer> group: groups) {
            System.out.println("" + group.size());
            System.out.println(group.stream().map(Objects::toString).collect(Collectors.joining(" ")));
        }


//        List<List<Integer>> lastSets = splitter.getLastSets();
//        for (List<Integer> set: lastSets) {
//            Logger.getGlobal().info("Processing " + set.size() + " rules:");
//            for (int j = 0; j < set.size(); j++) {
//                for (int k = 0; k < j; k++) {
//                    Nfa a = Main.buildNfa(rules.get(set.get(j)));
//                    Nfa b = Main.buildNfa(rules.get(set.get(k)));
////                    assert !Main.runChecker(a, b);
//                    if (Main.runChecker(a, b)) {
//                        if (Main.runThompsonAlt(a, b)) {
//                            throw new RuntimeException("a != b");
//                        } else {
//                            throw new RuntimeException("checker != thompson");
//                        }
//                    }
////                    assert !Main.runThompsonAlt(a, b);
////                    boolean thompson = Main.runThompsonAlt(a, b);
////                    assert thompson == Main.runChecker(a, b);
//                }
//            }
//        }
    }
}
