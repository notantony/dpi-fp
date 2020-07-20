import automaton.nfa.Nfa;
import main.Main;
import org.junit.Test;
import parsing.ParsingError;
import templates.NfaTester;
import templates.RegexTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import generated.python.PythonTestset;

public class PythonTests {

    @Test
    public void test() {
        NfaTester tester = new NfaTester();
        List<String> failed = new ArrayList<>();
        for (RegexTest test: PythonTestset.allTests) {
            if (!test.runTest(tester)) {
                failed.add(test.getName());
            }
        }
        assertTrue("Total " + failed.size() + " of " + PythonTestset.allTests.length +
                " tests failed:\n" + String.join("\n", failed), failed.isEmpty());
    }
}
