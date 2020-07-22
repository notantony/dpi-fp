package automaton.algo;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;
import util.Triple;

import java.util.*;

public class DfaMinimizer {
    Set<Set<Node>> partition = new HashSet<>();
    Queue<Pair<Set<Node>, Character>> queue = new ArrayDeque<>();

    private void insertAll(Set<Node> set) {
        for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
            queue.add(new Pair<>(set, c));
            partition.add(set);
        }
    }

    public Dfa run(Dfa dfa) {
        Collection<Node> nodes = dfa.allNodes();
        Map<Integer, Set<Node>> startClasses = new HashMap<>();
        for (Node node: nodes) {
            List<Integer> terminals = node.getTerminal();
            Integer terminal;
            if (terminals.size() > 1) {
                throw new AlgoException("Node has more than one terminal: " + terminals);
            } else if (terminals.size() == 0) {
                terminal = null;
            } else {
                terminal = terminals.get(0);
            }
            startClasses.putIfAbsent(terminal, new HashSet<>());
            startClasses.get(terminal).add(node);
        }
        for (Set<Node> startClass: startClasses.values()) {
            insertAll(startClass);
        }
        while (!queue.isEmpty()) {
            Pair<Set<Node>, Character> splitterPair = queue.poll();
            char splitter = splitterPair.getSecond();
            List<Triple<Set<Node>, Set<Node>, Set<Node>>> lateModify = new ArrayList<>();
            for (Set<Node> set: partition) {
                Set<Node> target = splitterPair.getFirst();
                Set<Node> succeeded = new HashSet<>(), failed = new HashSet<>();
                for (Node node: set) {
                    if (target.contains(node.getEdges().get(splitter))) {
                        succeeded.add(node);
                    } else {
                        failed.add(node);
                    }
                }
                if (!failed.isEmpty() && !succeeded.isEmpty()) {
                    lateModify.add(new Triple<>(set, failed, succeeded));
                }
            }
            for (Triple<Set<Node>, Set<Node>, Set<Node>> triple: lateModify) {
                partition.remove(triple.getA());
                insertAll(triple.getB());
                insertAll(triple.getC());
            }
        }
        Map<Set<Node>, Node> bijection = new HashMap<>();
        partition.forEach(set -> bijection.put(set, new Node()));
        Map<Node, Node> newNodes = new HashMap<>();
        partition.forEach(set -> set.forEach(node -> newNodes.put(node, bijection.get(set))));

        for (Node node: nodes) {
            node.getEdges().forEach((c, target) -> newNodes.get(node).addEdge(c, newNodes.get(target)));
            newNodes.get(node).setTerminal(node.getTerminal());
        }

        return new Dfa(newNodes.get(dfa.getStart()));
    }
}
