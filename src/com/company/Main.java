package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        ProbabillityCounter probCounter = new ProbabillityCounter();
        HashMap<Integer, Integer> probTable = probCounter.getProbabilityTable("testFile.txt");

        for (int i = -1; i < 257; i++) {
            if (probTable.containsKey(i)) {
                System.out.println(i + " - " + probTable.get(i));
            }
        }/*

        System.out.println("-----LONGITUDES-----");

        HashMap<Integer, Integer> lengthTable = Shannon.getLengthTable(probTable);
        for (int i = 0; i < 256; i++) {
            if (lengthTable.containsKey(i)) {
                System.out.println(i + " - " + lengthTable.get(i));
            }
        }

        System.out.println("-----CODIGOS-----");

        HashMap<Integer, String> codeTable = Shannon.generateInstantCode(lengthTable);

        for (int i = 0; i < 257; i++) {
            if (codeTable.containsKey(i)) {
                System.out.println(i + " - " + codeTable.get(i));
            }
        }

        CodingUtils.translateIntoOutputFile("testFile.txt", codeTable);

        // Ejemplo de guardar / leer un HashMap
        CodingUtils.saveTable(lengthTable);

        try {
            HashMap<Integer, Integer> newHash = CodingUtils.loadTable();
            System.out.println("-----LONGITUDES LEIDAS-----");
            for (int i = 0; i < 256; i++) {
                if (newHash.get(i) != null && newHash.get(i) != 0) {
                    System.out.println(i + " - " + newHash.get(i));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        //HUFFMAN ENCODE
        HashMap<Integer, String> codeTable = Huffman.generateInstantCode(probTable);
        CodingUtils.translateIntoOutputFile("testFile.txt", codeTable);
        CodingUtils.saveTable(codeTable);
        CodingUtils.hammingEncode("OUTtestFile.txt");

        //HUFFMAN DECODE
        /*HashMap<String, Integer> binaryCharTable = CodingUtils.generateBinaryCharTable(CodingUtils.loadTable());
        String binaryCode = CodingUtils.translateToBinaryString("OUTtestFile.txt");
        String sourceString = CodingUtils.decode(binaryCode,binaryCharTable);*/ 

        //SHANNON ENCODE
        /*HashMap<Integer, Integer> lengthTable = Shannon.getLengthTable(probTable);
        HashMap<Integer, String> codeTable = Shannon.generateInstantCode(lengthTable);
        CodingUtils.translateIntoOutputFile("testFile.txt", codeTable);
        CodingUtils.saveTable(codeTable);

        //SHANNON DECODE
        HashMap<String, Integer> binaryCharTable = CodingUtils.generateBinaryCharTable(CodingUtils.loadTable());
        String binaryCode = CodingUtils.translateToBinaryString("OUTtestFile.txt");
        String sourceString = CodingUtils.decode(binaryCode,binaryCharTable);*/

    }
}
