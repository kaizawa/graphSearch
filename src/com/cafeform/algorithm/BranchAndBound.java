package com.cafeform.algorithm;

import java.util.*;

/**
 * Implementation of Branch and Bound algorithm
 * @author kaizawa
 */
public class BranchAndBound extends SearchAlgorithm {
    HashMap<Node, Integer> nodeCostMap = new HashMap<>();
    HashMap<Node, Node> predeccessorMap = new HashMap(); 
    HashSet<Node> foundNodeSet = new HashSet<>();    
            
    /**
     * Queue that priolitized by cost of node calculated so far.
     * Lowest cost node is at the top of this queue.
     */
    PriorityQueue<Node> lowestCostFirstNodeQueue = new PriorityQueue<>(10, new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            // node must be exist in nodeCostMpa before putting into lowestCostFirstNodeQueue
            Integer cost1 = nodeCostMap.get(node1);
            Integer cost2 = nodeCostMap.get(node2);                
            
            if(cost1 > cost2){
                return 1;
            } else if (cost1 < cost2){
                return -1;
            } else {
                return 0;
            }
        }
    });

    public BranchAndBound (List<Node> nodeList, Node startNode, Node goalNode) {
        super(nodeList, startNode, goalNode);
    }
    
    @Override
    public SearchResult doSearch() {

        nodeCostMap.put(startNode, 0);                        
        lowestCostFirstNodeQueue.add(startNode);
        predeccessorMap.put(startNode, null);
        
        while(false == lowestCostFirstNodeQueue.isEmpty()){
            time.incrementAndGet();
            Node currentNode = lowestCostFirstNodeQueue.poll();
            System.out.println("Cost:" + nodeCostMap.get(currentNode) + " " + currentNode);
            /**
             * Found goal.
             */
            if(currentNode == goalNode){
                break;
            }
            for(Node child: (Set<Node>)currentNode.getChildrenCostMap().keySet()){
                time.incrementAndGet();
                Integer cost = nodeCostMap.get(currentNode)
                        + (Integer)currentNode.getChildrenCostMap().get(child);                
                /**
                 * In case child is already in queue
                 */
                if(lowestCostFirstNodeQueue.contains(child)){
                    Integer prevCost = nodeCostMap.get(child);
                    if(cost < prevCost) {
                        // total cost of new path is lower than the cost of 
                        // previous path. Remove from queue.                      
                        lowestCostFirstNodeQueue.remove(child);
                        predeccessorMap.put(child, currentNode);
                        nodeCostMap.put(child, cost);
                        // put child to queue                
                        lowestCostFirstNodeQueue.add(child);                          
                    } else {
                        // previous cost of path is lower.
                        continue;
                    }
                } else if(false == foundNodeSet.contains(child)){
                    predeccessorMap.put(child, currentNode);
                    nodeCostMap.put(child, cost);
                    // put child to queue                
                    lowestCostFirstNodeQueue.add(child);                      
                }
            }
            foundNodeSet.add(currentNode);
        }
        return new SearchResult(getShortstPathFromGloal(), "BranchANdBound", time.get());
    }
        
    public List<Node> getShortstPathFromGloal(){
        ArrayList<Node> shortestPath = new ArrayList<>();
        
        for(Node node = goalNode; null != node; node = predeccessorMap.get(node)){
            shortestPath.add(0, node);
        }        
        return shortestPath;
    }         
}
