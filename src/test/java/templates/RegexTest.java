package templates;

import java.util.function.BiFunction;

public class RegexTest {
    final private String name, regex, string;
    final private int verdict;

    public RegexTest(String name, String regex, String string, int verdict) {
        this.name = name;
        this.regex = regex;
        this.string = string;
        this.verdict = verdict;
    }

    public boolean runTest(BiFunction<String, String, Integer> tester) {
        return tester.apply(regex, string) == verdict;
    }

    public String getName() {
        return name;
    }
}
