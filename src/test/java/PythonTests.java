import automaton.dfa.Dfa;
import automaton.nfa.Nfa;
import main.Main;
import org.junit.Test;
import parsing.ParsingError;
import templates.DfaMinTester;
import templates.DfaTester;
import templates.NfaTester;
import templates.RegexTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import generated.python.PythonTestset;

public class PythonTests {

    private void runAll(BiFunction<String, String, Integer> tester) {
        List<String> failed = new ArrayList<>();
        for (RegexTest test: PythonTestset.allTests) {
            if (!test.runTest(tester)) {
                failed.add(test.getName());
            }
        }
        assertTrue("Total " + failed.size() + " of " + PythonTestset.allTests.length +
                " tests failed:\n" + String.join("\n", failed), failed.isEmpty());
    }

    @Test
    public void testNfa() {
        runAll(new NfaTester());
    }

    @Test
    public void testDfa() {
        runAll(new DfaTester());
    }

    @Test
    public void testDfaMin() {
        runAll(new DfaMinTester());
    }

}
