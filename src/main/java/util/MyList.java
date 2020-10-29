package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyList {

    @SafeVarargs
    public static <T> List<T> of(T ... a) {
        return Arrays.asList(a);
    }
}
