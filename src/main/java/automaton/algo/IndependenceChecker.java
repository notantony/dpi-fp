package automaton.algo;

import automaton.dfa.Node;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;

import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.ArrayPrinter.printBoolean;

public class IndependenceChecker {
    private boolean[][] distinct;
    private ArrayList<Integer>[][] incident;
    private Map<State, Integer> mapping;
    private Queue<Pair<Integer, Integer>> queue;
    private ArrayList<State> aStates;
    private ArrayList<State> bStates;

    private void fillIncident(State a) { // TODO: memoization?
        int aId = mapping.get(a);
        a.getEdges().forEach(edge -> {
            Transition transition = edge.getFirst();
            State target = edge.getSecond();
            if (!(transition instanceof EpsilonTransition)) {
                List<Integer> targetIds = State.traverseEpsilonsSafe(Collections.singletonList(target)).stream()
                        .map(mapping::get).collect(Collectors.toList());

                // TODO: add threading?
                transition.getAccepted().forEach(c -> {
                    targetIds.forEach(targetId -> {
//                        assert targetId != 1089;
                        if (c == 1089) {
                            System.out.println(transition.getClass());
                        }
                        incident[targetId][c].add(aId);
                    });
                });
            }

        });
    }

    private void preCalc(Nfa a, Nfa b) {
        aStates = new ArrayList<>(a.getStart().allChildren());
        bStates = new ArrayList<>(b.getStart().allChildren());
        int newSize = aStates.size() + bStates.size();
        mapping = new HashMap<>();
        for (int i = 0; i < aStates.size(); i++) {
            mapping.put(aStates.get(i), i);
        }
        for (int i = 0; i < bStates.size(); i++) {
            mapping.put(bStates.get(i), aStates.size() + i);
        }

        incident = new ArrayList[newSize][Transitions.MAX_CHAR + 1];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j <= Transitions.MAX_CHAR; j++) {
                incident[i][j] = new ArrayList<>();
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
                .filter(state -> state.isTerminal())
                .map(mapping::get)
                .forEach(nonTerminalId -> {
                    terminals.forEach(terminalId -> {
                        distinct[nonTerminalId][terminalId] = true;
                        distinct[terminalId][nonTerminalId] = true;
                        queue.add(new Pair<>(nonTerminalId, terminalId)); // TODO: no need for duplicates?
                        queue.add(new Pair<>(terminalId, nonTerminalId));
                    });
                });
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
//        printBoolean(distinct);

        while (!queue.isEmpty()) { // TODO: instant return on two starts
            Pair<Integer, Integer> cur = queue.remove();
            for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                char finalC = c;
                incident[cur.getFirst()][finalC].forEach(aId ->
                        incident[cur.getSecond()][finalC].forEach(bId -> {
                            if (!distinct[aId][bId]) {
                                distinct[aId][bId] = true; // TODO: no need for duplicates? (2)
                                distinct[bId][aId] = true;
                                queue.add(new Pair<>(aId, bId));
                            }
                        })
                );
            }
        }
        return !distinct[mapping.get(a.getStart())][mapping.get(b.getStart())];
    }
}
