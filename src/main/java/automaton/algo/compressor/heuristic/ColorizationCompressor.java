package automaton.algo.compressor.heuristic;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import intgraph.ColorizatorSimple;
import intgraph.IntGraph;
import main.debug.HeuristicColors;
import util.IntMonitor;
import util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorizationCompressor {
    ArrayList<Node> ordered;
    HashMap<Node, Integer> index;

    IntMonitor __sizeMonitor = new IntMonitor("Nodes remaining", 50, IntMonitor.Mode.LINEAR);

    public void compress(Dfa dfa) {
        boolean progress = true;
        while (progress) {
            progress = false;
            IntGraph graph = buildGraph(dfa);
            if (__sizeMonitor.update(index.size())) {
                Utils.writeTo("./output/graph/checkpoints/colors/g" + HeuristicColors.groupId1 + "/at" + index.size() + ".txt",
                        dfa.print(Dfa.PrintingMode.SERIALIZE));
            }
//            System.err.println("Nodes remaining: " + ordered.size());
            List<List<Integer>> colors = new ColorizatorSimple().colorize(graph);
//            System.err.println("Colors found: " + ordered.size());
            if (colors.size() != dfa.nodesCount()) {
                progress = true;
                Map<Integer, Node> mapping = new HashMap<>();
                for (List<Integer> colorEntries : colors) {
                    Node colorNode = new Node();
                    boolean anyTerminal = false;
                    for (Integer entry : colorEntries) {
                        mapping.put(entry, colorNode);
                        Node oldNode = ordered.get(entry);
                        if (oldNode.isTerminal()) {
                            colorNode.setTerminal(oldNode.getTerminal());
                            assert !anyTerminal;
                            anyTerminal = true;
                        }
                    }
                }
                for (List<Integer> colorEntries : colors) {
                    HashMap<Character, Node> newEdges = new HashMap<>();
                    colorEntries.forEach(entryId -> {
                        ordered.get(entryId).getEdges().forEach((c, node) -> {
                            Node newNode = mapping.get(index.get(node));
                            Node __replaced = newEdges.put(c, newNode);
                            assert __replaced == null || __replaced == newNode;
                        });
                    });
                    mapping.get(colorEntries.get(0)).setEdges(newEdges);
                }
                dfa.setStart(mapping.get(index.get(dfa.getStart())));
            }
        }
    }

    public IntGraph buildGraph(Dfa dfa) {
        index = new HashMap<>();
        ordered = new ArrayList<>(dfa.allNodes());
        for (int i = 0; i < ordered.size(); i++) {
            index.put(ordered.get(i), i);
        }
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
