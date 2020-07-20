package automaton.transition;

import java.util.Collection;

public abstract class AbstractTransition implements Transition {

    @Override
    public boolean test(Character c) {
        if (c <= 255) {
            return testImpl(c);
        }
        return false;
    }

    public abstract boolean testImpl(Character b);

    public abstract Collection<Character> getAccepted();
}
