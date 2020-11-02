package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.dfa.Dfa;
import automaton.dfa.DfaGenerator;
import main.Main;

public class RecursiveStress {
    public static void main(String[] args) {
        DfaGenerator generator = new DfaGenerator(131L);
        while (true) {
            Dfa dfa = generator.generateNext();
            try {
                System.out.println("===");
                dfa.print();
                new RecursiveCompressor().compress(dfa);

                int sz = dfa.nodesCount();
                Main.compress(dfa);
                assert sz == dfa.nodesCount();
            } catch (AssertionError e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
