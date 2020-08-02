package automaton.algo;

import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class ThompsonConverterOld {
    private final Queue<Set<State>> queue = new ArrayDeque<>();
    private final HashMap<Set<State>, Node> bijection = new HashMap<>();

    public Node createNode(Set<State> states) {
        Node node = new Node();
        bijection.put(states, node);
        queue.add(states);
        return node;
    }

    public Dfa run(Nfa nfa) {
        Set<State> startSet = State.traverseEpsilonsSafe(Collections.singletonList(nfa.getStart()));
        Node newStart = createNode(startSet);

        while (!queue.isEmpty()) {
            Set<State> states = queue.poll();
            Node curNode = bijection.get(states);
            for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                Set<State> newStates = new HashSet<>();
                for (State state : states) {
                    char finalC = c;
                    state.getEdges().stream()
                            .filter(edgePair -> !(edgePair.getFirst() instanceof EpsilonTransition) &&
                                    edgePair.getFirst().test(finalC))
                            .map(Pair::getSecond).forEach(newStates::add);
                }
                if (!newStates.isEmpty()) {
                    newStates = State.traverseEpsilonsSafe(newStates);
                    Node newNode;
                    if (!bijection.containsKey(newStates)) { // TODO: empty newStates
                        newNode = createNode(newStates);
                    } else {
                        newNode = bijection.get(newStates);
                    }
                    curNode.addEdge(c, newNode);
                }
            }
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
