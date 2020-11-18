package automaton.algo.compressor.heuristic;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import intgraph.IntGraph;

import java.util.*;
import java.util.stream.Collectors;

public class HeuristicGraphBuilder {
    public IntGraph buildGraph(Dfa dfa) {
        ArrayList<Node> ordered = new ArrayList<>(dfa.allNodes());
        IntGraph graph = new IntGraph(ordered.size());
        for (int i = 0; i < ordered.size(); i++) {
            Node a = ordered.get(i);
            Map<Character, Node> aEdges = a.getEdges();
            for (int j = 0; j < i; j++) {
                Node b = ordered.get(j);
                Map<Character, Node> bEdges = b.getEdges();
                boolean failed = false;

                if (a.isTerminal() || b.isTerminal()) {
                    failed = true;
                } else {
                    for (Map.Entry<Character, Node> entry : aEdges.entrySet()) {
                        char c = entry.getKey();
                        Node target = entry.getValue();
                        if (bEdges.containsKey(c) && bEdges.get(c) != target) {
                            failed = true;
                        }
                    }
                }

                if (failed) {
                    graph.addEdge2(i, j);
                }
            }
        }
        return graph;
    }
}
