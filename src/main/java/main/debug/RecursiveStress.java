package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.dfa.Dfa;
import automaton.dfa.DfaGenerator;

public class RecursiveStress {
    public static void main(String[] args) {
        DfaGenerator generator = new DfaGenerator(131L);
        while (true) {
            Dfa dfa = generator.generateNext();
            try {
                System.out.println("===");
                dfa.print();
                new RecursiveCompressor().compress(dfa);
            } catch (AssertionError e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
