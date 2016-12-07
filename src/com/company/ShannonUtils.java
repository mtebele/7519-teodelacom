package com.company;

import java.util.HashMap;

/**
 * Created by mtebele on 7/12/16.
 */
public class ShannonUtils {
    public static HashMap<Integer, Integer> getLengthTable(HashMap<Integer, Integer> probTable) {

        HashMap<Integer, Integer> lengthTable = new HashMap<Integer, Integer>();

        int totalCount = probTable.get(-1);

        for (int i = 0; i < probTable.size() - 1; i++) {
            double probability = (double) probTable.get(i) / totalCount;
            if (probability > 0) {
                int length = -log(probability, 2);
                lengthTable.put(i, length);
            } else {
                lengthTable.put(i, 0);
            }
        }

        return lengthTable;
    }

    private static int log(double x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }
}
