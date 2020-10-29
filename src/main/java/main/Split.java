package main;

import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;

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

public class Split {
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

        while (!rules.isEmpty()) {
            cutGroup();
        }
        groupsWriter.close();
    }


    private static void cutGroup() throws IOException {
        logger.log(Level.INFO, "Starting cutting of " + rules.size() + " rules");
        List<Nfa> group = new ArrayList<>();
        List<String> groupRules = new ArrayList<>();
        List<String> rejected = new ArrayList<>();
        for (String rule: rules) {
            logger.info("Trying to add: " + rule);
            boolean failed = false;
            Nfa nfa = buildNfa(rule, Integer.MAX_VALUE);
            for (Nfa selected: group) {
                if (failed) {
                    break;
                }
//                boolean thompsonAlt = Main.runThompsonAlt(selected, nfa);
                boolean checker = Main.runChecker(selected, nfa);
//                if (thompsonAlt != checker) {
//                    throw new RuntimeException("WRONG ANSWER");
//                }

                if (!checker) {
                    failed = true;
                }
            }
            if (!failed) {
                nfa.close(1 + group.size());
                group.add(nfa);
                groupRules.add(rule);
                logger.info("Ok");
            } else {
                rejected.add(rule);
                logger.info("Rejected");
            }
        }

        groupsCounter++;
        String groupHeader = "Group #" + groupsCounter;

        groupsWriter.write(groupHeader + "\n");
        groupsWriter.write(String.join("\n", groupRules));
        groupsWriter.write("\n");
        groupsWriter.flush();

        System.out.println(groupHeader);
        processGroup(groupRules, group);
        System.out.println();
        System.out.flush();
        rules = rejected;
    }

    private static void processGroup(List<String> rules, List<Nfa> nfas) {
        System.out.println("Total " + rules.size() + " rules");
        if(true) {
            return;
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

        System.out.println();
        System.out.flush();
    }

}
