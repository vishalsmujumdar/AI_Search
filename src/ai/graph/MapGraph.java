package ai.graph;
import java.util.*;

public class MapGraph {
	public List<String> vertices = new ArrayList<String>();
	public HashMap<String, float[]> vertexCoordinates = new HashMap<String, float[]>();
	public HashMap<String, LinkedList> edges = new HashMap<String, LinkedList>();
	public ArrayList<ArrayList<String>> edgeCosts = new ArrayList<ArrayList<String>>();
    
	// Data structure that will store the cost of all edges.
	
	public int costOf(String startVertex, String endVertex){
		int cost = 0;
		for (ArrayList<String> path : this.edgeCosts) {
			if (path.get(0).equals(startVertex) && path.get(1).equals(endVertex)) {
				cost = Integer.parseInt(path.get(2));
			}
		}
		return cost;
	}
	
	public void normalizeAdjacencyMatrix(){
		for (String vertex : this.vertices) {
			Iterator<String> nodes = this.edges.keySet().iterator();
//			Iterator<Edge> edges = vertex.edges.iterator();
//			while(edges.hasNext()){
//				Vertex toVertex = edges.next().destination;
//				List<Edge> toEdges = toVertex.edges;
//				if(!toEdges.contains(vertex)){
//					toVertex.edges.add(new Edge(vertex, vertex.costOf(toVertex)));
//				}
//			}
			while(nodes.hasNext()){
				String nodekey = nodes.next();
				LinkedList consecutivenodeslist = this.edges.get(nodekey);
				if(consecutivenodeslist.contains(vertex)){
					if(!this.edges.get(vertex).contains(nodekey))
						this.edges.get(vertex).add(nodekey);
				}
			}
		}
		for (String vertex : this.edges.keySet()) {
			LinkedList<String> consecutivenodes = this.edges.get(vertex);
			Collections.sort(consecutivenodes, new Comparator<String>() {
			    public int compare(String node1, String node2) {
			          return node1.compareTo(node2);
			    }
			});
			this.edges.replace(vertex, consecutivenodes);
		}
		ArrayList<ArrayList<String>> reversePathCosts = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> path : edgeCosts) {
			ArrayList<String> reverseToFromCost = new ArrayList<String>(){{
				add(path.get(1));
				add(path.get(0)); 
				add(path.get(2));	
			}};
			reversePathCosts.add(reverseToFromCost);
		}
		edgeCosts.addAll(reversePathCosts);
	}
}
