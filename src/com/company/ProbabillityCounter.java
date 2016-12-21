package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ProbabillityCounter {

    public static HashMap<Integer, Integer> getProbabilityTable(String filename) throws IOException {

        FileInputStream in = null;

        try {
            in = new FileInputStream(filename);

            HashMap<Integer, Integer> probTable = new HashMap<Integer, Integer>();

            probTable.put(-1, in.available());

            int c;
            while ((c = in.read()) != -1) {
                if (!probTable.containsKey(c)) {
                    probTable.put(c, 1);
                } else {
                    int newProb = probTable.get(c) + 1;
                    probTable.put(c, newProb);
                }
            }

            probTable.put(256, 1);
            probTable.put(-1, probTable.get(-1) + 1);

            return probTable;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
