package graphs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = new FileInputStream("docs/configs.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		Graph graph = new Graph();
		
		String line = bufferedReader.readLine();
		System.out.println(line);
		
		while(line != null) {
			graph.addVertice(line);
			
			line = bufferedReader.readLine();
		}
		
		bufferedReader.close();
		
		graph.addEdgeByVertices();
		graph.printAdjacencyList();
		
		System.out.println("-----------");
		System.out.println("Valores Negativos: " + graph.getNegativeValues());
		
		System.out.println("-----------");
		System.out.println("Valores Duplicados: " + graph.getDuplicatedValues());
		
		
		graph.writeListToFile();
	}
}