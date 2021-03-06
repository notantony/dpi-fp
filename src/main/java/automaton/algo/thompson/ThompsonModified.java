package automaton.algo.thompson;

import automaton.algo.AlgoException;
import automaton.dfa.Dfa;
import automaton.dfa.Node;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThompsonModified {
    private final Queue<List<Set<State>>> queue = new ArrayDeque<>();
    private final HashMap<List<Set<State>>, Node> bijection = new HashMap<>();
    private ArrayList<Node> finals;

    public Node createNode(List<Set<State>> states) {
        Node node = new Node();
        bijection.put(states, node);
        queue.add(states);
        return node;
    }

    public Dfa run(List<Nfa> nfas) {
        finals = new ArrayList<>();
        for (int i = 0; i < nfas.size(); i++) {
            finals.add(null);
        }
        List<Set<State>> startSets = nfas.parallelStream()
                .map(Nfa::getStart)
                .map(state -> State.traverseEpsilonsSafe(Collections.singletonList(state)))
                .collect(Collectors.toList());

        Node newStart = createNode(startSets);

        while (!queue.isEmpty()) {
            List<Set<State>> statesList = queue.poll();
            Node curNode = bijection.get(statesList);
            for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                List<Set<State>> newStatesList = new ArrayList<>();
                for (int i = 0; i < nfas.size(); i++) {
                    Set<State> states = statesList.get(i);
                    char finalC = c;
                    Set<State> newStates = states.parallelStream()
                            .flatMap(state -> state.getEdges().stream())
                            .filter(edgePair -> !(edgePair.getFirst() instanceof EpsilonTransition) &&
                                    edgePair.getFirst().test(finalC))
                            .map(Pair::getSecond)
                            .collect(Collectors.toSet());
                    newStatesList.add(newStates);
                }
                List<Integer> nonEmptySets = new ArrayList<>();
                for (int i = 0; i < nfas.size(); i++) {
                    if (!newStatesList.get(i).isEmpty()) {
                        nonEmptySets.add(i);
                    }
                }

                if (nonEmptySets.size() > 1) {
                    newStatesList = newStatesList.parallelStream()
                            .map(State::traverseEpsilonsSafe)
                            .collect(Collectors.toList());
                    for (int i = 0; i < newStatesList.size(); i++) {
                        Set<State> states = newStatesList.get(i);
                        int finalI = i;
                        states.forEach(state -> {
                            if (state.isTerminal()) {
                                throw new AlgoException("Found terminal state which belongs to more than one Nfa: " + nonEmptySets.stream() + " terminal:" + state.getTerminal());
                            }
                        });
                    }
                    Node newNode;
                    if (!bijection.containsKey(newStatesList)) { // TODO: empty newStates
                        newNode = createNode(newStatesList);
                    } else {
                        newNode = bijection.get(newStatesList);
                    }
                    curNode.addEdge(c, newNode);
                } else if (nonEmptySets.size() == 1) {
                    int index = nonEmptySets.get(0);
                    Node newNode = finals.get(index);
                    if (newNode == null) {
                        newNode = new Node();
                        finals.set(index, newNode);
                    }
                    newNode.setTerminal(Collections.singletonList(index));
                    curNode.addEdge(c, newNode);
                }
            }
        }
        return new Dfa(newStart);
    }
}
