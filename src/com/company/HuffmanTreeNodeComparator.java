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
        if(o1.getFrequency() == o2.getFrequency()){
            return - (o1.getOrder() - o2.getOrder());
        }

        return o1.getFrequency() - o2.getFrequency();
    }
}
