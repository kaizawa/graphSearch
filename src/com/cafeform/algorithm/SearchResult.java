package com.cafeform.algorithm;

import java.util.List;

/**
 *
 * @author kaizawa
 */
public class SearchResult {
    List<Node> path;
    private final String algorithmName;
    private Integer time;
    
    public SearchResult(List<Node> path, String algorithmName, Integer time){
        this.path = path;
        this.algorithmName = algorithmName;
        this.time = time;
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

        outStr.append(algorithmName).append("\t");
        outStr.append("Distance:[").append(path.size()).append("],");
        outStr.append("Time:[").append(time).append("],");        
        outStr.append("Cost:[").append(getCost()).append("]\t");
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
