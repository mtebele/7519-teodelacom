package com.company;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Shannon {

   public static HashMap<Integer, String> generateInstantCode(HashMap<Integer, Integer> lengthTable) {

        HashMap<Integer, String> codeTable = new HashMap<>();

        HashMap<Integer, LinkedList<String>> subSets = new HashMap<>();

        char binary[] = {'0', '1'};

        lengthTable.values().forEach((Integer a) -> {
            if (!subSets.containsKey(a)) {
                subSets.put(a, printAllKLength(binary, a));
            }
        });

        lengthTable.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach((Map.Entry<Integer, Integer> entry) -> {
                    String code = subSets.get(entry.getValue()).pop();
                    codeTable.put(entry.getKey(), code);
                    subSets.forEach((Integer subkey, LinkedList<String> list) -> {
                        list.removeIf((String str) -> str.startsWith(code));
                    });
                });

        return codeTable;
    }

    private static LinkedList<String> printAllKLength(char set[], int k) {
        int n = set.length;
        LinkedList<String> list = new LinkedList<>();
        printAllKLengthRec(set, "", n, k, list);
        return list;
    }


    private static void printAllKLengthRec(char set[], String prefix, int n, int k, LinkedList<String> list) {

        // Base case: k is 0, print prefix
        if (k == 0) {
            list.add(prefix);
            return;
        }

        // One by one add all characters from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {

            // Next character of input added
            String newPrefix = prefix + set[i];

            // k is decreased, because we have added a new character
            printAllKLengthRec(set, newPrefix, n, k - 1, list);
        }
    }
}
