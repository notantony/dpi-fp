package automaton.transition;

import java.util.Collection;

public abstract class AbstractTransition implements Transition {

    @Override
    public abstract boolean test(Character b);

    public abstract Collection<Character> getAccepted();
}
