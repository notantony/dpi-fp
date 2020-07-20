package util;

import automaton.transition.SingleElementTransition;

public class Utils {
    public static char parseChar(String s) {
        if (s.startsWith("\\x")) {
            return (char) Integer.parseInt(s.substring(2), 16);
        } else if (s.length() == 1) {
            return s.charAt(0);
        }
        throw new IllegalArgumentException("Unexpected char sequence to parse: `" + s + "`");
    }
}
