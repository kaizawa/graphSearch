package com.cafeform.algorithm;

import java.util.List;

/**
 *
 * @author kaizawa
 */
public abstract class SearchAlgorithm {
    protected Node startNode;
    protected Node goalNode;
    protected List<Node> nodeList;
    
    public SearchAlgorithm(List<Node> nodeList, Node startNode, Node goalNode) {
        this.nodeList = nodeList;
        this.startNode = startNode;
        this.goalNode = goalNode;        
    }    
    
    public abstract SearchResult doSearch();

}
