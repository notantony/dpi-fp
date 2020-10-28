package automaton.algo.thompson;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import main.Main;
import util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThompsonConverter {
    private final Queue<Set<State>> queue = new ArrayDeque<>();
    private final Map<Set<State>, Node> bijection = new HashMap<>();

//    private void preCalc(Nfa nfa) {
//        Set<State> states = State.traverseEpsilonsSafe(Collections.singletonList(nfa.getStart()));
//        nfa.
//    }

    private int __debugNextBijectionMonitor = 512;
    private Node createNode(Set<State> states) {
        Node node = new Node();
        bijection.put(states, node);
        if (bijection.size() >= __debugNextBijectionMonitor) {
            Logger.getGlobal().info("Bijection size exceeded " + bijection.size());
            __debugNextBijectionMonitor *= 2;
        }
        queue.add(states);
        return node;
    }

    public Dfa run(Nfa nfa) {
        Set<State> startSet = State.traverseEpsilonsSafe(Collections.singletonList(nfa.getStart()));
        Node newStart = createNode(startSet);

        while (!queue.isEmpty()) {
            Set<State> states = queue.poll();
            Node curNode = bijection.get(states);
            IntStream.range(0, Transitions.MAX_CHAR + 1).forEach(c -> {
//                Set<State> newStates = ConcurrentHashMap.newKeySet();
                char finalC = (char) c;
                Set<State> newStates = states.parallelStream()
                        .flatMap(state -> state.getEdges().stream())
                        .filter(edgePair -> !(edgePair.getFirst() instanceof EpsilonTransition) &&
                                edgePair.getFirst().test(finalC))
                        .map(Pair::getSecond)
                        .collect(Collectors.toSet());
                if (!newStates.isEmpty()) {
                    Set<State> newStatesTraversed = State.traverseEpsilonsSafe(newStates);
                    Node newNode;
                    if (!bijection.containsKey(newStatesTraversed)) {
                        newNode = createNode(newStatesTraversed);
                    } else {
                        newNode = bijection.get(newStatesTraversed);
                    }
                    curNode.addEdge(finalC, newNode);
                }
            });
        }
        bijection.forEach((key, value) -> {
            List<Integer> terminals = key.stream()
                    .filter(State::isTerminal)
                    .map(State::getTerminal)
                    .distinct()
                    .collect(Collectors.toList());
            value.setTerminal(terminals);
        });
        return new Dfa(newStart);
    }
}
