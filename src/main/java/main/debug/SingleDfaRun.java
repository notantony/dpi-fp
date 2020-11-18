package main.debug;

import automaton.algo.compressor.recursive.RecursiveCompressorStatic;
import automaton.dfa.Dfa;
import main.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleDfaRun {
    public static void main(String[] args) throws IOException {
//        Dfa dfa = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./input/single/single_dfa.txt")));
//        new RecursiveCompressorStatic().compress(dfa);
//        System.out.println(dfa.nodesCount());
//        int x = dfa.nodesCount();
//        Main.compress(dfa);
//        assert x == dfa.nodesCount();
//
//        Dfa dfa2 = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./input/single/single_dfa.txt")));
//        Main.compress(dfa2);
//        System.out.println(dfa2.nodesCount());
//        assert x <= dfa2.nodesCount();

        Dfa dfa2 = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./input/single/single_dfa.txt")));
        Main.compress(dfa2);
        int x = dfa2.nodesCount();
        Main.compress(dfa2);
        System.out.println(dfa2.nodesCount());
        assert x == dfa2.nodesCount();

    }
}
