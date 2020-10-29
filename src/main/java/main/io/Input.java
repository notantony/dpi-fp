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
import java.util.stream.Stream;

public class Input {
    public static List<String> readRules(String path) throws IOException {
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(path));
        return rulesReader.lines().collect(Collectors.toList());
    }

    public static List<Pair<String, Dfa>> readDfaPairs(String path) throws IOException {
        return readRules(path).stream()
                .map(rule -> new Pair<>(rule, Main.buildDfa(rule)))
                .collect(Collectors.toList());
    }

    public static Dfa readDfa(String path) throws IOException {
        return Dfa.parseDfa(new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8));
    }

    public static List<Dfa> readDfas(String path) throws IOException {
        return readRules(path).stream()
                .map(Main::buildDfa)
                .collect(Collectors.toList());
    }

    public static List<List<String>> readGroups(String rulesPath, String groupsPath) throws IOException {
        BufferedReader rulesReader = Files.newBufferedReader(Paths.get(rulesPath));
        BufferedReader groupsReader = Files.newBufferedReader(Paths.get(groupsPath));

        List<List<Integer>> groups = groupsReader.lines().map(a ->
                Stream.of(a.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())).collect(Collectors.toList());

        List<String> rules = rulesReader.lines().collect(Collectors.toList());

        return groups.stream()
                .map(group -> group.stream()
                        .map(rules::get)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
