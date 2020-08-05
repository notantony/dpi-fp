package main;

import automaton.algo.AlgoException;
import automaton.algo.DfaCompressor;
import automaton.algo.ThompsonModified;
import automaton.algo.ThompsonModifiedMulti;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import util.FutureList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static main.Main.*;

public class Multi {
    private static final Logger logger = Logger.getGlobal();
    private static List<String> rules = new ArrayList<>();
    private static int groupsCounter = 0;
    private static BufferedWriter groupsWriter;

    public static void main(String[] args) throws IOException {
        String inputPath = "./input/selected.txt";
        String partitionPath = "./output/partition.txt";

        groupsWriter = Files.newBufferedWriter(Paths.get(partitionPath));

        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(inputPath));
        rules = rulesReader.lines().collect(Collectors.toList());

        process(rules, 25);
        process(rules, 50);
        process(rules, 100);
        groupsWriter.close();
    }

    private static void process(List<String> rules, int n) throws IOException {
        logger.log(Level.INFO, "Processing " + rules.size() + " rules with n = " + n);
        List<Nfa> nfas = rules.parallelStream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());
        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i + 1);
        }
        Dfa multi = new ThompsonModifiedMulti(n).run(nfas);
        System.out.println("ThompsonModifiedMulti" + n + ": " + multi.nodesCount());
        new DfaCompressor().compress(multi);
        System.out.println("ThompsonModifiedMulti" + n + "-Compressed: " + multi.nodesCount());
    }
}
