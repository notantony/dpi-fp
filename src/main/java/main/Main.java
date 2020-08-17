package main;

import antlr.RegexLexer;
import antlr.RegexParser;
import automaton.algo.*;
import automaton.algo.compressor.DfaCompressor;
import automaton.algo.independence.IndependenceCheckerBfs;
import automaton.algo.independence.IndependenceCheckerDfs;
import automaton.algo.thompson.ThompsonConverter;
import automaton.algo.thompson.ThompsonModified;
import automaton.algo.thompson.ThompsonModifiedDfs;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import org.antlr.v4.runtime.*;
import parsing.ParsingError;
import parsing.RegexVisitorImpl;
import util.FutureList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    private static BufferedWriter writer;

    static {
        try {
            writer = Files.newBufferedWriter(Paths.get("./output/distinct.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader rulesReader = Files.newBufferedReader(Paths.get("./input/selected.txt"));
//        rulesReader.lines().forEach(Main::processUnion);
//        rulesReader.lines().forEach(Main::writeNodesCount);
        rulesReader.lines().forEach(Main::processBaseline);
//        rulesReader.lines().forEach(Main::processDistinct);
//        writer.write(ind.toString());

        writer.close();
    }

    private static int counter = 0;
    private static ArrayList<Nfa> nfas = new ArrayList<>();
    private static ArrayList<Nfa> nfasSingle = new ArrayList<>();
    private static ArrayList<Nfa> rejected = new ArrayList<>();
    private static ArrayList<Integer> sizes = new ArrayList<>();

    public static void pause() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> ind = new ArrayList<>();

    private static void modifyOnly(String rule) {
        counter++;
        System.out.println(counter);
        Nfa nfaSingle = buildNfa(rule);
        nfaSingle.close(counter);
        nfas.add(nfaSingle);

        Dfa modified;
        try {
            modified = new ThompsonModified().run(nfas);
        } catch (AlgoException e) {
            System.out.println("Rejected");
            nfas.remove(nfas.size() - 1);
            return;
        }
        ind.add(counter);
        System.out.println("Total rules: " + nfas.size());
        System.out.println(""
                + "ThompsonModified: " + modified.nodesCount()
        );
//        compress(modified);
//        System.out.println(""
//                + "Compressed: " + modified.nodesCount());
        System.out.println(ind);
    }

    private static void writeNodesCount(String rule) {
        try {
            int count = buildMinDfa(rule).nodesCount();
            writer.write(rule + "\n" + count + "\n");
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processBaseline(String rule) {
        counter++;
        System.out.println("Processing " + counter + " rules:");

        // Nfa:
        Nfa nfaCurrent = buildNfa(rule);
        nfaCurrent.close(counter);
        nfas.add(nfaCurrent);

        Nfa nfaCurrentSingle = buildNfa(rule);
        nfaCurrentSingle.close(1);
        nfasSingle.add(nfaCurrentSingle);

        // Dfa:
        Dfa dfaCurrentMin = minimizeHopcroft(convert(nfaCurrent));
        sizes.add(dfaCurrentMin.nodesCount());

//        if (counter < 562) {
//            return;
//        }

        System.out.println("Sum-of-single: " + sizes.stream().reduce(0, Integer::sum));

        Dfa modified = new ThompsonModified().run(nfas);
        System.out.println("ThompsonModified: " + modified.nodesCount());

        Dfa modifiedMin = minimizeHopcroft(modified);
        compress(modifiedMin);
        Dfa modifiedMinCompressedMin = minimizeHopcroft(modifiedMin);
        System.out.println("ThompsonModifiedHeuristic: " + modifiedMin.nodesCount()
                + "/" + modifiedMinCompressedMin.nodesCount());


        Dfa dfaSingleMin = minimizeHopcroft(convert(Nfa.union(nfasSingle)));
        System.out.println("Single-terminal-minimized: " + dfaSingleMin.nodesCount());

        Dfa dfaMin = minimizeHopcroft(convert(Nfa.union(nfas)));
        System.out.println("Minimized: " + dfaMin.nodesCount());
        System.out.println("Minimized-cut: " + (dfaMin.cutCount() + nfas.size()));

        System.out.println();
        System.out.flush();
    }

    private static void processDistinct(String rule) {
        if (counter % 100 == 0) {
            System.out.println(ind);
        }
        counter++;
        System.out.println("Trying to add: " + counter);

        Nfa nfa = buildNfa(rule);
        nfa.close(counter);

        try {
            nfas.forEach(other -> new ThompsonModified().run(FutureList.of(nfa, other)));
//            new ThompsonModified().run(nfas);
        } catch (AlgoException e) {
            System.out.println("Rejected");
            return;
        }
        nfas.add(nfa);
        System.out.println("Success");
        ind.add(counter);
    }

    private static void processUnion(String rule) {
        counter++;
        System.out.println(counter);
        Nfa nfaSingle = buildNfa(rule);
        nfaSingle.close(counter);
        nfas.add(nfaSingle);

//        if (nfas.size() != 2) {
//            return;
//        }

        nfaSingle = buildNfa(rule);
        nfasSingle.add(nfaSingle);

        Dfa dfaSingle = convert(nfaSingle);
        Dfa dfa = new ThompsonConverter().run(Nfa.union(nfas));
        Dfa min, min2;
        try {
            min = minimize(dfa);
            min2 = minimizeHopcroft(dfa);
        } catch (AlgoException e) {
            System.out.println("Rejected");
            nfas.remove(nfas.size() - 1);
            return;
        }

//        dfa = new ThompsonConverter().run(Nfa.union(nfasSingleTerm));
//        Dfa minSingleTerm;
//        try {
//            minSingleTerm = minimize(dfa);
//        } catch (AlgoException e) {
//            System.out.println("Rejected2");
//            nfasSingleTerm.remove(nfasSingleTerm.size() - 1);
//            return;
//        }

        Dfa modified = new ThompsonModified().run(nfas);
        sizes.add(minimize(dfaSingle).nodesCount());
        System.out.println("Total " + nfas.size() + " nodes");
        System.out.println(""
                + "Sum of single: " + sizes.stream().reduce(0, Integer::sum) + "\n"
                + "Merged: " + dfa.nodesCount() + "\n"
                + "Minimized: " + min.nodesCount() + "\n"
                + "MinimizedHopcroft: " + min2.nodesCount() + "\n"
//                + "Single-terminal-minimized: " + minSingleTerm.nodesCount() + "\n"
                + "Optimized: " + min.cutCount() + "\n"
                + "ThompsonModified: " + modified.nodesCount()
        );
        modified = minimize(modified);
        compress(modified);
        System.out.println(""
                + "ThompsonModifiedHeuristic: " + modified.nodesCount());
        modified.print();

        System.out.flush();

//        if (counter == 20) {
//            pause();
//        }

//        pause();
    }

    private static void processRule(String rule) {

//        System.out.println("Processing:\n" + rule);
        try {
//            Nfa nfa = buildNfa(rule);
            Dfa dfa = buildDfa(rule);
            System.out.println(dfa.nodesCount());


        } catch (ParsingError e) {
            e.printStackTrace();
            System.out.println(rule);
        }
    }


    public static Nfa buildNfa(String rule) {
        ANTLRErrorListener parsingErrorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
                throw new ParsingError("Parsing error: " + msg, e);
            }
        };
        RegexLexer lexer = new RegexLexer(CharStreams.fromString(rule));
        lexer.removeErrorListeners();
        lexer.addErrorListener(parsingErrorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RegexParser parser = new RegexParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(parsingErrorListener);

        RegexParser.StartContext start = parser.start();
        RegexVisitorImpl visitor = new RegexVisitorImpl();
        return visitor.visit(start);
    }

    public static Nfa buildNfa(String rule, int terminal) {
        Nfa nfa = buildNfa(rule);
        nfa.close(terminal);
        return nfa;
    }


        public static Dfa buildDfa(String rule) {
        return new ThompsonConverter().run(buildNfa(rule));
    }

    public static Dfa buildMinDfa(String rule) {
        return minimizeHopcroft(buildDfa(rule));
    }

    public static Dfa minimize(Dfa dfa) {
        return new DfaMinimizer().run(dfa);
    }

    public static Dfa minimizeHopcroft(Dfa dfa) {
        return new HopcroftMinimizer().run(dfa);
    }

    public static Dfa convert(Nfa nfa) {
        return new ThompsonConverter().run(nfa);
    }

    public static void compress(Dfa dfa) {
        new DfaCompressor().compress(dfa);
    }

    public static <T> void timing(Consumer<T> consumer, T arg) {
        long pre = System.currentTimeMillis();
        consumer.accept(arg);
        long post = System.currentTimeMillis();
        System.out.println(post - pre);
    }

    public static List<String> readAllRules() throws IOException {
        String inputPath = "./input/selected.txt";
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(inputPath));
        return rulesReader.lines().collect(Collectors.toList());
    }

    public static boolean runChecker(Nfa a, Nfa b) {
        return new IndependenceCheckerDfs().areIndependent(a, b);
    }

    public static boolean runCheckerAlt(Nfa a, Nfa b) {
        return new IndependenceCheckerBfs().areIndependent(a, b);
    }

    public static boolean runThompson(Nfa a, Nfa b) {
        try {
            new ThompsonModified().run(FutureList.of(a, b));
        } catch (AlgoException e) {
            return false;
        }
        return true;
    }

    public static boolean runThompsonAlt(Nfa a, Nfa b) {
        try {
            new ThompsonModifiedDfs().run(FutureList.of(a, b));
        } catch (AlgoException e) {
            return false;
        }
        return true;
    }
}
