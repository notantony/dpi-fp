package automaton.transition;

import java.util.Collection;

public class EpsilonTransition implements Transition {
    public static EpsilonTransition epsilonTransition = new EpsilonTransition(); // TODO: refactor using static field

    @Override
    public Collection<Character> getAccepted() {
        throw new IllegalArgumentException("Epsilon transition cannot be checked");
    }

    @Override
    public boolean test(Character character) {
        throw new IllegalArgumentException("Epsilon transition cannot be checked");
    }
}
