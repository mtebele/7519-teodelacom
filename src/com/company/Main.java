package com.company;

import com.company.compressors.huffman.Compressor;
import com.company.compressors.huffman.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ProbabillityCounter probCounter = new ProbabillityCounter();
        HashMap<Integer,Integer> probTable = probCounter.getProbabilityTable("testFile.txt");

        /*for (int i = -1; i < 256; i++) {
            System.out.println(i+" - "+probTable.get(i));
        }*/
        FileInputStream in = null;
        in = new FileInputStream("testFile.txt");
        Compressor huffmanCompressor = new Compressor(probTable);
        BitSet compressedText = huffmanCompressor.compress(in);
    }
}
