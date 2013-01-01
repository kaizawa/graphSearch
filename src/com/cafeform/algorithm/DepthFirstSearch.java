package com.cafeform.algorithm;

import java.util.*;

/**
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
        deepSearch(startNode);
        return new SearchResult(getShortstPathFromGloal(), "DepthFirstSearch", time.get());
    }

    private void deepSearch(Node node) {
        time.incrementAndGet();
        if(node == goalNode){
            return;
        }        
        for(Node child: (Set<Node>) node.getChildrenMap().keySet()){
            if(foundNodeSet.contains(child)){
                continue;
            }
            predeccessorMap.put(child, node);
            foundNodeSet.add(child);
            deepSearch(child);
        }
    }
    
    public List<Node> getShortstPathFromGloal(){
        ArrayList<Node> shortestPath = new ArrayList<>();
        
        for(Node node = goalNode; null != node; node = predeccessorMap.get(node)){
            shortestPath.add(0, node);
        }        
        return shortestPath;
    }  
}
