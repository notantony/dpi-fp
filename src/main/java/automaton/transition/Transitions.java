package automaton.transition;

import org.stringtemplate.v4.ST;
import parsing.ParsingError;

import java.util.*;

public class Transitions {
    final private static Map<String, Transition> transitionMap = generateTransitionMap();

    private static HashMap<String, Transition> generateTransitionMap() { // TODO: pass config
        HashMap<String, Transition> transitionMap = new HashMap<>();
        transitionMap.put("\\n", new SingleElementTransition('\n'));
        transitionMap.put("\\t", new SingleElementTransition('\t'));
        transitionMap.put("\\r", new SingleElementTransition('\r'));
        transitionMap.put("\\f", new SingleElementTransition('\f'));
        char[] symbols = { '.', '/', '\\', '-', '?', '(', ')', ':', '^', '\'', ',', ';', '=', '<', '>', '*', '&', '{',
                '}', '|', '+', '%', '!', '_', '[', ']' };
        for (char symbol: symbols) {
            transitionMap.put("\\" + symbol, new SingleElementTransition(symbol));
        }

        Set<Character> sClass = Set.of('\n', '\t', '\r', '\f', ' ');
        transitionMap.put("\\s", new CollectionTransition(sClass));

        Transition digitTransition = new RangeTransition('0', '9');
        transitionMap.put("\\d", digitTransition);

        Transition alphaTransition = new RangeTransition('a', 'z');
        CollectionTransition wordTransition = new CollectionTransition(digitTransition, alphaTransition);
        wordTransition.addMore(new SingleElementTransition('_'));
        transitionMap.put("\\w", wordTransition);

        return transitionMap;
    }

    public static Transition ofString(String s) {
        if (s.equals(".")) {
            return new RangeTransition((char) 0, (char) 255);
        }
        if (s.length() == 1) {
            return new SingleElementTransition(s.charAt(0));
        }
        if (s.startsWith("\\x")) {
            return new SingleElementTransition((char) Integer.parseInt(s.substring(2), 16));
        }
        if (transitionMap.containsKey(s)) {
            return transitionMap.get(s);
        }
        throw new ParsingError("Unexpected character sequence for transition: `" + s + "`");
    }
}
