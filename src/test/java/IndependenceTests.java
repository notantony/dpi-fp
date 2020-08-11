import automaton.algo.AlgoException;
import automaton.algo.IndependenceChecker;
import automaton.algo.ThompsonModified;
import automaton.nfa.Nfa;
import org.junit.Test;
import util.FutureList;
import util.Pair;

import static main.Main.buildNfa;
import static org.junit.Assert.assertEquals;

public class IndependenceTests {
    private boolean runChecker(Nfa a, Nfa b) {
        return new IndependenceChecker().areIndependent(a, b);
    }

    private boolean runThompson(Nfa a, Nfa b) {
        try {
            new ThompsonModified().run(FutureList.of(a, b));
        } catch (AlgoException e) {
            return false;
        }
        return true;
    }

    String[] aSamples = {
            "1\n" + "0 1 q\n" + "1 1 c",
            "1\n" + "0 1 q\n" + "1 1 c",
            "1\n" + "0 1 a\n" + "1 1 c",
            "1\n" + "0 1 a\n" + "1 1 ac",
            "1\n" + "0 1 a\n" + "1 1 ac",
//            "/^a.*/", "/^ab.*/",
    };
    String[] bSamples = {
            "1\n" + "0 1 a\n" + "1 1 c",
            "3\n" + "0 1 a\n" + "1 2 c\n" + "2 2 a\n" + "2 3 c",
            "3\n" + "0 1 a\n" + "1 2 c\n" + "2 2 a\n" + "2 3 c",
            "2\n" + "0 1 a\n" + "1 2 b\n" + "2 2 ab",
            "2\n" + "0 1 a\n" + "1 2 b\n" + "2 2 ac",
    };

    @Test
    public void testParsing() {
        for (int i = 0; i < aSamples.length; i++) {
            Nfa a = Nfa.parseNfa(aSamples[i]);
            Nfa b = Nfa.parseNfa(bSamples[i]);
            a.setupTail();
            b.setupTail();
            a.close(1);
            b.close(2);
            assertEquals(
                    "Test #" + i + "\nA:\n" + aSamples[i] + "\nB:\n" + bSamples[i],
                    runThompson(a, b),
                    runChecker(a, b)
            );
        }
    }

    String[] regexPairs = {
            "//", "/b/",
//            "b", "c"
    };


    @Test
    public void testRegex() {
        for (int i = 0; i < regexPairs.length / 2; i++) {
            Nfa a = buildNfa(regexPairs[2 * i]);
            Nfa b = buildNfa(regexPairs[2 * i + 1]);
            a.close(1);
            b.close(2);
            assertEquals(
                    "Test #" + (2 * i) + "-" + (2 * i + 1) + "\nA:\n" + regexPairs[2 * i] + "\nB:\n" + regexPairs[2 * i + 1],
                    runThompson(a, b),
                    runChecker(a, b)
            );
        }
    }
}
