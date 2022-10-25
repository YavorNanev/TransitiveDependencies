package com.td;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public class TransitiveDependencies {

    Map<String, HashSet<String>> inputDependencies = new HashMap<>();

    public void computeDependencies(final String testData, final String lineSeparator, final String charSeparator) {

        Arrays.asList(testData.split(lineSeparator))
                .forEach(line -> extractKeyAndItsDependenciesFromAline(line, charSeparator));
        inputDependencies.forEach((key, dependencies) -> addTransitiveDependenciesPerKey(dependencies, key));

    }

    private void extractKeyAndItsDependenciesFromAline(String key, String charSeparator) {
        var inputList = new ArrayList<>(Arrays.asList(key.split(charSeparator)));
        inputDependencies.put(inputList.get(0), new HashSet<>(inputList.subList(1, inputList.size())));
    }

    private void addTransitiveDependenciesPerKey(HashSet<String> dependencies, String key) {

        Map<String, HashSet<String>> transitiveDependencies = new HashMap<>();
        Optional.ofNullable(dependencies).ifPresent(depKey ->
                getDependenciesQueue(depKey).flatMap(this::fetchAllDependencies)
                        .map(value -> transitiveDependencies.put(key, value)));

        System.out.println(transitiveDependencies);
    }

    private Optional<Queue<String>> getDependenciesQueue(HashSet<String> key) {
        return Optional.of(new LinkedList<>(key));
    }

    private Optional<HashSet<String>> fetchAllDependencies(Queue<String> dependenciesQueue) {

        HashSet<String> allDependencies = new HashSet<>();
        while (!dependenciesQueue.isEmpty()) {
            Queue<String> tempDependenciesQueueQueue = new LinkedList<>();

            for (String dep : dependenciesQueue) {
                allDependencies.add(dep);
                Optional.ofNullable(inputDependencies.get(dep))
                        .ifPresent(tempDependenciesQueueQueue::addAll);
            }
            dependenciesQueue = tempDependenciesQueueQueue;
        }
        return Optional.of(allDependencies);
    }

}
