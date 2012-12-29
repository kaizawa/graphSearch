package com.cafeform.algorithm;

import java.util.List;

/**
 *
 * @author kaizawa
 */
public class SearchResult {
    List<Node> path;
    
    public SearchResult(List<Node> path){
        this.path = path;
    }
    
    public Integer getCost(){
        Integer cost= 0;
        Node prevNode = null;
        for(Node node : path){
            if(null == prevNode ){
                continue;
            }
            cost += (Integer)prevNode.getChildrenMap().get(node);
            prevNode = node;
        }
        return cost;
    }

    void showResults() {
        StringBuilder outStr = new StringBuilder();

        outStr.append("Cost:[").append(getCost()).append("] ");
        boolean firstNode = true;
        
        for(Node node : path){
            if(firstNode){
                firstNode=false;
            } else {
                outStr.append("->");
            }
            outStr.append(node.getName()).append("[h:").append(node.getHeuristic())
                    .append("]");
        }
        System.out.println(outStr);
    }
}
