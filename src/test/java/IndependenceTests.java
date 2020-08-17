import automaton.nfa.Nfa;
import main.Main;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static main.Main.*;
import static org.junit.Assert.assertEquals;
import static util.Utils.printTimeLog;
import static util.Utils.timeit;

public class IndependenceTests {

    String[] aSamples = {
            "1\n" + "0 1 q\n" + "1 1 c",
            "1\n" + "0 1 a\n" + "1 1 ac",
            "0\n" + "0 0 ab",
            "1\n" + "0 1 q\n" + "1 1 c",
            "1\n" + "0 1 a\n" + "1 1 c",
            "1\n" + "0 1 a\n" + "1 1 ac",
            "1\n" + "0 1 a\n" + "1 1 ac",
//            "/^a.*/", "/^ab.*/",
    };
    String[] bSamples = {
            "1\n" + "0 1 a\n" + "1 1 c",
            "2\n" + "0 1 a\n" + "1 2 b\n" + "2 2 ab",
            "2\n" + "0 1 a\n" + "1 2 b\n" + "2 2 ab",
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
            "/^[a-c]/i",
            "/^ab/i",

            "/^(a{3})?\\w/i",
            "/^nettcp/i",

            "/^(\\d{3}\\x20)?\\S*\\x25\\w/i",
            "/^net\\x2etcp\\x2elisten\\x5b\\s*?\\d+?\\s*?\\x3b/i",

            "//",
            "/b/",
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

    String[] regexCompleteSet = {
//            "/(^.{36}([^\\x00]{0,255}\\x00)*[^\\x00]{256}).*/isR",
//            "/(^.{36}([^\\x00]{0,255}\\x00)*[^\\x00]{256}).*/isR",

            "/(^PRIVMSG\\s+[^\\s]+\\s+\\x3a\\s*\\x01SENDLINK\\x7c[^\\x7c]{69}).*/smi",
            "/(^\\/\\d+$).*/Ui",
            "/(^net\\x2etcp\\x2elisten\\x5b\\s*?\\d+?\\s*?[\\x22\\x27]\\s*?\\x3b).*/i",
            "/(^Contact\\x3A\\s*[^\\r\\n%]*%).*/Hsmi",
            "/(^(\\d{3}\\x20)?\\S*\\x25\\w).*/i",
            "/(^[^\\x22]+\\x22(\\d+\\x20+){2}).*/R",
            "/(^[a-z]+\\s+https\\x3a\\x2f\\x2f[^\\x2f\\x3a\\x25\\s]*\\x25[sn]).*/i",
            "/(^.{16}[^\\x00]{256}).*/sR",
            "/(^[^\\x26\\s]*(\\x3e|\\x253e)).*/iR",
            "/(^Reference\\s*=\\s*\\*\\x5CG\\{[A-Z\\d-]{36}\\}\\x23\\d+\\.\\d+\\x23\\d+\\x23[^\\r\\n]{474}).*/smi",
            "/(^HELO\\s[^\\n]{500}).*/smi",

            "/(^Referer\\x3A[^\\r\\n]*www\\x2Ewowokay\\x2Ecom/wowokaybar\\x2Ephp).*/smi",
            "/(^Contact\\x3a\\s+\\x22[^\\x22]*\\x3c).*/Hsmi",
            "/(^.{36}([^\\x00]{0,255}\\x00)*[^\\x00]{256}).*/isR",
            "/(^(GET|POST)\\h+[^\\n]*?\\x2E\\x2E\\x5C\\x2E\\x2E\\x5C\\x2E\\x2E\\x5C[^\\n]*?HTTP).*/i",
            "/(^\\S+\\s+(uid\\s+|)search\\s+charset\\s*\\{\\s).*/smi",
            "/(^SSKC[^\\r\\n]*v2\\x2E0[^\\r\\n]*Startup[^\\r\\n]*at).*/smi",
            "/(^Subject\\x3a[^\\r\\n]*\\x28\\x29).*/smi",
            "/(^User-Agent\\x3a[^\\r\\n]*samsung).*/iH",
//            "/(^X-FILTERED-BY-GHOST\\x3a[^\\r\\n]*1).*/smi",
//            "/(^.{2}).*/sR",
//            "/(^(GET|POST|PUT|HEAD)).*/mi",
//            "/(^User-Agent\\x3A[^\\r\\n]*TT-Bot).*/mi",
//            "/(^Erazer\\s+SIN\\s+Server).*/smi",
//            "/(^(Color|Motion)).*/Ri",
//            "/(^Host\\x3A\\s+83\\.149\\.75\\.49).*/smi",
//            "/(^.{1,20}HTTP\\s*\\x2f\\s*[12]\\.[01]).*/i",
//            "/(^Conectado\\s+Yeah\\!).*/smi",
//            "/(^..$).*/R",
//            "/(^File\\d+\\s*\\x3D\\s*[^\\x2E\\r\\n]+\\x2E[^\\r\\n]{32}).*/mi",
//            "/(^sleep\\x7c\\d+\\x7c\\d+\\x7C[a-z0-9]+\\x2E[a-z]{2,3}\\x7C[a-z0-9]+\\x7C).*/",
//            "/(^HEAD[^s]{432}).*/sm",
//            "/(^User-Agent\\x3A[^\\r\\n]+Kindle\\x2F3\\x2E0\\x2B).*/Hsmi",
//            "/(^User-Agent\\x3A[^\\r\\n]*Excite).*/smiH",
//            "/(^Host\\x3A[^\\r\\n]*www\\x2Epurityscan\\x2Ecom).*/smiH"
    };


    private void testManyComplete(List<Nfa> nfas) {
        for (int i = 0; i < nfas.size(); i++) {
            nfas.get(i).close(i + 1);
        }
        for (int i = 0; i < nfas.size(); i++) {
            for (int j = 0; j < i; j++) {
                Nfa a = nfas.get(i);
                Nfa b = nfas.get(j);

                boolean thompsonVerdict = timeit(Main::runThompson, a, b, "Thompson: ");
                boolean thompsonAltVerdict = timeit(Main::runThompsonAlt, a, b, "ThompsonAlt: ");
                boolean checkerVerdict = timeit(Main::runChecker, a, b, "Checker: ");
                boolean checkerAltVerdict = timeit(Main::runCheckerAlt, a, b, "CheckerAlt: ");

                System.err.println();

                assertEquals(
                        thompsonAltVerdict,
                        thompsonVerdict
                );

                assertEquals(
                        "Test #" + i + "-" + j + "\nA:\n" + regexCompleteSet[i] + "\nB:\n" + regexCompleteSet[j],
                        thompsonVerdict,
                        checkerVerdict
                );

                assertEquals(
                        thompsonVerdict,
                        checkerAltVerdict
                );
            }
        }
        printTimeLog();
    }

    @Test
    public void testParseComplete() {
        List<Nfa> nfas = Stream.concat(Stream.of(aSamples), Stream.of(bSamples))
                .map(Nfa::parseNfa)
                .collect(Collectors.toList());
        testManyComplete(nfas);
    }

    @Test
    public void testRegexComplete() {
        List<Nfa> nfas = Stream.of(regexCompleteSet).parallel()
                .map(Main::buildNfa)
                .collect(Collectors.toList());
        testManyComplete(nfas);
    }
}
