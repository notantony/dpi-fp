package main;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.algo.independence.IndependenceChecker;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;

public class MainSingle {

    public static void main(String[] args) {
//        String regex = "/qwe/";
//        String string = "abdqwe";

//        String a = "2 1\n" +
//                "3 2\n" +
//                "5 2\n" +
//                "0 1 a\n" +
//                "1 2 abc\n" +
//                "2 2 b\n" +
//                "3 4 ab\n" +
//                "3 5 ab\n" +
//                "1 3 c\n";
//        Dfa dfa = Dfa.parseDfa(a);

        String a = "/(^Contact\\x3A\\s+[^\\r\\n\\x3C]*\\x3C[^\\r\\n\\x3E]*?[\\x20\\x09]).*/Hsmi";
        Dfa dfa = Main.buildDfa(a);

        new RecursiveCompressor().compress(dfa);
        System.out.println(dfa.nodesCount());

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
