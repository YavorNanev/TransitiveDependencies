package com.td;

public class Main {

    public static void main(String[] args) {


        String testData = """
                A B C
                B C E
                C G
                D A F
                E F
                F H
                """;

        TransitiveDependencies trd = new TransitiveDependencies();
        trd.computeDependencies(testData, "\\n", " ");
    }
}
