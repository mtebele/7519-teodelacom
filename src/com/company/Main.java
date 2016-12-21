package com.company;

import com.sun.tools.javac.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Compresor\n\n");

        boolean end = false;
        String filename;
        while (!end) {
            System.out.println("\nQue desea hacer?\nEscriba COMPRIMIR para comprimir un archivo.\n" +
                    "Escriba ARMAR HAMMING para construir el codigo Hamming de un archivo a transmitir.\n" +
                    "Escriba DECODIFICAR para decodficar el codigo Hamming de un archivo ya transmitido.\n" +
                    "Escriba DESCOMPRIMIR para descomprimir un archivo.\n" +
                    "Escriba SALIR para salir de la aplicacion.\n\n");

            Scanner s = new Scanner(System.in);
            String command = s.nextLine();
            while (command.equals("")) {
                command = s.nextLine();
            }
            switch (command) {
                case "COMPRIMIR":
                    System.out.println("Desea usar HUFFMAN o SHANNON? Escriba el nombre del algoritmo a usar o VOLVER para regresar al menu original:\n");
                    String comComprimir = s.nextLine();
                    while (!comComprimir.equals("HUFFMAN") && !comComprimir.equals("SHANNON") && !comComprimir.equals("VOLVER")) {
                        System.out.println("COMANDO INVALIDO\nIngrese el comando nuevamente (HUFFMAN/SHANNON/VOLVER):\n");
                        comComprimir = s.nextLine();
                    }
                    if (comComprimir.equals("HUFFMAN") || comComprimir.equals("SHANNON")) {
                        System.out.println("Ingrese el nombre del archivo a comprimir: ");
                        filename = s.nextLine();
                        HashMap<Integer, Integer> probTable = ProbabillityCounter.getProbabilityTable(filename);
                        HashMap<Integer, String> codeTable;

                        if (comComprimir.equals("HUFFMAN")) {
                            codeTable = Huffman.generateInstantCode(probTable);
                        } else {
                            Pair<HashMap<Integer, Integer>, Boolean> result = Shannon.getLengthTable(probTable);
                            HashMap<Integer, Integer> lengthTable = result.fst;
                            // Chequeo si es compacto
                            if (result.snd) {
                                System.out.println("El código es compacto.");
                            }
                            codeTable = Shannon.generateInstantCode(lengthTable);
                        }

                        CodingUtils.translateIntoOutputFile(filename, codeTable);
                        CodingUtils.saveTable(codeTable);

                    }
                    break;
                case "ARMAR HAMMING":
                    //ACA VAN LOS PASOS PARA ARMAR EL OUTPUT DE HAMMING.
                    System.out.println("Ingrese el nombre del archivo a ser codificado usando HAMMING: ");
                    filename = s.nextLine();
                    CodingUtils.hammingEncode(filename);
                    break;
                case "DECODIFICAR":
                    //ACA VAN LOS PASOS PARA RESTAURAR EL ARCHIVO DESDE EL OUTPUT DE HAMMING.
                    System.out.println("Ingrese el nombre del archivo a ser decodificado: ");
                    filename = s.nextLine();
                    CodingUtils.hammingDecode(filename);
                    break;
                case "DESCOMPRIMIR":
                    System.out.println("Ingrese el nombre del archivo a descomprimir: ");
                    filename = s.nextLine();

                    HashMap<Integer, String> codeTable = CodingUtils.loadTable();

                    //ACA VAN LOS PASOS PARA DESCOMPRIMIR
                    HashMap<String, Integer> binaryCharTable = CodingUtils.generateBinaryCharTable(CodingUtils.loadTable());
                    String binaryCode = CodingUtils.translateToBinaryString(filename);
                    String sourceString = CodingUtils.decode(binaryCode, binaryCharTable);

                    //GUARDO DECODEADO A ARCHIVO
                    BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
                    out.write(sourceString);  //Replace with the string
                    //you are trying to write
                    out.close();

                    break;
                case "SALIR":
                    System.out.println("Saliendo...");
                    end = true;
                    break;
                default:
                    System.out.println("COMANDO INVALIDO\n\n");
                    break;
            }
        }

        /*
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
        /*HashMap<Integer, String> codeTable = Huffman.generateInstantCode(probTable);
        CodingUtils.translateIntoOutputFile("testFile.txt", codeTable);
        CodingUtils.saveTable(codeTable);
        CodingUtils.hammingEncode("OUTtestFile.txt");

        //HUFFMAN DECODE
        CodingUtils.hammingDecode("OUTtestFile.txt");
        HashMap<String, Integer> binaryCharTable = CodingUtils.generateBinaryCharTable(CodingUtils.loadTable());
        String binaryCode = CodingUtils.translateToBinaryString("OUTtestFile.txt");
        String sourceString = CodingUtils.decode(binaryCode,binaryCharTable);
        */
        //SHANNON ENCODE
        /*HashMap<Integer, Integer> lengthTable = Shannon.getLengthTable(probTable);
        HashMap<Integer, String> codeTable = Shannon.generateInstantCode(lengthTable);
        CodingUtils.translateIntoOutputFile("testFile.txt", codeTable);
        CodingUtils.saveTable(codeTable);
        CodingUtils.hammingEncode("OUTtestFile.txt");

        //SHANNON DECODE
        CodingUtils.hammingDecode("OUTtestFile.txt");
        HashMap<String, Integer> binaryCharTable = CodingUtils.generateBinaryCharTable(CodingUtils.loadTable());
        String binaryCode = CodingUtils.translateToBinaryString("OUTtestFile.txt");
        String sourceString = CodingUtils.decode(binaryCode,binaryCharTable);*/

    }
}
