package com.company;

import java.io.*;
import java.util.HashMap;

/**
 * Created by mtebele on 7/12/16.
 */
public class ShannonUtils {

    private static final String TEMP_TABLE_NAME = "temp_table";

    public static HashMap<Integer, Integer> getLengthTable(HashMap<Integer, Integer> probTable) {

        HashMap<Integer, Integer> lengthTable = new HashMap<Integer, Integer>();

        int totalCount = probTable.get(-1);

        for (int i = 0; i < probTable.size() - 1; i++) {
            double probability = (double) probTable.get(i) / totalCount;
            if (probability > 0) {
                int length = (int) Math.ceil(-log(probability, 2));
                lengthTable.put(i, length);
            } else {
                lengthTable.put(i, 0);
            }
        }

        return lengthTable;
    }

    private static double log(double x, int base) {
        return Math.log(x) / Math.log(base);
    }

    public static void saveTable(HashMap hash) throws IOException {
        File file = new File(TEMP_TABLE_NAME);
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(hash);
        s.close();
    }

    public static HashMap loadTable() throws IOException, ClassNotFoundException {
        File file = new File(TEMP_TABLE_NAME);
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        HashMap fileObj = (HashMap) s.readObject();
        s.close();
        return fileObj;
    }
}
