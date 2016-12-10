package com.company;

/**
 * Created by mati on 05/12/16.
 */
public class HuffmanTreeNode {
    private Integer asciiCharCode;
    private Integer frequency;
    private HuffmanTreeNode rightNode;
    private HuffmanTreeNode leftNode;

    public HuffmanTreeNode(Integer asciiCharCode, Integer frequency, HuffmanTreeNode rightNode, HuffmanTreeNode leftNode){
        this.asciiCharCode = asciiCharCode;
        this.frequency = frequency;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }

    public Integer getAsciiCharCode() {return this.asciiCharCode;}
    public Integer getFrequency(){
        return this.frequency;
    }
    public HuffmanTreeNode getRightNode(){return this.rightNode;}
    public HuffmanTreeNode getLeftNode(){return this.leftNode;}

    public boolean isLeaf(){
        return (this.rightNode == null && this.leftNode == null);
    }


}
