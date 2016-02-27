import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import ai.graph.MapGraph;
import ai.search.*;

public class SearchUSA {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		if(args.length < 3){
			System.out.println("Please enter arguments on Command Line.");
			System.out.println("Usage: java SearchUSA <source> <destination> <path-to-roads-file>");
			System.exit(0);
		}
		
		Input input = new Input();
		
		BufferedReader file = input.readFile(args[3]);
		
		MapGraph graph = input.readGraph(file);
	    	    
		graph.normalizeAdjacencyMatrix();
		
		
		String searchtype = args[0];
		String source = args[1];
		String destination = args[2];
		
		switch (searchtype) {
		case "greedy":
			GreedyBestFirst objGBF = new GreedyBestFirst(graph, source, destination);
			
			objGBF.runGBF();
			printSearchOutput(objGBF, source, destination);
			break;
			
		case "uniform":
			UniformCost objUCS = new UniformCost(graph, source, destination);
			objUCS.runUCS();
			printSearchOutput(objUCS, source, destination);
			break;
			
		case "astar":
			AStar objAstar = new AStar(graph, source, destination);
			objAstar.runAStar();
			printSearchOutput(objAstar, source, destination);
			break;
		default:
			break;
		}
	}
	
	private static void printSearchOutput(Search obj, String source, String destination){
		ArrayList<String> path = obj.getPathFrom(source,destination);

		System.out.println("The sequence of nodes expanded in "+obj.getClass().getSimpleName()+" is -");
		int j=0;
		for (String node : obj.output) {
			System.out.print(node+" ");
			j++;
		}
		System.out.println();
		System.out.println("Number of nodes expanded: "+ j);
		
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		int i = 0;
		for (String node : path) {
			System.out.print(node+" ");
			i++;
		}
		System.out.println();
		System.out.println("Number of nodes in path: "+ i);
		
		
		System.out.println("Path Cost: "+obj.pathCosts.get(destination));
	}
	
	private static void printAStarOutput(AStar objAstar, String source, String destination) {
		// TODO Auto-generated method stub
		ArrayList<String> ucspath = objAstar.getPathFrom(source,destination);
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		int i = 1;
		for (String node : ucspath) {
			System.out.print(i+". ");
			System.out.println(node);
			i++;			
		}
		System.out.println("Path Cost: "+objAstar.pathCosts.get(destination));
		System.out.println("The sequence of nodes expanded in A Star Search Output is -");
		int j = 1;
		for (String node : objAstar.output) {
			System.out.print(j+". ");
			System.out.println(node);
			j++;
		}
	}

	private static void printUCSOutput(UniformCost objUCS, String source, String destination) {
		// TODO Auto-generated method stub
		ArrayList<String> ucspath = objUCS.getPathFrom(source,destination);
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		int i = 1;
		for (String node : ucspath) {
			System.out.print(i+". ");
			System.out.println(node);
			i++;
		}
		System.out.println("Path Cost: "+objUCS.pathCosts.get(destination));
		System.out.println("The sequence of nodes expanded in Uniform Cost Search Output is -");
		int j=1;
		for (String node : objUCS.output) {
			System.out.print(j+". ");
			System.out.println(node);
			j++;
		}
	}

	private static void printGBFOutput(GreedyBestFirst obj, String source, String destination){
		ArrayList<String> gbfpath = obj.getPathFrom(source,destination);
		System.out.println("The path from Input "+source+" to Output "+destination+" is -");
		int i = 1;
		for (String node : gbfpath) {
			System.out.print(i+". ");
			System.out.println(node);
			i++;
		}
		System.out.println("The sequence of nodes expanded in Greedy Best First Search Output is -");
		int j=1;
		for (String node : obj.output) {
			System.out.print(j+". ");
			System.out.println(node);
			j++;
		}
	}

}
