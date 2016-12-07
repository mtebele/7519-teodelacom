package com.company.compressors.huffman;

/**
 * Created by mati on 05/12/16.
 */
public class Node {
    private int asciiCharCode;
    private int frequency;
    private Node rightNode;
    private Node leftNode;

    public Node(int asciiCharCode, int frequency, Node rightNode, Node leftNode){
        this.asciiCharCode = asciiCharCode;
        this.frequency = frequency;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }

    public boolean isLeaf(){
        return (this.rightNode == null && this.leftNode == null);
    }

    public int getFrequency(){
        return this.frequency;
    }
}
