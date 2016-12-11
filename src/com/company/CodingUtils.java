package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by mati on 10/12/16.
 */
public class CodingUtils {

    private static final String TEMP_TABLE_NAME = "temp_table";

    public static void translateIntoOutputFile(String filename, HashMap<Integer, String> codeTable) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(filename);
            out = new FileOutputStream("OUT"+filename);

            String buffer = "";

            int c;
            while ((c = in.read()) != -1) {
                buffer = buffer + codeTable.get(c);

                while (buffer.length() >= 8) {
                    String outputByte = buffer.substring(0,8);
                    out.write(Integer.parseInt(outputByte, 2));
                    buffer = buffer.substring(8,buffer.length());
                }
            }

            buffer = buffer + codeTable.get(256);
            while (buffer.length() >= 8) {
                String outputByte = buffer.substring(0,8);
                out.write(Integer.parseInt(outputByte, 2));
                buffer = buffer.substring(8,buffer.length());
            }
            if(buffer.length() > 0) {
                while (buffer.length() < 8) {
                    buffer = buffer + "0";
                }
                out.write(Integer.parseInt(buffer, 2));
            }

        } finally {
            if (in != null) {
                in.close();
                out.close();
            }
        }

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
