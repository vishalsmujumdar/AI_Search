package ai.search;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import ai.graph.MapGraph;

public class Search {
	HashMap<String, Integer> level = new HashMap<String, Integer>();
	public HashMap<String, String> parent = new HashMap<String, String>();
	public ArrayList<String> output = new ArrayList<>();
	public HashMap<String, Double> pathCosts = new HashMap<String, Double>();

	Comparator<String> comparator;
	
	MapGraph adj;
	String source, destination;
	
	public Search(MapGraph adj, String source, String destination){
		this.adj = adj;
		this.source = source;
		this.destination = destination;
	}
	public Search(){
		
	}
	
	public ArrayList<String> getPathFrom(String source, String destination){
		ArrayList<String> path = new ArrayList<>();
		HashMap<String, String> parent = this.parent;
		String currentNode = destination;
		path.add(currentNode);
		String parentNode = parent.get(currentNode);
		path.add(parentNode);
		do{
			currentNode = parentNode;
			parentNode = parent.get(currentNode);
			path.add(parentNode);
		}while(!parentNode.equals(source));
		Collections.reverse(path); 
		return path;
	}
	
	Comparator<String> setComparatorFunction(String methodName){
		comparator = new VertexComparator(this, methodName);
		return comparator;		
	}
}
class VertexComparator implements Comparator<String>{
	
	java.lang.reflect.Method method;
	Class<?> subClass;
	Search subclassObj;
	public VertexComparator(Search search, String methodName){
		try {
			subClass = Class.forName(search.getClass().getName());
			subclassObj = search;
			method = search.getClass().getMethod(methodName,String.class);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int compare(String vertex0, String vertex1) {
		// TODO Auto-generated method stub
		try {
			double costOfVertex0 = (double) method.invoke(subclassObj, vertex0);
			double costOfVertex1 = (double) method.invoke(subclassObj, vertex1);
			
			if (costOfVertex0 < costOfVertex1)
	        {
	            return -1;
	        }
	        if (costOfVertex0 > costOfVertex1)
	        {
	            return 1;
	        }
	        return 0;
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}