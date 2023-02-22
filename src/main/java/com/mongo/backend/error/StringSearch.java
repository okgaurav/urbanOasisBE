package com.mongo.backend.error;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class StringSearch {

    private static Set<String> result = new HashSet<>();

    public static Set<String> permutations(String text) {
        permutations("", text);
        return result;
    }

    private static void permutations(String prefix, String text) {
        int n = text.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutations(prefix + text.charAt(i), text.substring(0, i) + text.substring(i + 1, n));
            }
        }
    }

    public static Set<String> combinations(String text) {
        combinations("", text);
        return result;
    }

    private static void combinations(String prefix, String text) {
        result.add(prefix);
        for (int i = 0; i < text.length(); i++) {
            combinations(prefix + text.charAt(i), text.substring(i + 1));
        }
    }

    public static void main(String[] args) {
//        String text = "abc";
//        System.out.println("Permutations: " + permutations(text));
//        result.clear();
//        System.out.println("Combinations: " + combinations(text));
        int[] results = IntStream.of(10,20,30,40,50,60,70)
                .map(value -> value*20)
                .distinct()
                .sorted()
                .sequential()
                .toArray();
        System.out.println(Arrays.toString(results));
    }
}
