package com.company.huffman;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by mati on 05/12/16.
 *
 * HuffmanTree and HuffmanCompressor based in http://algs4.cs.princeton.edu/55compression/Huffman.java.html
 */
public class HuffmanCoder {

    private final int CHARS_FREQUENCY_TOTAL_CHARS_ENTRY_KEY = -1;
    private final int NODE_ROOT_CHAR_VALUE = -2;

    private AbstractMap<Integer,String> codeTable;

    public HuffmanCoder(AbstractMap<Integer,Integer> charFrequencies){
        initialize(charFrequencies);
    }

    /*
       Initialize code table
     */
    private void initialize(AbstractMap<Integer,Integer> charFrequencies){
        this.codeTable = new HashMap<>();
        if(charFrequencies != null && charFrequencies.size() > 1){
            Node treeRoot = this.makeTree(charFrequencies);
            if(treeRoot != null){
                this.buildCodeTable(treeRoot, "");
            }
        }
    }

    /*
        Creates Huffman Tree with chars frequencies
     */
    private Node makeTree(AbstractMap<Integer,Integer> charFrequencies)
    {
        //Using priority queue for merging Nodes
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(256, new NodeComparator());
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

    private void buildCodeTable(Node node, String charCode){
        if(!node.isLeaf()){
            buildCodeTable(node.getLeftNode(), charCode + '0');
            buildCodeTable(node.getRightNode(), charCode + '1');
        }else{
            this.codeTable.put(node.getAsciiCharCode(), charCode);
        }
    }

    public BitSet encode(FileInputStream inputStream){

        try{
            String compressedTextCode = "";
            int c;
            while((c = inputStream.read()) != -1){
                compressedTextCode += this.codeTable.get(c);
            }
            System.out.println(compressedTextCode);
            //convert compressed text code into bit set
            Integer totalCompressedTextSize = compressedTextCode.length();
            BitSet compressedCodeBinary = new BitSet(totalCompressedTextSize);
            for (int i = 0; i < totalCompressedTextSize; i++){
                if(compressedTextCode.charAt(i) == '1'){
                    compressedCodeBinary.set(i);
                }
            }

            return compressedCodeBinary;
        }catch (Exception ex){
            return new BitSet();
        }
    }

}
