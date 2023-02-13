package com.mongo.backend.model.utils;

import java.util.Arrays;
import java.util.List;

import static com.mongo.backend.model.ModelConstants.STRING_MATCHING_THRESHOLD;

public class StringMatcher {
    final String text = "Iphone moble 16";
    List<String> db = Arrays.asList("Apple Iphone 14", "Apple Mobile Phone", "Iphone14");

    public static String lcsPrint(String X, String Y, int m, int n, int[][] lookup) {
        if (m == 0 || n == 0) {
            return new String();
        }
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return lcsPrint(X, Y, m - 1, n - 1, lookup) + X.charAt(m - 1);
        }
        if (lookup[m - 1][n] > lookup[m][n - 1]) {
            return lcsPrint(X, Y, m - 1, n, lookup);
        } else {
            return lcsPrint(X, Y, m, n - 1, lookup);
        }
    }

    public static int lcsFind(String X, String Y, int m, int n, int[][] lookup) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1))
                    lookup[i][j] = lookup[i - 1][j - 1] + 1;
                else
                    lookup[i][j] = Integer.max(lookup[i - 1][j], lookup[i][j - 1]);

            }
        }
        return lookup[m][n];
    }

    public static boolean matcherInclude(String text, String target) {
        int n = text.length(), m = target.length();
        int[][] lookup = new int[m + 1][n + 1];
        StringMatcher matcher = new StringMatcher();
        int length = matcher.lcsFind(target, text, m, n, lookup);
        String results = matcher.lcsPrint(target, text, m, n, lookup);
//        System.out.println(results);
//        System.out.println(n+"    "+length);
        if((double)length/n > STRING_MATCHING_THRESHOLD)
            return true;
        return false;
    }

    public static void main(String[] args) {
        String Y = "APPLE IPHONE 14", X = "IPQWESNE 16";
        System.out.println(matcherInclude(X, Y));
    }
}
