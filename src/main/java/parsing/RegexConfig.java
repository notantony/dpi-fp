package parsing;

public class RegexConfig {
    private boolean caseInsensitive = false;

    public RegexConfig(String params) { // TODO: more config
        if (params.contains("i")) {
            caseInsensitive = true;
        }
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }
}
