package main.debug;

import automaton.algo.compressor.heuristic.ColorizationCompressor;
import automaton.algo.compressor.heuristic.HeuristicGraphBuilder;
import automaton.dfa.Dfa;
import intgraph.ChromaticNumberCalculator;
import intgraph.ColorizatorSimple;
import intgraph.IntGraph;
import main.io.Input;
import main.io.Static;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HeuristicColors {
    public static void main(String[] args) throws IOException {
        Files.list(Paths.get(Static.VALIDATION))
                .sorted()
                .map(p -> p.resolve("./modified.txt").toString())
                .map(Input::readSerialized)
                .forEachOrdered(HeuristicColors::processDfa1);

//        Files.list(Paths.get(Static.VALIDATION))
//                .sorted()
//                .map(p -> p.resolve("./modified.txt").toString())
//                .map(Input::readSerialized)
//                .forEachOrdered(HeuristicColors::processDfa);

//        IntGraph graph = new IntGraph(7);
//        graph.addEdge2(0, 1);
//        graph.addEdge2(0, 2);
//        graph.addEdge2(1, 2);
//        graph.addEdge2(3, 4);
//        graph.addEdge2(3, 5);
//        graph.addEdge2(3, 6);
//        graph.addEdge2(4, 5);
//        graph.addEdge2(4, 6);
//        graph.addEdge2(5, 6);
//        System.out.println(new ChromaticNumberCalculator().calculate(graph));
    }

    private static int groupId = 0;

    private static void processDfa(Dfa dfa) {
        groupId++;
        System.out.println("Group #" + groupId + " with " + dfa.nodesCount() + " nodes:");
        IntGraph graph = new HeuristicGraphBuilder().buildGraph(dfa);
        System.out.println("ChromaticNumber: " + new ChromaticNumberCalculator().calculate(graph));
    }

    public static int groupId1 = 0;

    private static void processDfa1(Dfa dfa) {
        groupId1++;
        System.out.println("Group #" + groupId1 + " with " + dfa.nodesCount() + " nodes:");
        new ColorizationCompressor().compress(dfa);
        System.out.println("ColorizationCompressor: " + dfa.nodesCount());
    }
}
