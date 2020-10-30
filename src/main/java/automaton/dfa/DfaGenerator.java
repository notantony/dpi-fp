package automaton.dfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DfaGenerator {
    private final int MAX_NODES = 20;
    private final double EDGE_PROB = 0.5;
    private final char ALPHA_BORDER = 'c';
    private final double TERMINAL_PROB = 0.33;
    private Random random;

    public DfaGenerator() {
        random = new Random(131);
    }

    public DfaGenerator(Long seed) {
        random = new Random(seed);
    }

    public Dfa generateNext() {
        int nNodes = random.nextInt(MAX_NODES) + 2;
        ArrayList<Node> nodes = new ArrayList<>(nNodes);
        for (int i = 0; i < nNodes; i++) {
            nodes.add(new Node());
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

        return new Dfa(nodes.get(0));
    }
}
