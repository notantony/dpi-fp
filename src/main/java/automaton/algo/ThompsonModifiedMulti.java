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

public class ThompsonModifiedMulti {
    private final Queue<List<Set<State>>> queue = new ArrayDeque<>();
    private final HashMap<List<Set<State>>, Node> bijection = new HashMap<>();
    private Map<Set<Integer>, Node> finals;
    private int n;

    public ThompsonModifiedMulti(int n) {
        this.n = n;
    }

    private Node createNode(List<Set<State>> states) {
        Node node = new Node();
        bijection.put(states, node);
        queue.add(states);
        return node;
    }

    public Dfa run(List<Nfa> nfas) {
        finals = new HashMap<>();
        List<Set<State>> startSets = nfas.stream()
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

                if (nonEmptySets.size() > n) {
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
                    if (!bijection.containsKey(newStatesList)) {
                        newNode = createNode(newStatesList);
                    } else {
                        newNode = bijection.get(newStatesList);
                    }
                    curNode.addEdge(c, newNode);
                } else { // TODO: not safe
                    Set<Integer> index = new HashSet<>(nonEmptySets);
                    Node newNode = finals.get(index);
                    if (newNode == null) {
                        newNode = new Node();
                        finals.put(index, newNode);
                    }

                    newNode.setTerminal(nonEmptySets);
                    curNode.addEdge(c, newNode);
                }
            }
        }
        return new Dfa(newStart);
    }
}
