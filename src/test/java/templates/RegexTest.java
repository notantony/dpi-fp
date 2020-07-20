import java.util.function.BiFunction;

public class RegexTest {
    private String name, regex, string;
    private int verdict;

    public RegexTest(String name, String regex, String string, int verdict) {

    }

    public boolean runTest(BiFunction<String, String, Integer> tester) {
        return tester.apply(name, regex) == verdict;
    }
}
