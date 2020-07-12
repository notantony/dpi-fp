package automaton.transition;

import java.util.Collection;
import java.util.function.Predicate;

public interface Transition extends Predicate<Character> {

    Collection<Character> getAccepted();
}
