package main.debug;

import automaton.algo.compressor.recursive.RecursiveCompressor;
import automaton.algo.thompson.ThompsonModified;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import main.io.Input;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SingleRegexRun {
    public static void main(String[] args) throws IOException {
        List<Nfa> nfas = Input.readRules("./input/single/regex.txt").stream()
                .map(Main::buildNfa)
                .collect(Collectors.toList());

        int i = 1;
        for (Nfa nfa : nfas) {
            nfa.close(i++);
        }

        Dfa dfa = new ThompsonModified().run(nfas);
        new RecursiveCompressor().compress(dfa);
        System.out.println(dfa.nodesCount());

        new RecursiveCompressor().compress(dfa);
        System.out.println(dfa.nodesCount());

        dfa.print();

        Main.compress(dfa);
        System.out.println(dfa.nodesCount());

        dfa.print();

//        Dfa result2 = new ThompsonModified().run(nfas);
//        Main.compress(result2);
//        System.out.println(result2.nodesCount());
    }
}
