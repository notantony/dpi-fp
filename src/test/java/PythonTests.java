import automaton.nfa.Nfa;
import org.junit.Test;
import parsing.ParsingError;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PythonTests {
    @Test
    public void test() throws IOException {
        BufferedReader tests = Files.newBufferedReader(Paths.get("./pythonTests/tests.txt"));
        String line;
        while ((line = tests.readLine()) != null) {
            String regex = tests.readLine();
            String string = tests.readLine();
            int verdict = Integer.parseInt(tests.readLine());
            Nfa nfa;
            try {
                nfa = Main.buildNfa(regex);
            } catch (IllegalArgumentException | ParsingError e) {
                assertEquals(line, 2, verdict);
                continue;
            }
            assertEquals(line, verdict == 0, nfa.test(string));
        }
    }
}
