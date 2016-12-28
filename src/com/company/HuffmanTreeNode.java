package com.company;

import java.io.Serializable;

/**
 * Created by mati on 05/12/16.
 */
public class HuffmanTreeNode implements Serializable{
    private Integer asciiCharCode;
    private Integer frequency;
    private Integer order;
    private HuffmanTreeNode rightNode;
    private HuffmanTreeNode leftNode;

    public HuffmanTreeNode(Integer asciiCharCode, Integer frequency, Integer order, HuffmanTreeNode rightNode, HuffmanTreeNode leftNode){
        this.asciiCharCode = asciiCharCode;
        this.frequency = frequency;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
        this.order = order;
    }

    public Integer getAsciiCharCode() {return this.asciiCharCode;}
    public Integer getFrequency(){
        return this.frequency;
    }
    public HuffmanTreeNode getRightNode(){return this.rightNode;}
    public HuffmanTreeNode getLeftNode(){return this.leftNode;}
    public Integer getOrder() {return this.order;}

    public boolean isLeaf(){
        return (this.rightNode == null && this.leftNode == null);
    }


}
