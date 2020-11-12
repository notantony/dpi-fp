package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;
import java.util.logging.Logger;

public class Utils {
    public static char parseChar(String s) {
        if (s.startsWith("\\x")) {
            return (char) Integer.parseInt(s.substring(2), 16);
        } else if (s.length() == 1) {
            return s.charAt(0);
        }
        throw new IllegalArgumentException("Unexpected char sequence to parse: `" + s + "`");
    }

    public static Collection<Integer> testHeader(Function<String, Collection<Integer>> predicate, String s) {
        s = (char) 257 + s + (char) 256;
        Collection<Integer> result = predicate.apply(s);
        for (int i = 0; i <= s.length(); i++) {
            result.addAll(predicate.apply(s.substring(i)));
        }
        return result;
    }

    public static <T, X> X timeit(Function<T, X> function, T arg) {
        long pre = System.currentTimeMillis();
        X tmp = function.apply(arg);
        long post = System.currentTimeMillis();
        Logger.getGlobal().info(function.getClass().getCanonicalName() + ": " + (post - pre));
        return tmp;
    }

    public static <T, V, X> X timeit(BiFunction<T, V, X> function, T arg, V arg1) {
        return timeit(function, arg, arg1, "");
    }

    private static Map<String, Long> timeLog = new HashMap<>();
    public static <T, V, X> X timeit(BiFunction<T, V, X> function, T arg, V arg1, String label) {
        long pre = System.currentTimeMillis();
        X tmp = function.apply(arg, arg1);
        long post = System.currentTimeMillis();
//        Logger.getGlobal().info(label + (post - pre));
        long timing = post - pre;
        System.err.println(label + timing);
        timeLog.putIfAbsent(label, 0L);
        timeLog.put(label, timeLog.get(label) + timing);
        return tmp;
    }

    public static void printTimeLog() {
        System.err.println(timeLog.toString());
    }

    public static void writeTo(String path, String s) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String objCode(Object o) {
        return o == null ? "null" : o.toString().split("@")[1];
    }
}
