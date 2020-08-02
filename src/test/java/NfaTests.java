import automaton.nfa.Nfa;
import main.Main;
import org.junit.Test;
import util.FutureList;

import java.util.List;

import static org.junit.Assert.*;

public class NfaTests {
    private void testRegex(String regex, List<String> accepted, List<String> rejected) {
        Nfa nfa = Main.buildNfa(regex);
        for (String string: accepted) {
            assertTrue(nfa.test(string));
        }
        for (String string: rejected) {
            assertFalse(nfa.test(string));
        }
    }

    @Test
    public void testRepeated1() {
        String rule = "/^a{1,3}/Hsmi";
        List<String> accepted = FutureList.of("a", "aa", "aaa");
        List<String> rejected = FutureList.of("", "aaaa", "b");
        testRegex(rule, accepted, rejected);
    }

    @Test
    public void
    testRepeated2() {
        String rule = "/^a{1,3}b{0,2}/Hsmi";
        List<String> accepted = FutureList.of("a", "aa", "aaa", "ab", "abb", "aaabb");
        List<String> rejected = FutureList.of("", "aaaa", "b", "bb", "baaa", "ba", "abbb");
        testRegex(rule, accepted, rejected);
    }

    @Test
    public void
    testRepeated3() {
        String rule = "/^\\w{0,1}\\d{0,2}/Hsmi";
        List<String> accepted = FutureList.of("", "a", "x", "a5", "b64", "44", "1");
        List<String> rejected = FutureList.of("aa2", "aa", "a345", "aaa", "42a", "3a", "a4a");
        testRegex(rule, accepted, rejected);
    }

//    test
}
