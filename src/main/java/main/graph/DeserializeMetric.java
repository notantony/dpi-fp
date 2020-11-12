package main.graph;

import automaton.algo.compressor.RecursiveCompressorDynamic;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import main.io.Static;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static main.Main.compress;

public class DeserializeMetric {
    public static void main(String[] args) throws IOException {
        Dfa modified = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./output/graph/dfa_minimized_610.txt")), Dfa.ParsingMode.DESERIALIZE);

//        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
//        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

//        Dfa combined = convert(Nfa.union(nfas));
//        System.out.println("Combined: " + combined.nodesCount());
//        Dfa dfaMin = minimizeHopcroft(combined);
//        System.out.println("Minimized: " + dfaMin.nodesCount());

//        System.out.println("Minimized-cut: " + (dfaMin.cutCount() + nfas.size()));

//        Dfa modified = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + modified.nodesCount());
//        Utils.writeTo("./output/graph/result.txt", modified.print(Dfa.PrintingMode.SERIALIZE));



//        Dfa modifiedMin = minimizeHopcroft(modified);

        Dfa modifiedCopy = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./output/graph/dfa_minimized_610.txt")), Dfa.ParsingMode.DESERIALIZE);
        new RecursiveCompressorDynamic().compress(modifiedCopy);
        System.out.println("ThompsonModifiedRecursive: " + modifiedCopy.nodesCount());
        int x = modifiedCopy.nodesCount();
        compress(modifiedCopy);
        assert x == modifiedCopy.nodesCount();

        compress(modified);
        System.out.println("ThompsonModifiedHeuristic: " + modified.nodesCount());

        new RecursiveCompressorDynamic().compress(modified);
        System.out.println("HeuristicThenRecursive: " + modified.nodesCount());



//        modifiedMinCopy.print();

        System.out.println();
        System.out.flush();
    }
}
