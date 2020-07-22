package main;

import automaton.dfa.Dfa;
import automaton.nfa.Nfa;

import java.util.List;

import static main.Main.*;

public class MainSingle {

    public static void main(String[] args) {
        String regex = "/^(abc|cde).*/";
        String string = "acb";
        Nfa nfa1 = buildNfa("/abcqd/");
        nfa1.close(1);
        Nfa nfa2 = buildNfa("/abcdc/");
        nfa2.close(2);
        Nfa nfa3 = buildNfa("/abcde/");
        nfa3.close(3);
        Nfa nfa = Nfa.union(List.of(nfa1, nfa2, nfa3));
//        System.out.println(nfa.test(string));
        Dfa dfa = convert(nfa);
        System.out.println(dfa.testAny(string));
        System.out.println(dfa.nodesCount());
        System.out.println(dfa.cutCount());
    }
}