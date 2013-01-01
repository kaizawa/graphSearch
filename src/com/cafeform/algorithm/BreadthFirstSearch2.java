package com.cafeform.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
    HashMap<Node, Color> colorMap = new HashMap<>();
    HashMap<Node, Node> predeccessorMap = new HashMap(); 
    
    enum Color {BLACK, WHITE, GRAY};

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
                    //Check if it has already been found
                    if(colorMap.containsKey(child)){
                        // This node has ever been found.
                        continue;
                    }

                    // Put child node to the gray queue.
                    foundNodeQueue.add(child);
                    // Set currentNode as parent of child node
                    predeccessorMap.put(child, currentNode);
                    // Set color of child node to GRAY
                    colorMap.put(child, Color.GRAY);
                }
            }
        } 

        ArrayList<Node> shortestPath = new ArrayList<>();
        
        for(Node node = goalNode; null != node; node = predeccessorMap.get(node)){
            shortestPath.add(0, node);
        }
        
        // Return path which found first        
        return new SearchResult(shortestPath, "BreadthFirstSearch2");
    }
}
