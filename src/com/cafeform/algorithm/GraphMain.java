package com.cafeform.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kaizawa
 */
public class GraphMain {

    private static final String [] nodeNameArray 
            = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static final Integer [] nodeHeuristicArray 
            = {0, 7, 4, 6 , 1, 2, 3, 4, 2, 0};
    private static final Integer[][] nodeChildrenArray 
            = {{1, 2}, {2, 6}, {3, 6, 7}, {4, 7, 8}, {8, 9}, {1}, {5, 7}, {8, 9}, {9}, {}};
    private static final Integer[][] nodeChildrenCostArray 
            = {{1, 3}, {1, 6}, {6, 6, 3}, {5, 2, 4}, {2, 1}, {1}, {7, 2}, {3, 7}, {5}, {}};    
    private static Node[] nodeArray = new Node[nodeNameArray.length];
    private static Node startNode;
    private static Node goalNode;
    private static List<Node> nodeList;

    public static void main(String[] args) {
        new GraphMain().start();
    }
    
    private void start(){
        createGraph();        
        new BreadthFirstSearch(nodeList, startNode, goalNode).doSearch().showResults();
        new BreadthFirstSearch2(nodeList, startNode, goalNode).doSearch().showResults();        
        new DepthFirstSearch(nodeList, startNode, goalNode).doSearch().showResults();                
        new BranchAndBound(nodeList, startNode, goalNode).doSearch().showResults();                        
    }

    private void createGraph() {
        for(int i = 0 ; i < nodeNameArray.length ; i ++){
            nodeArray[i] = new Node(nodeNameArray[i], nodeHeuristicArray[i]);
        }
        startNode = nodeArray[0];
        goalNode = nodeArray[9];
        for(int i = 0 ; i < nodeNameArray.length ; i++){
            for(int j = 0 ; j < nodeChildrenArray[i].length; j++){
                Integer child = nodeChildrenArray[i][j];
                if(null == child){
                    continue;
                }
               nodeArray[i].addChild(nodeArray[child], nodeChildrenCostArray[i][j]);
            }
        }  
        nodeList = Arrays.asList(nodeArray);
        System.out.println("V(vertices): " + nodeList.size());
        
        int edgeCount = 0;
        for(Integer[] nodes: nodeChildrenArray){
            for(Integer edge: nodes){
                edgeCount++;
            }
        }
        /*        
        for(Node node : nodeList){
            System.out.println(node);
        }
        */      
        System.out.println("E(edges)" + edgeCount);
        System.out.println("V+E: " + (nodeList.size() + edgeCount));
    }
}
