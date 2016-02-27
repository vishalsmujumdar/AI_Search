package ai.search;
// Algorithm implementation used from following link under MIT Creatice Commons License
// http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec13.pdf

import java.util.*;

import ai.graph.MapGraph;

public class BFS extends Search{	
	
	public void runBFS(String source, MapGraph adj){
		// 	set currentNode	to first element in graph
		//	Add to level, Add to parent
		// 	Dequeue and Add to output
		//	Traverse adj matrix to find consecutive nodes and add to queue alphabetically

		this.level.put(source, 0);
		this.parent.put(source, null);
		int i = 0;
		ArrayList<String> frontier = new ArrayList<>();
		frontier.add(source);
		
		while(!frontier.isEmpty()){
			ArrayList<String> next = new ArrayList<>();
			for (String currentvertex : frontier) {
				if(!output.contains(currentvertex))
					output.add(currentvertex);
				for (Object consecutivevertex : adj.edges.get(currentvertex)) {
					if(!this.level.containsKey((String)consecutivevertex)){
						this.level.put((String)consecutivevertex, i);
						this.parent.put((String)consecutivevertex, currentvertex);
						next.add((String)consecutivevertex);
					}
				}
			}
			frontier = next;
			i+=1;
		}					
	}
}
