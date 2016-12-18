package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by mati on 10/12/16.
 */
public class CodingUtils {

    private static final String TEMP_TABLE_NAME = "temp_table";
    private static final String TEMP_TREE_NAME = "temp_tree";

    private static final String HAMMING_COMMAND_DIR = "../tcd_Hamming/";
    private static final String HAMMING_COMMAND_LINUX = "tdc_Hamming.linux.exe";
    private static final String HAMMING_COMMAND_WIN32 = "tdc_Hamming.win32.exe";
    private static final String HAMMING_COMMAND_WIN64 = "tdc_Hamming.win64.exe";

    public static void translateIntoOutputFile(String filename, HashMap<Integer, String> codeTable) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        String full = "";

        try {
            in = new FileInputStream(filename);
            out = new FileOutputStream("OUT"+filename);

            String buffer = "";

            int c;
            while ((c = in.read()) != -1) {
                buffer = buffer + codeTable.get(c);

                while (buffer.length() >= 8) {
                    String outputByte = buffer.substring(0,8);
                    full += outputByte;
                    out.write(Integer.parseInt(outputByte, 2));
                    buffer = buffer.substring(8,buffer.length());
                }
            }

            buffer = buffer + codeTable.get(256);
            while (buffer.length() >= 8) {
                String outputByte = buffer.substring(0,8);
                full += outputByte;
                out.write(Integer.parseInt(outputByte, 2));
                buffer = buffer.substring(8,buffer.length());
            }
            if(buffer.length() > 0) {
                while (buffer.length() < 8) {
                    buffer = buffer + "0";
                }
                full += buffer;
                out.write(Integer.parseInt(buffer, 2));
            }

        } finally {
            if (in != null) {
                in.close();
                out.close();
            }
        }

    }

    public static String translateToBinaryString(String filename) throws IOException{
        FileInputStream in = null;

        try {
            in = new FileInputStream(filename);

            String buffer = "";

            int c;
            while ((c = in.read()) != -1) {
                String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
                buffer = buffer + binaryChar;
            }

            return buffer;

        } finally {
            if (in != null) {
                in.close();
            }
        }

    }

    //takes a codeTable and returns a Hashmap with codeTable's values as keys and codeTable's keys as values
    public static HashMap<String, Integer> generateBinaryCharTable(HashMap<Integer,String> codeTable){
        HashMap<String,Integer> binaryCharTable = new HashMap<>();

        for (Map.Entry<Integer,String> keyPair :  codeTable.entrySet()){
            binaryCharTable.put(keyPair.getValue(),keyPair.getKey());
        }

        return binaryCharTable;
    }

    //decode the binary text using a binary --> char table
    public static String decode(String binary, HashMap<String,Integer> binaryToCharTable){
        StringBuilder decodedTextBuilder = new StringBuilder("");
        StringBuilder binaryCodeBuilder = new StringBuilder("");
        Integer currentChar = -1;
        Integer i = 0;

        while(i < binary.length() && currentChar != 256){
            binaryCodeBuilder.append(binary.charAt(i));
            if(binaryToCharTable.containsKey(binaryCodeBuilder.toString())){
                currentChar = binaryToCharTable.get(binaryCodeBuilder.toString());
                if(currentChar != 256){
                    decodedTextBuilder.append((char)currentChar.byteValue());
                }
                //reset binary code
                binaryCodeBuilder = new StringBuilder("");
            }
            i++;
        }

        return decodedTextBuilder.toString();
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

    //HAMMING

    public static void hammingEncode(String filename) throws Exception {
        String hammingCommand = getHammingCommand();
        checkFileExists(filename);
        hammingCommand += " -e " + filename;
        runCommand(hammingCommand);
    }

    private static String getHammingCommand(){
        String os = System.getProperty("os.name");
        String arch =  System.getProperty("os.arch");

        String command = HAMMING_COMMAND_LINUX;

        if(os.matches("(.*)(W|w)indows(.*)")){
            command = HAMMING_COMMAND_WIN32;
            if(arch.contains("64")){
                command = HAMMING_COMMAND_WIN64;
            }
        }

        return HAMMING_COMMAND_DIR + command;
    }

    private static void runCommand(String hammingCommand) throws Exception {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec(hammingCommand);
        p.waitFor();
        if(p.exitValue() != 0){
            throw new Exception("Error: Failed Hamming encode!");
        }
    }

    private static void checkFileExists(String filePath) throws Exception{
        File f = new File(filePath);
        if(!f.exists()) {
            throw new Exception(filePath + " not found!");
        }
    }
}
