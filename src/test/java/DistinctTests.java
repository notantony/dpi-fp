import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import org.junit.Test;
import util.MyList;
import util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DistinctTests {
    private static class CounterCloser implements Function<Nfa, Pair<Nfa, Integer>> {
        private int counter = -1;

        @Override
        public Pair<Nfa, Integer> apply(Nfa nfa) {
            counter++;
            nfa.close(counter);
            return new Pair<>(nfa, counter);
        }
    }

    private void testNonDistinct(Collection<String> rules) {

    }

    private void test(Collection<String> rules, Collection<String> strings) {
        List<Pair<Nfa, Integer>> nfas = rules.stream()
                .map(Main::buildNfa)
                .map(new CounterCloser())
                .collect(Collectors.toList());
        Dfa dfa = Main.minimize(
                Main.convert(
                        Nfa.union(nfas.stream().map(Pair::getFirst).collect(Collectors.toList()))
                )
        );
        List<Integer> solutions = strings.stream().map(string -> {
            List<Integer> accepted = nfas.stream()
                    .filter(nfa -> nfa.getFirst().test(string))
                    .map(Pair::getSecond)
                    .collect(Collectors.toList());
            if (accepted.size() > 1) {
                throw new IllegalArgumentException("String `" + string + "` matches more than one rule: " + accepted);
            }
            return accepted.isEmpty() ? null : accepted.get(0);
        }).collect(Collectors.toList());
        List<Integer> minimizedSolutions = strings.stream()
                .map(string -> {
                    ArrayList<Integer> accepted = new ArrayList<>(dfa.test(string));
                    if (accepted.size() > 1) {
                        throw new IllegalArgumentException("String `" + string + "` matches more than one rule: " + accepted);
                    }
                    return accepted.isEmpty() ? null : accepted.get(0);
                })
                .collect(Collectors.toList());
        assertEquals(solutions, minimizedSolutions);
    }

    @Test
    public void test1() {
        List<String> rules = MyList.of(
                "/abc/",
                "/qwe/",
                "/cde/"
        );
        List <String> strings = MyList.of(
                "abc",
                "abd",
                "qwe",
                "abdqwe",
                "q"
        );
        test(rules, strings);
    }

    @Test
    public void test2() {
        List<String> rules = MyList.of(
                "/^abce.*/",
                "/^abcd.*/",
                "/^abc.*/"
        );
        List <String> strings = MyList.of(
                "abc",
                "abd",
                "abcd",
                "abdqwe",
                "q"
        );
        test(rules, strings);
    }

}
