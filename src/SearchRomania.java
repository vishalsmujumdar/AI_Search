import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import ai.graph.MapGraph;
import ai.search.BFS;
import ai.search.DFS;

public class SearchRomania {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		if(args.length < 3){
			System.out.println("Please enter arguments on Command Line.");
			System.out.println("Usage: java SearchRomania <source> <destination> <path-to-roads-file>");
			System.exit(0);
		}
		
		Input input = new Input();
		
		BufferedReader file = input.readFile(args[3]);
		
		MapGraph graph = input.readGraph(file);
	    	    
		graph.normalizeAdjacencyMatrix();
		
		String searchtype = args[0];
		String source = args[1];
		String destination = args[2];
		
		// Breadth First Search
		switch (searchtype) {
		case "bfs":
			BFS bfsobj = new BFS();		
			bfsobj.runBFS(source, graph);		
			printBFSOutput(bfsobj, source, destination);
			break;
			
		case "dfs":
			DFS dfsobj = new DFS();
			dfsobj.runDFS(graph, source);
			printDFSOutput(dfsobj, source, destination);

		default:
			break;
		}
	}
	static void printBFSOutput(BFS obj, String source, String destination){
		ArrayList<String> bfspath = obj.getPathFrom(source,destination);
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		for (String node : bfspath) {
			System.out.println(node);
		}
		System.out.println("The path from Breadth First Search Output is -");
		for (String node : obj.output) {
			System.out.println(node);
		}
	}
	static void printDFSOutput(DFS obj, String source, String destination){
		ArrayList<String> dfspath = obj.getPathFrom(source,destination);
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		for (String node : dfspath) {
			System.out.println(node);
		}
		System.out.println("The path from Depth First Search Output is -");
		for (String node : obj.output) {
			System.out.println(node);
		}
	}
}
