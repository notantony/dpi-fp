package main.graph;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.algo.compressor.RecursiveCompressorStatic;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import main.io.Input;
import main.io.Static;
import util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static main.Main.*;

public class GroupsMetric {
    public static void main(String[] args) throws IOException {
        List<List<String>> groupRules = Input.readGroups(Static.FILTERED, Static.TOP_10_GROUPS);

        for (List<String> group : groupRules) {
            processGroup(group);
        }
    }

    public static void processGroup(List<String> rules) {
        System.out.println("Total " + rules.size() + " rules");

        List<Nfa> nfas = rules.parallelStream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());
        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i);
        }

        // Nfa:
        List<Nfa> nfasSingle = rules.parallelStream()
                .map(Main::buildNfa)
                .peek(nfa -> nfa.close(1))
                .collect(Collectors.toList());

        // Dfa:
        int sum = nfasSingle.parallelStream()
                .map(Main::convert)
                .map(Main::minimizeHopcroft)
                .map(Dfa::nodesCount)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("Sum-of-single: " + sum);

        int max = nfasSingle.parallelStream()
                .map(Main::convert)
                .map(Main::minimizeHopcroft)
                .map(Dfa::nodesCount)
                .reduce(Integer::max)
                .orElse(0);
        System.out.println("Max-of-single: " + max);

//        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
//        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

//        Dfa combined = convert(Nfa.union(nfas));
//        System.out.println("Combined: " + combined.nodesCount());
//        Dfa dfaMin = minimizeHopcroft(combined);
//        System.out.println("Minimized: " + dfaMin.nodesCount());

//        System.out.println("Minimized-cut: " + (dfaMin.cutCount() + nfas.size()));

        Dfa modified = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + modified.nodesCount());
        Utils.writeTo("./output/graph/result.txt", modified.print(Dfa.PrintingMode.SERIALIZE));

//        Dfa modifiedMin = minimizeHopcroft(modified);
        compress(modified);
        System.out.println("ThompsonModifiedHeuristic: " + modified.nodesCount());

        new RecursiveCompressorStatic().compress(modified);
        System.out.println("HeuristicThenRecursive: " + modified.nodesCount());

        Dfa modifiedCopy = new ThompsonModified().run(nfas);
        new RecursiveCompressorStatic().compress(modifiedCopy);
        System.out.println("ThompsonModifiedRecursive: " + modifiedCopy.nodesCount());
        int x = modifiedCopy.nodesCount();
        compress(modifiedCopy);
        assert x == modifiedCopy.nodesCount();

//        modifiedMinCopy.print();

        System.out.println();
        System.out.flush();
    }

    public static void processGroupDependent(List<String> rules) {
        System.out.println("Total " + rules.size() + " rules");

        List<Nfa> nfas = rules.parallelStream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());
        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i);
        }

        // Nfa:
//        List<Nfa> nfasSingle = rules.parallelStream()
//                .map(Main::buildNfa)
//                .peek(nfa -> nfa.close(1))
//                .collect(Collectors.toList());

        // Dfa:
//        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
//        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

        Dfa combined = convert(Nfa.union(nfas));
        System.out.println("Combined: " + combined.nodesCount());
        Dfa dfaMin = minimizeHopcroft(combined);
        System.out.println("Minimized: " + dfaMin.nodesCount());

        System.out.println();
        System.out.flush();
    }
}
