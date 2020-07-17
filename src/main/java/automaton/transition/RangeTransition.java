package automaton.transition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RangeTransition extends AbstractTransition {
    final private char l, r;

    public RangeTransition(char l, char r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public boolean test(Character c) {
        return c >= l && c <= r;
    }

    @Override
    public Collection<Character> getAccepted() {
        List<Character> list = new ArrayList<>(); // TODO: HashSet?
        for (char c = l; c <= r; c++) {
            list.add(c);
        }
        return list;
    }
}
