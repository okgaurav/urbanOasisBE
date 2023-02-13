package com.mongo.backend.error;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String text = "abc";
        System.out.println("Permutations: " + permutations(text));
        result.clear();
        System.out.println("Combinations: " + combinations(text));
    }
}