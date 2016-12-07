package com.company.compressors.huffman;

/**
 * Created by mati on 05/12/16.
 */
public class Node {
    private Integer asciiCharCode;
    private Integer frequency;
    private Node rightNode;
    private Node leftNode;

    public Node(Integer asciiCharCode, Integer frequency, Node rightNode, Node leftNode){
        this.asciiCharCode = asciiCharCode;
        this.frequency = frequency;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }

    public Integer getAsciiCharCode() {return this.asciiCharCode;}
    public Integer getFrequency(){
        return this.frequency;
    }
    public Node getRightNode(){return this.rightNode;}
    public Node getLeftNode(){return this.leftNode;}

    public boolean isLeaf(){
        return (this.rightNode == null && this.leftNode == null);
    }


}
