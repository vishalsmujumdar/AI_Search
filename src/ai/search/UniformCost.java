package ai.search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import ai.graph.MapGraph;

public class UniformCost extends Search{
//	UNIFORM COST SEARCH	
//	Initialize a priority queue of paths with the one-node path consisting of the initial state
//	While (queue not empty)
//	    Remove path at root (which will be of min cost)
//	    If last node on path matches goal, return path
//	    Else extend the path by one node in all possible ways, by generating successors of the last node on the path
//	    Foreach successor path succ
//	        Update path cost to succ
//	        Insert succ on queue and re-heapify
//	            using PATH COST FROM START NODE as the priority
//	    If two or more paths reach the same node, delete all paths except
//	        the one of min cost
//	Return FAIL
	
	public UniformCost() {
		// TODO Auto-generated constructor stub
	}
	
	public UniformCost(MapGraph adj, String source, String destination){
		super(adj, source, destination);
	}
	
	public void runUCS(){
		parent.put(source, null);
		pathCosts.put(source, 0.0);
		Comparator<String> comparator = setComparatorFunction("actualCost");
        PriorityQueue<String> frontier = 
            new PriorityQueue<String>(10, comparator);
        frontier.add(source);
        while(!frontier.isEmpty()){
        	String currentNode = frontier.remove();
        	if(currentNode.equals(destination))
        		break;
        	output.add(currentNode);
        	
            for (Object vertexobj : this.adj.edges.get(currentNode)) {
            	String vertexobjstr = (String)vertexobj;
        		if (!output.contains(vertexobjstr)) {
        			double newPathOfVertex = pathCosts.get(currentNode)+adj.costOf(currentNode, vertexobjstr);
                	if (!frontier.contains(vertexobjstr)) {
                		parent.put(vertexobjstr, currentNode);
        				frontier.add(vertexobjstr);
        				pathCosts.put(vertexobjstr, newPathOfVertex);
    				} else {
    					// Check if new path is smaller. If yes update parent of vertexobjstr and also the pathCost
    					if (newPathOfVertex < pathCosts.get(vertexobjstr)) {
    						parent.replace(vertexobjstr, currentNode);
    						pathCosts.replace(vertexobjstr, newPathOfVertex);
						}
    				}
    			}	
			}
//            System.out.println("-------------------------FRONTIER-----------------------");
//            for (String string : frontier) {
//				System.out.println(string+" - "+actualCost(string));
//			}
        }        
	}
	
	public double actualCost(String vertex){
		double cost = pathCosts.get(parent.get(vertex)) + adj.costOf(parent.get(vertex), vertex);
		return cost;
	}
}
