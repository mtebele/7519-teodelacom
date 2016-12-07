package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ProbabillityCounter {

    public HashMap<Integer, Integer> getProbabilityTable(String filename) throws IOException {

        FileInputStream in = null;

        try {
            in = new FileInputStream(filename);

            HashMap<Integer, Integer> probTable = new HashMap<Integer, Integer>();

            probTable.put(-1, in.available());
            for (int i = 0; i < 256; i++) {
                probTable.put(i, 0);
            }

            int c;
            while ((c = in.read()) != -1) {
                int newProb = probTable.get(c) + 1;
                probTable.put(c, newProb);
            }

            return probTable;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public HashMap<Integer, Integer> getLengthTable(HashMap<Integer, Integer> probTable) {

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
