package ai.search;
// Algorithm implementation used from following link under MIT Creatice Commons License
// http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec14.pdf

import java.util.*;

import ai.graph.MapGraph;

public class DFS extends Search{

	public void runDFS(MapGraph adj, String source){
//			DFS (V, Adj)
//			 parent = { }
//			 for s in V:
//			 if s not in parent:
//			 parent [s] = None
//			 DFS-visit (V, Adj, s)
		
		// Adding source node to the beginning of list from it's previous alphabetical position
		adj.vertices.remove(source);
		adj.vertices.add(0, source);
		this.parent = new HashMap<String, String>();
		for (Object vertexobj : adj.vertices) {
			String currentvertex = (String)vertexobj;
			if (!parent.containsKey(currentvertex)) {
				parent.put(currentvertex, null);
				output.add(currentvertex);
				runDFSVisit(currentvertex, adj);
			}
		}
	}
	
	public void runDFSVisit(String source, MapGraph adj){
//		DFS-visit (V, Adj, s):
//		 for v in Adj [s]:
//		 if v not in parent:
//		 parent [v] = s
//		 DFS-visit (V, Adj, v)
		for (Object vertexobj : adj.edges.get(source)) {
			String currentvertex = (String)vertexobj;
			if (!parent.containsKey(currentvertex)) {
				parent.put(currentvertex, source);
				output.add(currentvertex);
				runDFSVisit(currentvertex, adj);
			}
		}
	}
}
