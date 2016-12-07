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
}
