package com.cafeform.algorithm;

/**
 *
 * @author kaizawa
 */
public abstract class SearchAlgorithm {
    protected Node startNode;
    protected Node goalNode;
    
    public abstract SearchResult doSearch();

}
