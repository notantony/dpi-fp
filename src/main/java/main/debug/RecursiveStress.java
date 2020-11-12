package main.debug;

import automaton.algo.compressor.RecursiveCompressor;
import automaton.algo.compressor.RecursiveCompressorDynamic;
import automaton.algo.compressor.RecursiveCompressorStatic;
import automaton.dfa.Dfa;
import automaton.dfa.DfaGenerator;
import main.Main;

public class RecursiveStress {
    public static void main(String[] args) {
        DfaGenerator generator = new DfaGenerator(132L);
        while (true) {
            Dfa dfa = generator.generateNext();
            try {
                System.out.println("===");
                dfa.print();
                new RecursiveCompressorStatic().compress(dfa);

                int sz = dfa.nodesCount();
                Main.compress(dfa);
                assert sz == dfa.nodesCount() : sz + " " + dfa.nodesCount();
            } catch (AssertionError e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
