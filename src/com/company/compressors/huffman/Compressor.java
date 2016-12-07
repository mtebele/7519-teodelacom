package com.company.compressors.huffman;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by mati on 05/12/16.
 *
 * HuffmanTree and HuffmanCompressor based in http://algs4.cs.princeton.edu/55compression/Huffman.java.html
 */
public class Compressor {

    private final int CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY = -1;
    private final int NODE_ROOT_CHAR_VALUE = -2;
    private final int ASCII_ZERO = -3;


    private AbstractMap<Integer,String> codeTable;

    public Compressor(AbstractMap<Integer,Integer> charFrequencies){
        initialize(charFrequencies);
    }

    /*
       Initialize code table
     */
    private void initialize(AbstractMap<Integer,Integer> charFrequencies){
        codeTable = null;
        Node tree = this.makeTree(charFrequencies);
    }

    /*
        Creates Huffman Tree with chars frequencies
     */
    private Node makeTree(AbstractMap<Integer,Integer> charFrequencies)
    {
        /*charFrequencies = new HashMap<Integer,Integer>();
        charFrequencies.put(10,2);
        charFrequencies.put(255,0);
        charFrequencies.put(3,0);
        charFrequencies.put(2,15);
        charFrequencies.put(0,50);*/

        //Using priority queue for merging Nodes
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(256, new NodeComparator());
        //remove {key: -1} that contains text char count
        charFrequencies.remove(CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY);

        //load priority queue with Nodes whose char's frequency is bigger than zero
        Iterator<Integer> it = charFrequencies.keySet().iterator();
        while(it.hasNext()){
            Integer currentChar = it.next();
            Integer frequency = charFrequencies.get(currentChar);
            if(frequency > 0){
                priorityQueue.add(new Node(currentChar, frequency,null,null));
            }
        }

        //if only one caracter then add another extra node with root value and frequency zero
        if(priorityQueue.size() == 1){
            priorityQueue.add(new Node(NODE_ROOT_CHAR_VALUE,0,null,null));
        }

        //build tree
        while(priorityQueue.size() > 1){
            Node right = priorityQueue.poll();
            Node left = priorityQueue.poll();
            Node parent = new Node(NODE_ROOT_CHAR_VALUE, right.getFrequency() + left.getFrequency(),left,right);
            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }
}
