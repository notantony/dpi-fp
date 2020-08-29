package main.graph;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;

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
        BufferedReader groupsReader = Files.newBufferedReader(Paths.get("./input/top10groups.txt"));
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get("./input/filtered.txt"));

        List<List<Integer>> groups = groupsReader.lines().map(a ->
                Stream.of(a.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())).collect(Collectors.toList());

        List<String> rules = rulesReader.lines().collect(Collectors.toList());

        List<List<String>> groupRules = groups.stream().map(group ->
                group.stream()
                        .map(rules::get)
                        .collect(Collectors.toList())).collect(Collectors.toList());

        for (List<String> group: groupRules) {
            processGroup(group);
        }
    }

    private static void processGroup(List<String> rules) {
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

        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

        Dfa dfaMin = minimizeHopcroft(convert(Nfa.union(nfas)));
        System.out.println("Minimized: " + dfaMin.nodesCount());
        System.out.println("Minimized-cut: " + (dfaMin.cutCount() + nfas.size()));

        Dfa modified = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + modified.nodesCount());

        Dfa modifiedMin = minimizeHopcroft(modified);
        compress(modifiedMin);
        System.out.println("ThompsonModifiedHeuristic: " + modifiedMin.nodesCount());

        Dfa modifiedMinCopy = minimizeHopcroft(modified);
        new RecursiveCompressor().compress(modifiedMinCopy);
        System.out.println("ThompsonModifiedRecursive: " + modifiedMinCopy.nodesCount());
        modifiedMinCopy.print();

        System.out.println();
        System.out.flush();
    }
}
