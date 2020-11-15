package automaton.algo.compressor.validator;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class HeuristicValidator {
    public boolean test(Dfa dfa) {
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
        ordered = ordered.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < ordered.size(); i++) {
            Node a = ordered.get(i);
            if (a == null || a.isTerminal()) {
                continue;
            }
            Map<Character, Node> aEdges = a.getEdges();
            for (int j = 0; j < i; j++) {
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
                    return false;
                }
            }
        }
        return true;
    }
}
