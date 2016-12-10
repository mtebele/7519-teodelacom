package com.company;

import java.util.*;

/**
 * Created by mati on 05/12/16.
 *
 * HuffmanTree and HuffmanCompressor based in http://algs4.cs.princeton.edu/55compression/Huffman.java.html
 *                                            http://codereview.stackexchange.com/questions/44473/huffman-code-implementation
 */
public class Huffman {

    private static final int CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY = -1;
    private static final int NODE_PARENT_CHAR_VALUE = -2;

    /*
       Initialize code table
     */
    public static HashMap<Integer, String> generateInstantCode(HashMap<Integer, Integer> probTable){
        HashMap<Integer,String> codeTable = new HashMap<>();
        if(probTable != null && probTable.size() > 1){
            HuffmanTreeNode treeRoot = makeTree(probTable);
            if(treeRoot != null){
                buildCodeTable(codeTable, treeRoot, "");
            }
        }

        return codeTable;
    }

    /*
        Creates Huffman Tree with chars frequencies
     */
    private static HuffmanTreeNode makeTree(HashMap<Integer, Integer> charFrequencies)
    {
        //Using priority queue for merging Nodes
        PriorityQueue<HuffmanTreeNode> priorityQueue = new PriorityQueue<>(256, new HuffmanTreeNodeComparator());
        //remove {key: -1} that contains text char count
        charFrequencies.remove(CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY);

        //load priority queue with Nodes whose char's frequency is bigger than zero
        Iterator<Integer> it = charFrequencies.keySet().iterator();
        while(it.hasNext()){
            Integer currentChar = it.next();
            Integer frequency = charFrequencies.get(currentChar);
            if(frequency > 0){
                priorityQueue.add(new HuffmanTreeNode(currentChar, frequency,null,null));
            }
        }

        //if only one caracter then add another extra node with root value and frequency zero
        if(priorityQueue.size() == 1){
            priorityQueue.add(new HuffmanTreeNode(NODE_PARENT_CHAR_VALUE,0,null,null));
        }

        //build tree
        while(priorityQueue.size() > 1){
            HuffmanTreeNode right = priorityQueue.poll();
            HuffmanTreeNode left = priorityQueue.poll();
            HuffmanTreeNode parent = new HuffmanTreeNode(NODE_PARENT_CHAR_VALUE, right.getFrequency() + left.getFrequency(),left,right);
            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    private static void buildCodeTable(HashMap<Integer,String>codeTable, HuffmanTreeNode node, String charCode){
        if(!node.isLeaf()){
            buildCodeTable(codeTable, node.getLeftNode(), charCode + '0');
            buildCodeTable(codeTable, node.getRightNode(), charCode + '1');
        }else{
            codeTable.put(node.getAsciiCharCode(), charCode);
        }
    }



}
