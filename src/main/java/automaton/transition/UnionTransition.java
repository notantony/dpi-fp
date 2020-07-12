package automaton.transition;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnionTransition extends AbstractTransition {
    final private Transition a, b;
    private Collection<Character> accepted;

    public UnionTransition(Transition a, Transition b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean test(Character c) {
        return a.test(c) | b.test(c);
    }

    @Override
    public Collection<Character> getAccepted() {
        if (accepted == null) {
            accepted = Stream.concat(
                    a.getAccepted().stream(),
                    b.getAccepted().stream())
                    .collect(Collectors.toCollection(HashSet::new));
        }
        return accepted;
    }
}
