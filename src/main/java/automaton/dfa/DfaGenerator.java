package automaton.dfa;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DfaGenerator {
//    private final int MAX_NODES = 20;
//    private final double EDGE_PROB = 0.2;
//    private final char ALPHA_BORDER = 'c';
//    private final double TERMINAL_PROB = 0.33;

    private final int MAX_NODES = 20;
    private final double EDGE_PROB = 0.4;
    private final char ALPHA_BORDER = 'f';
    private final double TERMINAL_PROB = 0.33;

    private Random random;
    private int nNodes;
    private ArrayList<Node> nodes;
    private HashMap<Node, Integer> nodeIds;

    private int nRuns = 0;

    public DfaGenerator() {
        random = new Random(131);
    }

    public DfaGenerator(Long seed) {
        random = new Random(seed);
    }

    public Dfa generateNext() {
        nRuns++;
        Logger.getGlobal().info("Generation #" + nRuns);
        nNodes = random.nextInt(MAX_NODES) + 2;
        nodes = new ArrayList<>(nNodes);
        nodeIds = new HashMap<>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            Node node = new Node();
            nodes.add(node);
            nodeIds.put(node, i);
        }

        for (int i = 0; i < nNodes; i++) {
            Node node = nodes.get(i);
            if (i > 0 && random.nextDouble() <= TERMINAL_PROB) {
                node.setTerminal(Collections.singletonList(i));
            } else {
                for (char c = 'a'; c <= ALPHA_BORDER; c++) {
                    if (random.nextDouble() <= EDGE_PROB) {
                        node.addEdge(c, nodes.get(random.nextInt(nNodes - 1)));
                    }
                }
            }
        }

        return shrinkStage1(new Dfa(nodes.get(0)));
    }

    private Dfa shrinkStage1(Dfa dfa) {
        if (nRuns == 48) {
            nRuns = 100;
        }
        List<Integer> mergeTargets = new ArrayList<>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            Node node = nodes.get(i);
            List<Node> collectedTerminals = new Dfa(node).allNodes().stream()
                    .filter(Node::isTerminal)
                    .collect(Collectors.toList());
            if (collectedTerminals.size() == 0) {
                mergeTargets.add(-1);
            } else if (collectedTerminals.size() == 1) {
                mergeTargets.add(collectedTerminals.get(0).getTerminal().get(0));
            } else {
                mergeTargets.add(null);
            }
        }
        for (int i = 0; i < nNodes; i++) {
            Integer targetTerminal = mergeTargets.get(i);
            if (targetTerminal == null) {
                Node node = nodes.get(i);
                node.setEdges(node.getEdges().entrySet().stream()
                        .map(entry -> {
                            Integer newTarget = mergeTargets.get(nodeIds.get(entry.getValue()));
                            if (newTarget != null) {
                                if (newTarget == -1) {
                                    return null;
                                }
                                entry.setValue(nodes.get(newTarget));
                            }
                            return entry;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            }
        }
        Integer newStartId = mergeTargets.get(nodeIds.get(dfa.getStart()));
        if (newStartId != null) {
            if (newStartId == -1) {
                return generateNext();
            }
            return new Dfa(nodes.get(newStartId));
        }
        return dfa;
    }

}
