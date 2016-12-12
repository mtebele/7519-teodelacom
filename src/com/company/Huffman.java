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
    public static HashMap<Integer, String> generateInstantCode(HuffmanTreeNode treeRoot){
        HashMap<Integer,String> codeTable = new HashMap<>();

        if(treeRoot != null){
            buildCodeTable(codeTable, treeRoot, "");
        }

        return codeTable;
    }

    public static String decode(String binaryCode, HuffmanTreeNode nodeRoot) {
        StringBuilder decodedTextBuilder = new StringBuilder("");
        /*int totalChars = probTable.size() - 1; //-1 key should not be taken in count
        HuffmanTreeNode root = makeTree(probTable);*/
        int decodedTextChar = 0;
        int binaryCodeIndex = 0;
        char binaryCodeChar;
        try {
            //iterate through the binary code until int 256 is found (it's added at the end of the file)
            while(decodedTextChar != 256){
                HuffmanTreeNode currentNode = nodeRoot;
                while(!currentNode.isLeaf()){
                        binaryCodeChar = binaryCode.charAt(binaryCodeIndex);
                        if(binaryCodeChar == '1'){
                            currentNode = currentNode.getRightNode();
                        }else{
                            currentNode = currentNode.getLeftNode();
                        }
                        binaryCodeIndex++;
                }

                decodedTextChar = currentNode.getAsciiCharCode();
                if(decodedTextChar != 256){
                    decodedTextBuilder.append((char) decodedTextChar);
                }
            }

            return decodedTextBuilder.toString();
        }catch (Exception e){
            //Error: binaryCodeIndex bigger than binaryCode length
            return "";
        }
    }

    /*
        Creates Huffman Tree with chars frequencies
     */
    public static HuffmanTreeNode makeTree(HashMap<Integer, Integer> charFrequencies)
    {
        HuffmanTreeNode treeRoot;
        if(charFrequencies != null && charFrequencies.size() > 1) {
            //remove {key: -1} that contains text char count
            charFrequencies.remove(CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY);

            //Using priority queue for merging Nodes
            PriorityQueue<HuffmanTreeNode> priorityQueue = new PriorityQueue<>(charFrequencies.size(), new HuffmanTreeNodeComparator());

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

        return null;
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
