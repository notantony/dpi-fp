package automaton.transition;

import java.util.Collection;
import java.util.Collections;

public class SingleElementTransition extends AbstractTransition {
    final private char c;

    public SingleElementTransition(char c) {
        assert c < Transitions.MAX_CHAR;
        this.c = c;
    }

    @Override
    public boolean testImpl(Character c) {
        return c == this.c;
    }

    @Override
    public Collection<Character> getAccepted() {
        return Collections.singletonList(c);
    }
}
