package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.dfa.Dfa;
import main.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleDfaRun {
    public static void main(String[] args) throws IOException {
        Dfa dfa = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./input/single/single_dfa.txt")));
        new RecursiveCompressor().compress(dfa);
        System.out.println(dfa.nodesCount());

        Dfa dfa2 = Dfa.parseDfa(Files.newBufferedReader(Paths.get("./input/single/single_dfa.txt")));
        Main.compress(dfa2);
        System.out.println(dfa2.nodesCount());
    }
}
