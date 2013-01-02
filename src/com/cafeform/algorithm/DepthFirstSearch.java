package com.cafeform.algorithm;

import java.util.*;

/**
 * Implementation of depth first search algorithm.
 * 
 * @author kaizawa
 */
public class DepthFirstSearch extends SearchAlgorithm {
    
    HashSet<Node> foundNodeSet = new HashSet<>();
    HashMap<Node, Node> predeccessorMap = new HashMap(); 
    
    public DepthFirstSearch(List<Node> nodeList, Node startNode, Node goalNode ){
        super(nodeList, startNode, goalNode);
    }

    @Override
    public SearchResult doSearch() {
        predeccessorMap.put(startNode, null);
        foundNodeSet.add(startNode);
        // Recursively searchs path to the goal
        deepSearch(startNode);
        return new SearchResult(getShortstPathFromGloal(), "DepthFirstSearch", time.get());
    }

    private void deepSearch(Node node) {
        time.incrementAndGet();
        if(node == goalNode){
            return;
        }        
        // Check childlen
        for(Node child: (Set<Node>) node.getChildrenCostMap().keySet()){
            time.incrementAndGet();            
            if(foundNodeSet.contains(child)){
                continue;
            }
            predeccessorMap.put(child, node);
            foundNodeSet.add(child);
            // search path from this child first.
            deepSearch(child);
        }
    }
    
    /**
     * Get list of node which represents path.
     */
    public List<Node> getShortstPathFromGloal(){
        ArrayList<Node> shortestPath = new ArrayList<>();
        
        for(Node node = goalNode; null != node; node = predeccessorMap.get(node)){
            shortestPath.add(0, node);
        }        
        return shortestPath;
    }  
}
