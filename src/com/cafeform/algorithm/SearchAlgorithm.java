package com.cafeform.algorithm;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author kaizawa
 */
public abstract class SearchAlgorithm {
    protected Node startNode;
    protected Node goalNode;
    protected List<Node> nodeList;
    protected AtomicInteger time = new AtomicInteger(0); // time to search
    
    public SearchAlgorithm(List<Node> nodeList, Node startNode, Node goalNode) {
        this.nodeList = nodeList;
        this.startNode = startNode;
        this.goalNode = goalNode;        
    }       
    
    public abstract SearchResult doSearch();
}
