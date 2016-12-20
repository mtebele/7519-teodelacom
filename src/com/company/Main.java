package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Compresor/Detector de Errores/Descompresor\n\n");
        System.out.println("Que desea hacer?\nEscriba COMPRIMIR para comprimir un archivo.\n" +
                "Escriba ARMAR HAMMING para construir el codigo Hamming de un archivo a transmitir.\n" +
                "Escriba DETECTAR para detectar errores en un codigo Hamming de un archivo ya transmitido.\n" +
                "Escriba DESCOMPRIMIR para descomprimir un archivo.\n\n");

        boolean end = false;
        while (!end) {
            String command = System.console().readLine();
            while (command == "") {
                command = System.console().readLine();
            }
            switch (command) {
                case "COMPRIMIR":
                    System.out.println("Desea usar HUFFMAN o SHANNON? Escriba el nombre del algoritmo a usar o VOLVER para regresar al menu original:\n");
                    String comComprimir = System.console().readLine();
                    while (comComprimir != "HUFFMAN" && comComprimir != "SHANNON" && comComprimir != "VOLVER") {
                        System.out.println("COMANDO INVALIDO\nIngrese el comando nuevamente (HUFFMAN/SHANNON/VOLVER):\n");
                        comComprimir = System.console().readLine();
                    }
                    if (comComprimir == "HUFFMAN" || comComprimir == "SHANNON") {
                        System.out.println("Ingrese el nombre del archivo a comprimir: ");
                        String filename = System.console().readLine();
                        HashMap<Integer,Integer> probTable = ProbabillityCounter.getProbabilityTable(filename);

                        if (comComprimir == "HUFFMAN") {

                        } else {
                            HashMap<Integer, Integer> lengthTable = Shannon.getLengthTable(probTable);
                            HashMap<Integer, String> codeTable = Shannon.generateInstantCode(lengthTable);
                            CodingUtils.translateIntoOutputFile(filename, codeTable);
                            CodingUtils.saveTable(codeTable);
                        }

                        end = true;
                    } else {
                        end = false;
                    }
                    break;
                case "ARMAR HAMMING":
                    //ACA VAN LOS PASOS PARA ARMAR EL OUTPUT DE HAMMING.
                    break;
                case "DETECTAR":
                    //ACA VAN LOS PASOS PARA RESTAURAR EL ARCHIVO DESDE EL OUTPUT DE HAMMING.
                    break;
                case "DESCOMPRIMIR":
                    System.out.println("Ingrese el nombre del archivo a descomprimir: ");
                    String filename = System.console().readLine();

                    HashMap<Integer, String> codeTable = CodingUtils.loadTable();

                    //ACA VAN LOS PASOS PARA DESCOMPRIMIR

                    end = true;

                    break;
                default:
                    System.out.println("COMANDO INVALIDO\n\n");
                    end = false;
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
