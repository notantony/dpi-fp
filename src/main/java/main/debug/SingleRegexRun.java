package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
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

        Dfa result = new ThompsonModified().run(nfas);
        new RecursiveCompressor().compress(result);
        System.out.println(result.nodesCount());

        Dfa result2 = new ThompsonModified().run(nfas);
        Main.compress(result2);
        System.out.println(result2.nodesCount());
    }
}
