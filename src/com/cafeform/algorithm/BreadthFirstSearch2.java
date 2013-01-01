package com.cafeform.algorithm;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Another implementation of bredth first search.
 * Uses color and predecesser HashMap to check the status of each node
 *  color : Back=Found, Gray=Found, but not child search, White=Not found
 *  predeccessor : parent node
 * 
 * @author kaizawa
 */
class BreadthFirstSearch2 extends SearchAlgorithm {
    LinkedBlockingDeque<Node> foundNodeQueue = new LinkedBlockingDeque<>();
    HashSet<Node> foundNodeSet = new HashSet<>();
    HashMap<Node, Node> predeccessorMap = new HashMap(); 
    
    public BreadthFirstSearch2(List<Node> nodeList, Node startNode, Node goalNode) {
        super(nodeList, startNode, goalNode);
    }
    
    @Override
    public SearchResult doSearch() {
        predeccessorMap.put(startNode, null);
        foundNodeQueue.add(startNode);
            
        while(false == foundNodeQueue.isEmpty()){
            Node currentNode = foundNodeQueue.poll();

            if (currentNode == goalNode) {
                break;
            } else {
                for (Node child : (Set<Node>) currentNode.getChildrenMap().keySet()) {
                    time.incrementAndGet();
                    //Check if it has already been found
                    if(foundNodeSet.contains(child)){
                        // This node has ever been found.
                        continue;
                    }

                    // Put child node to the gray queue.
                    foundNodeQueue.add(child);
                    // Set currentNode as parent of child node
                    predeccessorMap.put(child, currentNode);
                    // Set color of child node to GRAY
                    foundNodeSet.add(child);
                }
            }
        } 

        // Return path which found first        
        return new SearchResult(getShortstPathFromGloal(), "BreadthFirstSearch2", time.get());
    }
    
    public List<Node> getShortstPathFromGloal(){
        ArrayList<Node> shortestPath = new ArrayList<>();
        
        for(Node node = goalNode; null != node; node = predeccessorMap.get(node)){
            shortestPath.add(0, node);
        }        
        return shortestPath;
    }    
}
