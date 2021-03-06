package automaton.transition;

import parsing.ParsingError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static util.Utils.parseChar;

public class RangeTransition extends AbstractTransition {
    final private char l, r;

    public RangeTransition(char l, char r) {
        if (r > Transitions.MAX_CHAR) {
            throw new ParsingError("r > MAX_CHAR");
        }
        if (l > r) {
            throw new ParsingError("l > r");
        }
        this.l = l;
        this.r = r;
    }

    public RangeTransition(String l, String r) {
        this(parseChar(l), parseChar(r));
        assert parseChar(l) < Transitions.MAX_CHAR;
        assert parseChar(r) < Transitions.MAX_CHAR;
    }

    @Override
    public boolean testImpl(Character c) {
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
