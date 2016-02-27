import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import ai.graph.MapGraph;

public class Input {
	public BufferedReader readFile(String strpath) throws IOException {
		Path path = FileSystems.getDefault().getPath(strpath);
	    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	    return reader;
	}
	
	public MapGraph readGraph(BufferedReader file) throws IOException{
		MapGraph graph = new MapGraph();
		
		String  currentLine = null;
		while ((currentLine = file.readLine()) != null) {
            currentLine = currentLine.replaceAll("\\s","");
            
            String linetype = currentLine.substring(0, 4);
            
            switch (linetype) {
			case "city":
				String city = readCity(currentLine);
				graph.vertices.add(city);				
				graph.vertexCoordinates.put(city, readCoordinates(currentLine));
				graph.edges.put(city, new LinkedList());
			break;
				
			case "road":
				String cityvertex = readCity(currentLine);
				String nextEdge = readNextEdge(currentLine);
				String cost = readPathCost(currentLine);
				ArrayList<String> toFromCost = new ArrayList<String>(){{
					add(cityvertex);
					add(nextEdge); 
					add(cost);	
				}};
				LinkedList<String> vertexlist = graph.edges.get(cityvertex);
				vertexlist.add(nextEdge);
				graph.edgeCosts.add(toFromCost);
			break;

			default:
				break;
			}
            
         } 
		return graph;
	}
	private String readNextEdge(String line) {
		String nextcity = line.substring(line.indexOf(',')+1, line.indexOf(',',line.indexOf(',')+1));
		return nextcity;
	}

	private String readCity(String line){
		String city = line.substring(5, line.indexOf(','));
		return city;
	}
	
	private float[] readCoordinates(String line){
		String[] coordinatesStrings = line.split(",");
		float[] coordinates = {Float.parseFloat(coordinatesStrings[1]), Float.parseFloat(coordinatesStrings[2].substring(0, coordinatesStrings[2].indexOf(")")))};
		return coordinates;		
	}
	
	private String readPathCost(String line){
		String[] words = line.split(",");
		return words[words.length-1].replaceAll("\\D", "");
	}
}
