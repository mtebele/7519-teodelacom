package com.company;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ProbabillityCounter probCounter = new ProbabillityCounter();
        HashMap<Integer, Integer> probTable = probCounter.getProbabilityTable("testFile.txt");

        for (int i = -1; i < 256; i++) {
            System.out.println(i + " - " + probTable.get(i));
        }

        System.out.println("-----LONGITUDES-----");

        HashMap<Integer, Integer> lengthTable = probCounter.getLengthTable(probTable);
        for (int i = 0; i < 256; i++) {
            if (lengthTable.get(i) != 0) {
                System.out.println(i + " - " + lengthTable.get(i));
            }
        }
    }
}
