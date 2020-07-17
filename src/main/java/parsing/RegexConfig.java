package parsing;

public class RegexConfig {
    private boolean caseSensitive = false;

    public RegexConfig(String params) { // TODO: more config
        if (params.contains("s")) {
            caseSensitive = true;
        }
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }
}
