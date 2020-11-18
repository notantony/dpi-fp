package automaton.algo.compressor.heuristic;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class DfaCompressorOld1 {
    public void compress(Dfa dfa) {
        Map<Node, Set<Pair<Character, Node>>> mp = new HashMap<>();
        HashSet<Node> nodes = new HashSet<>(dfa.allNodes());
        for (Node node : nodes) {
            mp.put(node, new HashSet<>());
        }
        for (Node node : nodes) {
            node.getEdges().forEach((c, target) -> {
                mp.get(target).add(new Pair<>(c, node));
            });
        }
        boolean found = true;
        while (found) {
            found = false;
            List<Node> ordered = new ArrayList<>(nodes);
            for (int i = 0; !found && i < ordered.size(); i++) {
                for (int j = 0; !found && j < i; j++) {
                    Node a = ordered.get(i);
                    if (a.isTerminal()) {
                        continue;
                    }
                    Map<Character, Node> aEdges = a.getEdges();
                    Node b = ordered.get(j);
                    if (b.isTerminal()) {
                        continue;
                    }
                    Map<Character, Node> bEdges = b.getEdges();
                    boolean failed = false;
                    if (aEdges.size() >= bEdges.size()) {
                        Map<Character, Node> tmp = aEdges;
                        aEdges = bEdges;
                        bEdges = tmp;
                        Node tmp1 = a;
                        a = b;
                        b = tmp1;
                    }
                    for (Map.Entry<Character, Node> entry : aEdges.entrySet()) {
                        char c = entry.getKey();
                        Node target = entry.getValue();
                        if (bEdges.containsKey(c) && bEdges.get(c) != target) {
                            failed = true;
                        }
                    }

                    if (!failed) {
                        found = true;

                        Node finalA = a;
                        Node finalB = b;
                        aEdges.forEach((c, target) -> {
                            if (target == finalA) {
                                finalB.addEdge(c, finalB);
                            } else {
                                mp.get(target).remove(new Pair<>(c, finalA));
                                mp.get(target).add(new Pair<>(c, finalB));
                                finalB.addEdge(c, target);
                            }
                        });
                        for (Pair<Character, Node> pair : mp.get(a)) {
                            pair.getSecond().addEdge(pair.getFirst(), b);
                        }
                        mp.get(b).addAll(mp.get(a).stream().map(pair -> {
                            if (pair.getSecond() == finalA) {
                                return new Pair<>(pair.getFirst(), finalB);
                            }
                            return pair;
                        }).collect(Collectors.toSet()));

                        nodes.remove(a);
                        if (dfa.getStart() == a) {
                            dfa.setStart(b);
                        }
                    }
                }
            }
        }
    }
}
