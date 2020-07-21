package util;

import automaton.transition.SingleElementTransition;

import java.util.function.Predicate;

public class Utils {
    public static char parseChar(String s) {
        if (s.startsWith("\\x")) {
            return (char) Integer.parseInt(s.substring(2), 16);
        } else if (s.length() == 1) {
            return s.charAt(0);
        }
        throw new IllegalArgumentException("Unexpected char sequence to parse: `" + s + "`");
    }

    public static boolean testHeader(Predicate<String> predicate, String s) {
        s = (char) 257 + s + (char) 256;
        boolean result = predicate.test(s);
        for (int i = 0; i <= s.length(); i++) {
            if (result) {
                return result;
            }
            result = predicate.test(s.substring(i));
        }
        return result;
    }
}
