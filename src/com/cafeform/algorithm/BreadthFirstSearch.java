package com.cafeform.algorithm;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author kaizawa
 */
class BreadthFirstSearch extends SearchAlgorithm {

    LinkedBlockingDeque<ArrayList> foundNodeQueue = new LinkedBlockingDeque<>();
    List<ArrayList> goaledPathList = new ArrayList<>();
    Set<Node> foundNodeSet = new HashSet<>(); // Found, but has not children search 

    public BreadthFirstSearch(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
    }

    @Override
    public SearchResult doSearch() {
        // List of Node which represents path.
        ArrayList<Node> currentPath = new ArrayList<>();
        currentPath.add(startNode);
        
        foundNodeQueue.add(currentPath);

        while(false == foundNodeQueue.isEmpty()){
            Node currentNode = currentPath.get(currentPath.size()-1);

            if (currentNode == goalNode) {
                goaledPathList.add(currentPath);
            } else {
                for (Node child : (Set<Node>) currentNode.getChildrenMap().keySet()) {
                    //Check if it has already been found
                    if (foundNodeSet.contains(child)) {
                        // This node has ever been found.
                        continue;
                    }
                    ArrayList childPath = (ArrayList) currentPath.clone();
                    childPath.add(child);

                    // Put child path to the gray queue.
                    foundNodeQueue.add(childPath);
                    foundNodeSet.add(child);
                }
            }
            currentPath = foundNodeQueue.poll();
        } 
        // Return path which found first        
        List<Node> firstPath = goaledPathList.isEmpty() ? Collections.emptyList() : goaledPathList.get(0);
        return new SearchResult(firstPath);
    }
}
