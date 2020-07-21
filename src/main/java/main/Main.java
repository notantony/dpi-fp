package main;

import antlr.RegexLexer;
import antlr.RegexParser;
import automaton.algo.ThompsonConverter;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import org.antlr.v4.runtime.*;
import parsing.ParsingError;
import parsing.RegexVisitorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        char x = (char) ((byte) 'a');// Byte.parseByte("52", 16);
//        byte x = (byte) ((char) 255);// Byte.parseByte("52", 16);
//        char x = (char) Integer.parseInt("3a", 16);// (Integer.valueOf("3a"));
//        char x = Character.codePointOf("\n");
//        System.out.println((char) 256);
//        Character.

        BufferedReader rulesReader = Files.newBufferedReader(Paths.get("./input/filtered.txt"));
        rulesReader.lines().forEach(Main::processRule);

//        new RegexTest("#318", "/abc/i", "ABC", 0),
//        new RegexTest("#319", "/abc/i", "XBC", 1),
//        new RegexTest("#320", "/abc/i", "AXC", 1),
//        new RegexTest("#321", "/abc/i", "ABX", 1),
//        new RegexTest("#322", "/abc/i", "XABCY", 0),
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

    public static Dfa buildDfa(String rule) {
        return new ThompsonConverter().run(buildNfa(rule));
    }

    private static void processRule(String rule) {
//        System.out.println("Processing:\n" + rule);
        try {
//            Nfa nfa = buildNfa(rule);
            Dfa dfa = buildDfa(rule);
            System.out.println(dfa.nodesCount());

//            Thread thread = new Thread(() -> {
//                Dfa dfa = buildDfa(rule);
//            });
//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            Future<?> future = executor.submit(thread);
//            try {
//                future.get(10, TimeUnit.SECONDS);
//            } catch (TimeoutException e) {
//                System.out.println(rule);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
        } catch (ParsingError e) {
            e.printStackTrace();
            System.out.println(rule);
        }
    }
}
