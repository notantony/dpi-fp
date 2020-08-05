package main;

import automaton.algo.*;
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

public class PreparePlot {
    private static final Logger logger = Logger.getGlobal();
    private static BufferedWriter plotWriter;

    public static void main(String[] args) throws IOException {
        String inputPath = "./input/selected.txt";
        String partitionPath = "./output/plot.txt";

        plotWriter = Files.newBufferedWriter(Paths.get(partitionPath));

        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(inputPath));
        List<String> rules = rulesReader.lines().collect(Collectors.toList());

        List<String> prefix = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            if (i % 25 == 0) {
                calc(prefix);
            }
            prefix.add(rules.get(i));
        }
//        calc(rules);

        plotWriter.close();
    }

    private static void calc(List<String> rules) {
        System.out.println(rules.size());
        logger.info("Calculating for " + rules.size() + " rules:");

        logger.info("Building nfas...");
        List<Nfa> nfas = rules.parallelStream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());

        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i + 1);
        }

//        // Dfa:
//        int sum = nfasSingle.parallelStream()
//                .map(Main::convert)
//                .map(Main::minimizeHopcroft)
//                .map(Dfa::nodesCount)
//                .reduce(Integer::sum)
//                .orElse(0);
//        System.out.println("Sum-of-single: " + sum);

//        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
//        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

        logger.info("Minimizing...");
        Dfa dfaMin = minimizeHopcroft(convert(Nfa.union(nfas)));
        System.out.println("Minimized: " + dfaMin.nodesCount());
//        System.out.println("Minimized-cut: " + (dfaMin.cutCount() + nfas.size()));

        logger.info("Modified...");
        Dfa modified = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + modified.nodesCount());

        logger.info("Compressing...");
        compress(modified);
        System.out.println("ThompsonModifiedHeuristic: " + modified.nodesCount());

        logger.info("Modified5...");
        Dfa modified5 = new ThompsonModifiedMulti(5).run(nfas);
        logger.info("Compressing...");
        new DfaCompressor().compress(modified5);
        System.out.println("ThompsonModifiedHeuristic5: " + modified5.nodesCount());

        logger.info("Modified10...");
        Dfa modified10 = new ThompsonModifiedMulti(10).run(nfas);
        logger.info("Compressing...");
        new DfaCompressor().compress(modified10);
        System.out.println("ThompsonModifiedHeuristic10: " + modified10.nodesCount());

        logger.info("Modified5...");
        Dfa modified52 = new ThompsonModifiedMulti(5).run(nfas);
        logger.info("Compressing...");
        new DfaCompressorMulti().compress(modified52);
        System.out.println("ThompsonModifiedHeuristic5: " + modified52.nodesCount());

        logger.info("Modified10...");
        Dfa modified102 = new ThompsonModifiedMulti(10).run(nfas);
        logger.info("Compressing...");
        new DfaCompressorMulti().compress(modified102);
        System.out.println("ThompsonModifiedHeuristic10: " + modified102.nodesCount());

        System.out.flush();
    }

}
