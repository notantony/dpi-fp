package main.io;

import automaton.dfa.Dfa;
import main.Main;
import util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Input {
    public static List<String> readRules(String path) throws IOException {
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(path));
        return rulesReader.lines().collect(Collectors.toList());
    }

    public static List<Pair<String, Dfa>> readDfaPairs(String path) throws IOException {
        return readDfas(path).stream()
                .map(rule -> new Pair<>(rule, Main.buildDfa(rule)))
                .collect(Collectors.toList());
    }

    public static Dfa readDfa(String path) throws IOException {
        return Dfa.parseDfa(new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8));
    }

    public static List<String> readDfas(String path) throws IOException {
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(path));
        return rulesReader.lines().collect(Collectors.toList());
    }
}
