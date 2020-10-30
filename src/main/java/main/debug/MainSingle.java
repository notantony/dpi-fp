package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import main.io.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MainSingle {

    public static void main(String[] args) throws IOException {
//        String regex = "/qwe/";
//        String string = "abdqwe";

//        String a = "6 1\n" +
//                "7 2\n" +
//                "8 3\n" +
//                "0 1 ab\n" +
//                "1 2 ac\n" +
//                "1 3 b\n" +
//                "2 5 b\n" +
//                "2 4 a\n" +
//                "3 4 ac\n" +
//                "4 8 b\n" +
//                "5 5 c\n" +
//                "5 6 a\n" +
//                "5 7 b\n";
//        String a = "3 3\n" +
//                "1 1\n" +
//                "4 4\n" +
//                "0 1 a\n" +
//                "0 2 b\n" +
//                "2 4 b\n" +
//                "0 3 c\n";
//        Dfa dfa = Dfa.parseDfa(a);

//        String a = "/(^Contact\\x3A\\s+[^\\r\\n\\x3C]*\\x3C[^\\r\\n\\x3E]*?[\\x20\\x09]).*/Hsmi";
//        Dfa dfa = Main.buildDfa(a);




//        String a = "1\n" +
//                "0 1 q\n" +
//                "1 1 c";
//        String b = "3\n" +
//                "0 1 a\n" +
//                "1 2 c\n" +
//                "2 2 a\n" +
//                "2 3 c";
//        Nfa aNfa = Nfa.parseNfa(a);
//        Nfa bNfa = Nfa.parseNfa(b);
//        System.out.println(new IndependenceChecker().areIndependent(aNfa, bNfa));


//        Dfa dfa = buildMinDfa(regex);
//        System.out.println(dfa.test(string));

//        Nfa nfa1 = buildNfa("/abcqd/");
//        nfa1.close(1);
//        Nfa nfa2 = buildNfa("/abcdc/");
//        nfa2.close(2);
//        Nfa nfa3 = buildNfa("/abcde/");
//        nfa3.close(3);
//        Nfa nfa = Nfa.union(List.of(nfa1, nfa2, nfa3));
//        Dfa dfa = convert(nfa);
//        System.out.println(dfa.testAny(string));
//        System.out.println(dfa.nodesCount());
//        System.out.println(dfa.cutCount());
    }
}
