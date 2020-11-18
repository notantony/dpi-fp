package main.debug;

import automaton.algo.compressor.recursive.RecursiveCompressorMinRootDist;
import automaton.algo.compressor.recursive.RecursiveCompressorStatic;
import automaton.algo.compressor.validator.HeuristicValidator;
import automaton.algo.compressor.validator.MergePairValidator;
import automaton.algo.compressor.validator.RecursiveStaticValidator;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import main.io.Input;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static main.Main.compress;

public class RecursiveValidate {
    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 10; )
        Path path = Paths.get("./output/graph/validation");
//        Files.list(path)
//                .sorted()
//                .map(p -> p.resolve("./heuristic_recursive.txt").toString())
//                .map(Input::readSerialized)
//                .forEachOrdered(dfa -> RecursiveValidate.validate2(dfa, "HTR"));
//
//        Files.list(path)
//                .sorted()
//                .map(p -> p.resolve("./recursive.txt").toString())
//                .map(Input::readSerialized)
//                .forEachOrdered(dfa -> RecursiveValidate.validate2(dfa, "REC"));

//        Files.list(path)
//                .sorted()
//                .map(p -> p.resolve("./heuristic.txt").toString())
//                .map(Input::readSerialized)
//                .forEachOrdered(dfa -> RecursiveValidate.heuristic2validate(dfa, "HEU"));

        Files.list(path)
                .sorted()
                .map(p -> p.resolve("./modified.txt").toString())
                .map(Input::readSerialized)
                .forEachOrdered(RecursiveValidate::minRootHeuristic);



//        List<List<String>> groupRules = Input.readGroups(Static.FILTERED, Static.TOP_10_GROUPS);
//        for (List<String> group : groupRules) {
//            processGroup(group);
//        }
    }

    private static int groupId = 0;

    public static void validate1(List<String> rules) {
        groupId++;
        System.out.println("Group #" + groupId + " with " + rules.size() + " rules");

        List<Nfa> nfas = rules.parallelStream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());
        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i);
        }

        Dfa dfaHeuristic = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + dfaHeuristic.nodesCount());
        Utils.writeTo("./output/graph/validation/g" + groupId + "/modified.txt", dfaHeuristic.print(Dfa.PrintingMode.SERIALIZE));

        compress(dfaHeuristic);
        System.out.println("ThompsonModifiedHeuristic: " + dfaHeuristic.nodesCount());
        Utils.writeTo("./output/graph/validation/g" + groupId + "/heuristic.txt", dfaHeuristic.print(Dfa.PrintingMode.SERIALIZE));

        new RecursiveCompressorStatic().compress(dfaHeuristic);
        System.out.println("HeuristicThenRecursive: " + dfaHeuristic.nodesCount());
        Utils.writeTo("./output/graph/validation/g" + groupId + "/heuristic_recursive.txt", dfaHeuristic.print(Dfa.PrintingMode.SERIALIZE));

        boolean test1_1 = new HeuristicValidator().test(dfaHeuristic);
        assert test1_1;
        System.out.println("(1) HTR: " + (test1_1 ? "ok" : "failed"));
        boolean test2_1 = new RecursiveStaticValidator().test(dfaHeuristic);
        assert test2_1;
        System.out.println("(2) HTR: " + (test2_1 ? "ok" : "failed"));

//        new RecursiveCompressorStatic().compress(dfaHeuristic);

        Dfa dfaRecursive = new ThompsonModified().run(nfas);
        new RecursiveCompressorStatic().compress(dfaRecursive);
        System.out.println("ThompsonModifiedRecursive: " + dfaRecursive.nodesCount());
        Utils.writeTo("./output/graph/validation/g" + groupId + "/recursive.txt", dfaHeuristic.print(Dfa.PrintingMode.SERIALIZE));

        boolean test1_2 = new HeuristicValidator().test(dfaRecursive);
        assert test1_2;
        System.out.println("(1) TMR: " + (test1_2 ? "ok" : "failed"));
        boolean test2_2 = new RecursiveStaticValidator().test(dfaHeuristic);
        assert test2_2;
        System.out.println("(2) TMR: " + (test2_2 ? "ok" : "failed"));


//        modifiedMinCopy.print();

        System.out.println();
        System.out.flush();
    }

    public static void validate2(Dfa dfa, String name) {
        boolean test3 = new MergePairValidator().test(dfa);
        assert test3;
        System.out.println("(3) " + name + ": " + (test3 ? "ok" : "failed"));
    }

    public static void heuristic2validate(Dfa dfa, String name) {
        int x = dfa.nodesCount();
        compress(dfa);
        boolean success = dfa.nodesCount() == x;
        assert success;
        System.out.println("(4) " + name + ": " + (success ? "ok" : "failed"));
    }

    private static void minRootHeuristic(Dfa dfa) {
        groupId++;
        System.out.println("Group #" + groupId + " with " + dfa.nodesCount() + " nodes");
        new RecursiveCompressorMinRootDist().compress(dfa);
        System.out.println("MinRootDist: " + dfa.nodesCount());
    }
}
