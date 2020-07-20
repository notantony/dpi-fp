package automaton.transition;

import java.util.Collection;
import java.util.Collections;

public class StartTransition extends SpecialTransition {
    @Override
    public boolean test(Character character) {
        return character == 257;
    }

    @Override
    public Collection<Character> getAccepted() {
        return Collections.singletonList((char) 257);
    }
}
