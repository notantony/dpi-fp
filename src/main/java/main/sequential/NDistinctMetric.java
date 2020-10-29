package main.sequential;

import graph.GreedySplitter;
import graph.IntGraph;
import main.graph.GroupsMetric;
import main.graph.IndGraphParallelBuilder;
import main.io.Input;
import main.io.Static;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NDistinctMetric {
    public static void main(String[] args) throws IOException {
        List<List<String>> groups = Input.readGroups(Static.FILTERED, Static.TOP_10 + "/special.txt");

        groups.forEach(group -> {
            System.out.println("INDEPENDENT:");
            NDistinctMetric.processGroup(group);
            System.out.println("DEPENDENT:");
            GroupsMetric.processGroupDependent(group);
            System.out.println("===");
        });
    }

    private static void processGroup(List<String> rules) {
        IndGraphParallelBuilder graphBuilder = new IndGraphParallelBuilder(4);
        IntGraph graph = graphBuilder.process(rules);
        GreedySplitter splitter = new GreedySplitter();
        List<List<Integer>> groups = splitter.split(graph);
        System.out.println("Total " + groups.size() + " groups");
        groups.forEach(group -> {
            List<String> rulesGroup = group.stream()
                    .map(rules::get)
                    .collect(Collectors.toList());
            GroupsMetric.processGroup(rulesGroup);
        });
    }
}
