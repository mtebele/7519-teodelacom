package com.company;

import java.util.Comparator;

/**
 * Created by mati on 05/12/16.
 *
 * Used to compare nodes in PriorityQueue
 */
public class HuffmanTreeNodeComparator implements Comparator<HuffmanTreeNode>{

    @Override
    public int compare(HuffmanTreeNode o1, HuffmanTreeNode o2) {
        return o1.getFrequency() - o2.getFrequency();
    }
}
