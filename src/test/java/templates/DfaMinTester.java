package templates;

import automaton.algo.DfaMinimizer;
import automaton.algo.thompson.ThompsonConverter;
import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import parsing.ParsingError;

import java.util.function.BiFunction;

public class DfaMinTester implements BiFunction<String, String, Integer> {

    @Override
    public Integer apply(String regex, String string) {
        Nfa nfa;
        try {
            nfa = Main.buildNfa(regex);
        } catch (IllegalArgumentException | ParsingError e) {
            return 2;
        }
        ThompsonConverter converter = new ThompsonConverter();
        Dfa dfa = converter.run(nfa);
        DfaMinimizer minimizer = new DfaMinimizer();
        Dfa min = minimizer.run(dfa);
        return min.testAny(string) ? 0 : 1;
    }
}
