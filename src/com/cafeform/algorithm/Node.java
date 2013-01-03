package com.cafeform.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kaizawa
 */
public class Node<V> {
    /* Node and Cost pare */
    private Map<Node, Integer> childrenCostMap = new LinkedHashMap<>();
    private V value;
    private final String name;
    private final Integer heuristic;

    public V getValue() {
        return value;
    }

    public Integer getHeuristic() {
        return heuristic;
    }

    public String getName() {
        return name;
    }
    
    public Node (String name, Integer heuristic){
        this.name = name;
        this.heuristic = heuristic;
    }
    
    public Map<Node, Integer> getChildrenCostMap(){
        return childrenCostMap;
    }
    
    public void addChild(Node childNode, Integer cost){
        childrenCostMap.put(childNode, cost);
    }      
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(name).append("[Heuristic:").append(heuristic).append(",Children:");
        boolean isFirstChild = true;
        for(Node child : childrenCostMap.keySet()){
            if(isFirstChild){
                isFirstChild = false;
            } else {
                str.append(",");
            }
            str.append(child.getName()).append("(").append(childrenCostMap.get(child)).append(")");
        }
        str.append("]");
        
        return str.toString();
    }
}
