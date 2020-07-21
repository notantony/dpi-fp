package main;

import automaton.dfa.Dfa;
import automaton.nfa.Nfa;

import static main.Main.buildDfa;
import static main.Main.buildNfa;

public class MainSingle {
    public static void main(String[] args) {
        String regex = "/^\\s*?[^(ID)]\\d/R";
        String string = "acb";
//        Nfa nfa = buildNfa(regex);
//        System.out.println(nfa.test(string));
        Dfa dfa = buildDfa(regex);
        System.out.println(dfa.testAny(string));
        System.out.println(dfa.nodesCount());
    }
}
