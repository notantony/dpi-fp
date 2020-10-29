package automaton.algo;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.logging.Logger;

public class HopcroftMinimizer {
    private Queue<Pair<Integer, Character>> queue = new ArrayDeque<>();
    private List<Node> nodes;
    private int[] classAssigned;
    private ArrayList<Integer>[][] inv;
    private ArrayList<Set<Integer>> partition;

    private int __debugPartitionNextMonitorSize = 512;

    private void preCalc(Dfa dfa) {
        HashMap<Node, Integer> back = new HashMap<>();
        nodes = new ArrayList<>(dfa.allNodes());

        for (int i = 0; i < nodes.size(); i++) {
            back.put(nodes.get(i), i);
        }

        inv = new ArrayList[nodes.size()][Transitions.MAX_CHAR + 1];
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j <= Transitions.MAX_CHAR; j++) {
                inv[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            int finalI = i;
            nodes.get(i).getEdges().forEach((c, target) -> {
                inv[back.get(target)][c].add(finalI);
            });
        }

        int maxTerm = nodes.stream().map(Node::getTerminal).map(terminals -> {
            if (terminals.size() > 1) {
                throw new AlgoException("Node has more than one terminal: " + terminals);
            } else if (terminals.size() == 0) {
                return 0;
            }
            return terminals.get(0);
        }).reduce(Integer::max).orElse(0);
        maxTerm += 2;

        partition = new ArrayList<>();
        for (int i = 0; i < maxTerm; i++) {
            partition.add(new HashSet<>());
        }
        classAssigned = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            List<Integer> terminals = nodes.get(i).getTerminal();
            int targetClass;
            if (terminals.size() == 1) {
                targetClass = terminals.get(0);
            } else {
                targetClass = maxTerm - 1;
            }
            partition.get(targetClass).add(i);
            classAssigned[i] = targetClass;
        }

        for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
            for (int i = 0; i < maxTerm; i++) {
                queue.add(new Pair<>(i, c));
            }
        }
    }


    public Dfa run(Dfa dfa) {
        HashMap<Set<Integer>, Integer> newTerminals = new HashMap<>();
        dfa.allNodes().stream()
                .map(node -> new HashSet<>(node.getTerminal()))
                .distinct()
                .forEach(terms -> newTerminals.put(terms, newTerminals.size() + 1));
        dfa.allNodes().forEach(node -> {
            node.setTerminal(Collections.singletonList(newTerminals.get(new HashSet<>(node.getTerminal()))));
        });

        preCalc(dfa);

        while (!queue.isEmpty()) {
            Pair<Integer, Character> splitterPair = queue.poll();
            Map<Integer, List<Integer>> involved = new HashMap<>();
            char splitter = splitterPair.getSecond();
            for (Integer classMember : partition.get(splitterPair.getFirst())) {
                for (int partitionMember : inv[classMember][splitter]) {
                    int i = classAssigned[partitionMember];
                    involved.putIfAbsent(i, new ArrayList<>());
                    involved.get(i).add(partitionMember);
                }
            }
            involved.forEach((i, members) -> {
                if (members.size() < partition.get(i).size()) {
                    partition.add(new HashSet<>());
                    if (partition.size() >= __debugPartitionNextMonitorSize) {
                        Logger.getGlobal().info("Partition size exceeded " + __debugPartitionNextMonitorSize);
                        __debugPartitionNextMonitorSize *= 2;
                    }
                    int j = partition.size() - 1;
                    for (int member : members) {
                        partition.get(i).remove(member);
                        partition.get(j).add(member);
                    }
                    if (partition.get(j).size() > partition.get(i).size()) {
                        Set<Integer> tmp = partition.get(i);
                        partition.set(i, partition.get(j));
                        partition.set(j, tmp);
                    }
                    for (int classMember : partition.get(j)) {
                        classAssigned[classMember] = j;
                    }
                    for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                        queue.add(new Pair<>(j, c));
                    }
                }
            });
        }

        Map<Set<Integer>, Node> bijection = new HashMap<>(partition.size());
        partition.forEach(set -> bijection.put(set, new Node()));
        Map<Node, Node> newNodes = new HashMap<>(partition.size());
        partition.forEach(set -> set.forEach(node -> newNodes.put(nodes.get(node), bijection.get(set))));

        for (Node node: nodes) {
            node.getEdges().forEach((c, target) -> newNodes.get(node).addEdge(c, newNodes.get(target)));
            newNodes.get(node).setTerminal(node.getTerminal());
        }

        return new Dfa(newNodes.get(dfa.getStart()));
    }
}
