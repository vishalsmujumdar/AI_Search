package ai.search;

import java.util.Comparator;
import java.util.PriorityQueue;

import ai.graph.MapGraph;

public class GreedyBestFirst extends Search{
//	Initialize a priority queue of paths with the one-node path consisting of the initial state
//	While (queue not empty)
//	    Remove path at root (which will be of min cost)
//	    If last node on path matches goal, return path
//	    Else extend the path by one node in all possible ways,
//	        by generating successors of the last node on the path
//	    Foreach successor path succ
//	        Heuristically estimate remaining distance (h-hat) to goal from last node on succ
//	        Insert succ on queue and re-heapify
//	            using ESTIMATED REMAINING DISTANCE TO GOAL as the priority
//	Return FAIL
	
	public GreedyBestFirst() {
		// TODO Auto-generated constructor stub
	}
	
	public GreedyBestFirst(MapGraph adj, String source, String destination){
		super(adj, source, destination);
	}
	
	public void runGBF(){
		parent.put(source, null);
		pathCosts.put(source, 0.0);
		Comparator<String> comparator = setComparatorFunction("heuristicCost");
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
    				}
    			}	
			}
        }        
	}
	
	public double heuristicCost(String vertex){
		double Lat1 = this.adj.vertexCoordinates.get(vertex)[0];
		double Long1 = this.adj.vertexCoordinates.get(vertex)[1];
		double Lat2 = this.adj.vertexCoordinates.get(destination)[0];
		double Long2 = this.adj.vertexCoordinates.get(destination)[1];
		double cost = Math.sqrt(
				Math.pow((69.5 * (Lat1 - Lat2)),2) + 
				Math.pow((69.5 * Math.cos((Lat1 + Lat2)/360 * Math.PI) * (Long1 - Long2)),2));
		return cost;
	}
	
}