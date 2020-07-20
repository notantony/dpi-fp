package automaton.transition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComplementTransition extends AbstractTransition {
    final private Transition orig;

    public ComplementTransition(Transition orig) {
        this.orig = orig;
    }

    @Override
    public boolean testImpl(Character c) {
        return !orig.test(c);
    }

    @Override
    public Collection<Character> getAccepted() {
        List<Character> accepted = new ArrayList<>();
        for (char c = 0; c < 255; c++) {
            if (!orig.test(c)) {
                accepted.add(c);
            }
        }
        return accepted;
    }
}
