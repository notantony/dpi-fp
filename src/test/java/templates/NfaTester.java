package templates;

import automaton.nfa.Nfa;
import main.Main;
import parsing.ParsingError;

import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public class NfaTester implements BiFunction<String, String, Integer> {
    @Override
    public Integer apply(String regex, String string) {
        Nfa nfa;
        try {
            nfa = Main.buildNfa(regex);
        } catch (IllegalArgumentException | ParsingError e) {
            return 2;
        }
        return nfa.test(string) ? 0 : 1;
    }
}
