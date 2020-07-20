package automaton.transition;

import java.util.Collection;
import java.util.function.Predicate;

public abstract class SpecialTransition implements Transition {

    @Override
    public abstract boolean test(Character character);

    @Override
    public abstract Collection<Character> getAccepted();
}
