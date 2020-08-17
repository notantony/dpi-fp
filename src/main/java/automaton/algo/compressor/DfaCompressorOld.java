package automaton.algo.compressor;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DfaCompressorOld {
    public void compress(Dfa dfa) {
        boolean found = true;
        while (found) {
            found = false;
            List<Node> nodes = new ArrayList<>(dfa.allNodes());
            for (int i = 0; !found && i < nodes.size(); i++) {
                Node a = nodes.get(i);
                if (a.isTerminal()) {
                    continue;
                }
                Map<Character, Node> aEdges = a.getEdges();
                for (int j = 0; !found && j < i; j++) {
                    Node b = nodes.get(j);
                    if (b.isTerminal()) {
                        continue;
                    }
                    Map<Character, Node> bEdges = b.getEdges();
                    boolean failed = false;
                    for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                        if (aEdges.containsKey(c) && bEdges.containsKey(c) &&
                                aEdges.get(c) != bEdges.get(c)) {
                            failed = true;
                        }
                    }
                    if (!failed) {
                        System.out.println("fix: " + a.hashCode() + " " + b.hashCode());
                        found = true;
                        Node newNode = new Node();
                        aEdges.forEach((c, target) -> {
                            newNode.addEdge(c, (target == a || target == b) ? newNode : target);
                        });
                        bEdges.forEach((c, target) -> {
                            newNode.addEdge(c, (target == a || target == b) ? newNode : target);
                        });
                        for (Node node: nodes) {
                            if (node == a || node == b) {
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
                    }
                }
            }
        }
    }
}
