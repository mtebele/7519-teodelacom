package com.company.compressors.huffman;

import java.util.Comparator;

/**
 * Created by mati on 05/12/16.
 *
 * Used to compare nodes in PriorityQueue
 */
public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getFrequency() - o2.getFrequency();
    }
}
