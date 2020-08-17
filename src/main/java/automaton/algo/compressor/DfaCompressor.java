package automaton.algo.compressor;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DfaCompressor {
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

        ArrayList<Node> ordered = new ArrayList<>(nodes);
        boolean progress = true;
        while (progress) {
            progress = false;
            ordered = ordered.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
            for (int i = 0; i < ordered.size(); i++) {
                Node a = ordered.get(i);
                if (a == null || a.isTerminal()) {
                    continue;
                }
                Map<Character, Node> aEdges = a.getEdges();
                boolean found = false;
                for (int j = 0; !found && j < i; j++) {
                    Node b = ordered.get(j);
                    if (b == null || b.isTerminal()) {
                        continue;
                    }
                    Map<Character, Node> bEdges = b.getEdges();
                    boolean failed = false;
                    for (Map.Entry<Character, Node> entry : aEdges.entrySet()) {
                        char c = entry.getKey();
                        Node target = entry.getValue();
                        if (bEdges.containsKey(c) && bEdges.get(c) != target) {
                            failed = true;
                        }
                    }

                    if (!failed) {
                        found = true;
                        progress = true;
                        aEdges.forEach((c, target) -> {
                            if (target == a) {
                                b.addEdge(c, b);
                            } else {
                                mp.get(target).remove(new Pair<>(c, a));
                                mp.get(target).add(new Pair<>(c, b));
                                b.addEdge(c, target);
                            }
                        });
                        for (Pair<Character, Node> pair : mp.get(a)) {
                            pair.getSecond().addEdge(pair.getFirst(), b);
                        }
                        mp.get(b).addAll(mp.get(a).stream().map(pair -> {
                            if (pair.getSecond() == a) {
                                return new Pair<>(pair.getFirst(), b);
                            }
                            return pair;
                        }).collect(Collectors.toSet()));

                        //                    nodes.remove(a);
                        ordered.set(i, null);
                        if (dfa.getStart() == a) {
                            dfa.setStart(b);
                        }
                    }
                }
            }
        }
    }
}
