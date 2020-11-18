package automaton.algo.compressor.heuristic;

import automaton.dfa.Dfa;
import automaton.dfa.Node;

import java.util.*;

public class DfaCompressorMulti {
//    private int n;
//
//    public DfaCompressorMulti(int n) {
//        this.n = n;
//    }

    public void compress(Dfa dfa) {
        List<Node> nodes = new ArrayList<>(dfa.allNodes());
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < i; j++) {
                Node a = nodes.get(i);
                Node b = nodes.get(j);
                if (a == null || b == null || !a.isTerminal() || !b.isTerminal()) {
                    continue;
                }
                List<Integer> aTerminals = a.getTerminal();
                List<Integer> bTerminals = b.getTerminal();
                List<Integer> newTerminals = null;
                if ((aTerminals.size() >= bTerminals.size()) && aTerminals.containsAll(bTerminals)) {
                    newTerminals = aTerminals;
                } else if (aTerminals.size() < bTerminals.size() && bTerminals.containsAll(aTerminals)) {
                    newTerminals = bTerminals;
                }
                if (newTerminals != null) {
//                    System.err.println("Optimized: " + (newTerminals == aTerminals ? bTerminals : aTerminals)
//                            + " -> " + newTerminals);
                    Node newNode = new Node();
                    newNode.setTerminal(newTerminals);
                    a.getEdges().forEach((c, target) -> {
                        newNode.addEdge(c, (target == a || target == b) ? newNode : target);
                    });
                    b.getEdges().forEach((c, target) -> {
                        newNode.addEdge(c, (target == a || target == b) ? newNode : target);
                    });
                    for (Node node : nodes) {
                        if (node == null || node == a || node == b) {
                            continue;
                        }
                        Map<Character, Node> updated = new HashMap<>();
                        node.getEdges().forEach((c, target) -> {
                            if (target == a || target == b) {
                                updated.put(c, newNode);
                            } else {
                                updated.put(c, target);
                            }
                        });
                        node.setEdges(updated);
                    }
                    if (dfa.getStart() == a || dfa.getStart() == b) {
                        dfa.setStart(newNode);
                    }
                    nodes.set(i, null);
                    nodes.set(j, null);
                    nodes.add(newNode);
                }
            }
        }
        new DfaCompressor().compress(dfa);
    }
}
