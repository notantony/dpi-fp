package automaton.transition;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionTransition extends AbstractTransition {
    private Collection<Character> collection;

    public CollectionTransition(Collection<Character> collection) {
        this.collection = collection;
    }

    public CollectionTransition(Transition a, Transition b) {
        this.collection = Stream.concat(
                a.getAccepted().stream(),
                b.getAccepted().stream())
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static CollectionTransition merge(Collection<Transition> transitions) {
        return new CollectionTransition(transitions.stream()
                .flatMap(transition -> transition.getAccepted().stream())
                .collect(Collectors.toCollection(HashSet::new)));
    }

    public void addMore(Transition other) {
        collection = Stream.concat(
                collection.stream(),
                other.getAccepted().stream())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public boolean testImpl(Character b) {
        return collection.contains(b);
    }

    @Override
    public Collection<Character> getAccepted() {
        return collection;
    }
}
