package automaton.algo.independence;

import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.EpsilonTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;
import util.Pair;

import java.util.*;
import java.util.logging.Logger;

public class IndependenceCheckerBfs {
    private boolean[][] distinct;
    private Map<State, Integer> mapping;
    private Deque<Pair<State, State>> queue;
    private ArrayList<State> aStates;
    private ArrayList<State> bStates;
    private State.EpsilonMemoizedTraverser traverser = new State.EpsilonMemoizedTraverser();

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

        distinct = new boolean[newSize][newSize];
    }

    private boolean insert(State aTarget, State bTarget) {
        int aId = mapping.get(aTarget);
        int bId = mapping.get(bTarget);
        if (!distinct[aId][bId]) {
            if (aTarget.isTerminal() || bTarget.isTerminal()) {
                return false;
            }
            distinct[aId][bId] = true;
            queue.add(new Pair<>(aTarget, bTarget));
        }
        return true;
    }

    public boolean areIndependent(Nfa a, Nfa b) {
        preCalc(a, b);

        int aStartId = mapping.get(a.getStart());
        int bStartId = mapping.get(b.getStart());

        Logger.getGlobal().info("Processing " + mapping.size() + " nodes");

        queue = new ArrayDeque<>();
        queue.add(new Pair<>(a.getStart(), b.getStart()));

        while (!queue.isEmpty()) {
            Pair<State, State> cur = queue.remove();
//            Pair<State, State> cur = queue.removeLast();
            State aState = cur.getFirst();
            State bState = cur.getSecond();
            for (Pair<Transition, State> aEdge : aState.getEdges()) {
                if (aEdge.getFirst() instanceof EpsilonTransition) {
                    if (!insert(aEdge.getSecond(), bState)) {
                        return false;
                    }
                    continue;
                }
                for (Pair<Transition, State> bEdge : bState.getEdges()) {
                    if (bEdge.getFirst() instanceof EpsilonTransition) {
                        if (!insert(aState, bEdge.getSecond())) {
                            return false;
                        }
                        continue;
                    }
                    for (char c = 0; c <= Transitions.MAX_CHAR; c++) {
                        if (aEdge.getFirst().test(c) && bEdge.getFirst().test(c)) {
                            if (!insert(aEdge.getSecond(), bEdge.getSecond())) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
