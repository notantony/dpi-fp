package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FutureSet {
    @SafeVarargs
    public static <T> Set<T> of(T ... a) {
        return new HashSet<T>(Arrays.asList(a));
    }
}
