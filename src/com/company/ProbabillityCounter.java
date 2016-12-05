package com.company;
import java.io.*;
import java.util.HashMap;

public class ProbabillityCounter {

    public void getProbabilityTable(String filename) throws IOException {

        FileInputStream in = null;

        try {
            in = new FileInputStream(filename);

            HashMap<Integer,Integer> probTable = new HashMap<Integer,Integer>();

            probTable.put(-1, in.available());
            for (int i = 0; i < 256; i++) {
                probTable.put(i,0);
            }

            int c;
            while ((c = in.read()) != -1) {
                int newProb = probTable.get(c) + 1;
                probTable.put(c, newProb);
            }

            for (int i = -1; i < 256; i++) {
                System.out.println(i+" - "+probTable.get(i));
            }

        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
