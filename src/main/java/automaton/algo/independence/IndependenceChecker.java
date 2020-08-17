package automaton.algo.independence;

import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndependenceChecker {
    private boolean[][] distinct;
    private HashSet<Integer>[][] incident;
    private ArrayList<Integer>[][] incidentFin;
    private Map<State, Integer> mapping;
    private Queue<Pair<Integer, Integer>> queue;
    private ArrayList<State> aStates;
    private ArrayList<State> bStates;
    private State.EpsilonMemoizedTraverser traverser = new State.EpsilonMemoizedTraverser();

    private void fillIncident(State a) { // TODO: memoization?
        int aId = mapping.get(a);
//        List<State> aEpsilon =
//        List<Pair<Character, Integer>> edges = State.traverseEpsilonsSafe(Collections.singleton(a)).parallelStream() // TODO: memoization here?
//        List<Pair<Character, Integer>> edges = traverser.traverseEpsilonsSafe(a).parallelStream() // TODO: memoization here?
        List<Pair<Character, Integer>> edges = Stream.of(a)
                .flatMap(aNeighbour -> aNeighbour.getEdges().stream())
                .filter(pair -> !(pair.getFirst() instanceof EpsilonTransition))
                .flatMap(pair ->
//                        State.traverseEpsilonsSafe(Collections.singleton(pair.getSecond()))
                        traverser.traverseEpsilonsSafe(pair.getSecond())
                                .stream()
                                .map(mapping::get)
                                .map(target -> new Pair<>(pair.getFirst(), target)))
                .flatMap(pair -> pair.getFirst().getAccepted().stream()
                        .map(targetChar -> new Pair<>(targetChar, pair.getSecond()))
                )
                .collect(Collectors.toList());
        edges.forEach(pair -> incident[pair.getSecond()][pair.getFirst()].add(aId));

//        aEpsilon.forEach(aNeighbour -> {
//            aNeighbour.getEdges().forEach(edge -> {
//                Transition transition = edge.getFirst();
//                State target = edge.getSecond();
//                if (!(transition instanceof EpsilonTransition)) {
//                    List<Integer> targetIds = State.traverseEpsilonsSafe(Collections.singletonList(target)).stream()
//                            .map(mapping::get)
//                            .collect(Collectors.toList());
//
//                    // TODO: add threading?
//                    transition.getAccepted().forEach(c -> {
//                        targetIds.forEach(targetId -> {
//                            incident[targetId][c].add(aId);
//                        });
//                    });
//                }
//            });
//        });
    }

    private void preCalc(Nfa a, Nfa b) {
        aStates = new ArrayList<>(a.getStart().allChildren());
        bStates = new ArrayList<>(b.getStart().allChildren());
        int newSize = aStates.size() + bStates.size();
        mapping = new HashMap<>();

        Logger.getGlobal().info("Processing " + newSize + " nodes");
        for (int i = 0; i < aStates.size(); i++) {
            mapping.put(aStates.get(i), i);
        }
        for (int i = 0; i < bStates.size(); i++) {
            mapping.put(bStates.get(i), aStates.size() + i);
        }

        incident = new HashSet[newSize][Transitions.MAX_CHAR + 1];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j <= Transitions.MAX_CHAR; j++) {
                incident[i][j] = new HashSet<>();
            }
        }
        aStates.forEach(this::fillIncident);
        bStates.forEach(this::fillIncident);

        distinct = new boolean[newSize][newSize];
        queue = new ArrayDeque<>();
        List<Integer> terminals = aStates.stream()
                .filter(State::isTerminal)
                .map(mapping::get)
                .collect(Collectors.toList());

        bStates.stream()
                .filter(State::isTerminal)
                .map(mapping::get)
                .forEach(bId -> {
                    terminals.forEach(aId -> {
                        distinct[aId][bId] = true;
                        queue.add(new Pair<>(aId, bId));
                    });
                });

        incidentFin = new ArrayList[newSize][Transitions.MAX_CHAR + 1];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j <= Transitions.MAX_CHAR; j++) {
                incidentFin[i][j] = incident[i][j].stream().distinct().collect(Collectors.toCollection(ArrayList::new));
            }
        }
        incident = null;
    }

//    private void debugPrint() {
//        int nNodes = mapping.size();
//        StringBuilder[][] v = new StringBuilder[nNodes][nNodes];
//        for (State a: aStates) {
//            for (State b: aStates)  {
//                for
//            }
//        }
//        mapping.forEach();
//    }

    public boolean areIndependent(Nfa a, Nfa b) {
        preCalc(a, b);

        int aStartId = mapping.get(a.getStart());
        int bStartId = mapping.get(b.getStart());
        Logger.getGlobal().info("Processing " + mapping.size() + " nodes");
//        printBoolean(distinct);

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.remove();
            for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                for (int aId : incidentFin[cur.getFirst()][c]) {
                    for (int bId : incidentFin[cur.getSecond()][c]) {
                        if (!distinct[aId][bId]) {
                            if (aId == aStartId && bId == bStartId) {
                                return false;
                            }
                            distinct[aId][bId] = true;
                            queue.add(new Pair<>(aId, bId));
                        }
                    }
                }
            }
        }
        Set<State> bNodes = traverser.traverseEpsilonsSafe(b.getStart());
        return traverser.traverseEpsilonsSafe(a.getStart()).parallelStream()
                .map(mapping::get)
                .flatMap(aNode -> bNodes.stream()
                        .map(mapping::get)
                        .map(bNode -> new Pair<>(aNode, bNode))
                )
                .noneMatch(pair -> distinct[pair.getFirst()][pair.getSecond()]);
//        return true;
    }
}
