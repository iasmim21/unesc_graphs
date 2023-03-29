package graphs;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjacentVertices;    
    private List<String> negativeValues = new ArrayList<String>();
    private List<String> duplicatedValues = new ArrayList<String>();

    public Graph() {
    	adjacentVertices = new LinkedHashMap<>();
    }

    public void addVertice(String line) {
    	String formattedLine = line.substring(line.indexOf(":") + 1).trim();
		
		if(!formattedLine.equals("EDIFICIO PLANALTO – CRICIUMA SC") && !formattedLine.equals("TRAILER33")){
			if(Integer.parseInt(formattedLine) < 0) {
				negativeValues.add(line);
				
				return;
			}
			
			if(getAdjacentVertices().containsKey(Integer.parseInt(formattedLine))) {
				duplicatedValues.add(line);
				
				return;
			}
			
			adjacentVertices.putIfAbsent(Integer.parseInt(formattedLine), new ArrayList<>());
		}
    }

    public void addEdge(Integer currentVertice, Integer nextVertice) {
        if(!this.adjacentVertices.containsKey(nextVertice)){
            return;
        }
        
        adjacentVertices.get(currentVertice).add(nextVertice);
    }
    
    public void addEdgeByVertices() {
    	getAdjacentVertices().forEach((chave, valor) -> {
            if(Objects.nonNull(chave)) {
                switch (chave % 10) {
                    case 1:
                    	addEdge(chave, chave + 1);
                        break;
                    case 2:
                    	addEdge(chave, chave + 1);
                    	addEdge(chave, chave + 99);
                    	addEdge(chave, chave + 100);
                        break;
                    case 3:
                    	addEdge(chave, chave + 1);
                    	addEdge(chave, chave + 99);
                    	addEdge(chave, chave + 100);
                    	addEdge(chave, chave + 101);
                        break;
                    case 4:
                    	addEdge(chave, chave + 1);
                    	addEdge(chave, chave + 99);
                    	addEdge(chave, chave + 100);
                        break;
                }
            }
        });
    }
    
    public Map<Integer, List<Integer>> getAdjacentVertices() {
        return adjacentVertices;
    }
    
    public List<String> getNegativeValues() {
        return negativeValues;
    }
    
    public List<String> getDuplicatedValues() {
        return duplicatedValues;
    }
    
    public void printAdjacencyList() {
    	getAdjacentVertices().forEach((chave, value) -> {
			System.out.print("[" + chave + "]");
			
			value.forEach((val) -> {
				System.out.print("-> [" + val + "]");
			});
			
			System.out.println("-> [/]");
        });
    }
    
    public void writeListToFile() throws IOException {
    	FileWriter fileWriter = new FileWriter("docs/list.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
	    printWriter.println("================= Lista de Adjacencia ====================");
	    printWriter.println("");
	    
	    getAdjacentVertices().forEach((chave, value) -> {
	    	StringBuilder lineList = new StringBuilder();
	    	
	    	lineList.append("[" + chave + "]");
			
			value.forEach((val) -> {
				lineList.append(" -> [" + val + "]");
			});
			
			lineList.append(" -> [/]");
			
			printWriter.println(lineList.toString());
        });
	    
	    printWriter.println("");
	    printWriter.println("Valores Negativos: " + negativeValues);
		
	    printWriter.println("");
	    printWriter.println("Valores Duplicados: " + duplicatedValues);
		
	    
	    fileWriter.close();
	    
	    System.out.printf("Arquivo gerado com sucesso!");
    }
}
