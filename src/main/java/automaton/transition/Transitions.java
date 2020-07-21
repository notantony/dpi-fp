package automaton.transition;

import parsing.ParsingError;
import parsing.RegexConfig;

import java.util.*;

public class Transitions {
    final private static Map<String, Transition> transitionMap = generateTransitionMap();
    final public static char MAX_CHAR = 257;

    private static HashMap<String, Transition> generateTransitionMap() { // TODO: pass config
        HashMap<String, Transition> transitionMap = new HashMap<>();
        transitionMap.put("\\n", new SingleElementTransition('\n'));
        transitionMap.put("\\t", new SingleElementTransition('\t'));
        transitionMap.put("\\r", new SingleElementTransition('\r'));
        transitionMap.put("\\f", new SingleElementTransition('\f'));
        transitionMap.put("$", new EofTransition()); // TODO: special character
        transitionMap.put("^", new StartTransition());
        char[] symbols = { '.', '/', '\\', '-', '?', '(', ')', ':', '^', '\'', ',', ';', '=', '<', '>', '*', '&', '{',
                '}', '|', '+', '%', '!', '_', '[', ']' };
        for (char symbol: symbols) {
            transitionMap.put("\\" + symbol, new SingleElementTransition(symbol));
        }

        Set<Character> sClass = Set.of('\n', '\t', '\r', '\f', ' ');
        transitionMap.put("\\s", new CollectionTransition(sClass));

        transitionMap.put("\\h", new SingleElementTransition('\t')); // TODO: support Unicode / filter rules

        Transition digitTransition = new RangeTransition('0', '9');
        transitionMap.put("\\d", digitTransition);

        Transition alphaTransition1 = new RangeTransition('a', 'z');
        Transition alphaTransition2 = new RangeTransition('A', 'Z');
        CollectionTransition wordTransition = new CollectionTransition(alphaTransition1, alphaTransition2);
        wordTransition.addMore(new SingleElementTransition('_'));
        wordTransition.addMore(digitTransition);
        transitionMap.put("\\w", wordTransition);

        transitionMap.put("\\S", new ComplementTransition(transitionMap.get("\\s")));
        transitionMap.put("\\D", new ComplementTransition(transitionMap.get("\\d")));
        transitionMap.put("\\W", new ComplementTransition(transitionMap.get("\\w")));

        transitionMap.put("\\i", new CollectionTransition(wordTransition, new SingleElementTransition(':')));

        return transitionMap;
    }

    public static Transition ofString(String s, RegexConfig config) {
        return ofStringImpl(s, config, false);
    }

    public static Transition ofStringImpl(String s, RegexConfig config, boolean literal) {
        if (s.equals(".")) {
            return new RangeTransition((char) 0, (char) 255);
        }
        if (literal) {
            if (s.equals("$") | s.equals("^")) {
                return new SingleElementTransition(s.charAt(0));
            }
        }
        if (transitionMap.containsKey(s)) {
            return transitionMap.get(s);
        }
        if (s.length() == 1) {
            char c = s.charAt(0);
            if (config.isCaseInsensitive() && Character.isAlphabetic(c)) {
                List<Character> list = List.of(Character.toUpperCase(c), Character.toLowerCase(c));
                return new CollectionTransition(list);
            }
            return new SingleElementTransition(c);
        }
        if (s.startsWith("\\x")) {
            return new SingleElementTransition((char) Integer.parseInt(s.substring(2), 16));
        }
        throw new ParsingError("Unexpected character sequence for transition: `" + s + "`");
    }
}
