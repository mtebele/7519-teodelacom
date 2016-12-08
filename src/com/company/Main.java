package com.company;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ProbabillityCounter probCounter = new ProbabillityCounter();
        HashMap<Integer, Integer> probTable = probCounter.getProbabilityTable("testFile.txt");

        for (int i = -1; i < 257; i++) {
            if (probTable.containsKey(i)) {
                System.out.println(i + " - " + probTable.get(i));
            }
        }

        System.out.println("-----LONGITUDES-----");

        HashMap<Integer, Integer> lengthTable = ShannonUtils.getLengthTable(probTable);
        for (int i = 0; i < 256; i++) {
            if (lengthTable.containsKey(i)) {
                System.out.println(i + " - " + lengthTable.get(i));
            }
        }

        System.out.println("-----CODIGOS-----");

        HashMap<Integer, String> codeTable = ShannonUtils.generateInstantCode(lengthTable);

        for (int i = 0; i < 257; i++) {
            if (codeTable.containsKey(i)) {
                System.out.println(i + " - " + codeTable.get(i));
            }
        }

        ShannonUtils.translateIntoOutputFile("testFile.txt", codeTable);

        // Ejemplo de guardar / leer un HashMap
        /*ShannonUtils.saveTable(lengthTable);

        try {
            HashMap<Integer, Integer> newHash = ShannonUtils.loadTable();
            System.out.println("-----LONGITUDES LEIDAS-----");
            for (int i = 0; i < 256; i++) {
                if (newHash.get(i) != 0) {
                    System.out.println(i + " - " + newHash.get(i));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
