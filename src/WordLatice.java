import java.util.*;


import java.io.*;
public class WordLatice {

	Graph graph = new Graph();

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Random g = new Random();
		Scanner scan = new Scanner(System.in);
		String fname;
		System.out.println("Podaj nazwe pliku:");
		fname = scan.next();
		WordLatice main = new WordLatice();
		main.loadLattice(fname);
		//String sentence = main.bestSentence();
		//System.out.println(sentence);
		//String sentence = main.randomSentence(3);
		//System.out.println(sentence);
		String [] result = main.bestNSentences(5);
		for(int i=0; i<result.length; i++)
		{
			System.out.println(result[i]);
		}
	}
	
	void loadLattice( String fname) { // Tworzy graf z pliku i normalizuje prawdopodobie�stwa
		String line;
		graph.addVertex("<s>");
		graph.addVertex("</s>");
		try {
			Scanner scan = new Scanner(new FileReader(fname));
			while(scan.hasNext()) {
				line  = scan.nextLine();
				String[] arr = line.split(" ");
				if(graph.findElement(arr[0]) == null) {
					graph.addEdge("<s>", arr[0], 1.0);
				}
				graph.addVertex(arr[0]);
				graph.addVertex(arr[1]);
				graph.addEdge(arr[0], arr[1], Double.parseDouble(arr[2]));
				if(graph.getSumWeight(arr[0]) > 1.0)
					graph.normalize(arr[0]);
				
			}
			graph.addEnd();
			graph.displayGraph();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	 String bestSentence() // znajduje najbardziej prawdopodobne zdanie
	 { 
		 GraphNode next = graph.GraphNodeStart();
		 GraphNode current;
		 StringBuilder sentence= new StringBuilder();
		 sentence.append("<s>");
		 sentence.append(" ");
		 sentence.append(next.getValue());
		 sentence.append(" ");
		 while(!next.getValue().equals("</s>"))
		 {
			 current = next;
			 next = graph.heaviestWeight(current.getValue());
			 sentence.append(next.getValue());
			 sentence.append(" ");
			 //System.out.println(sentence.toString());
		 }
		 return sentence.toString();
	 }
	 
	 String [] bestNSentences( int n ) // znajduje najbardziej n najbardziej prawdopodobnych zda�
	 {
		 ArrayList<String>bestSentence = new ArrayList<String>();
		 int i=0;
		 while(i<n) {
			 GraphNode next = graph.GraphNodeStart();
			 GraphNode current;
			 StringBuilder sentence= new StringBuilder();
			 sentence.append("<s>");
			 sentence.append(" ");
			 sentence.append(next.getValue());
			 sentence.append(" ");
			 while (next != null && !next.getValue().equals("</s>") ) {
				 current = next;
				 next = graph.getNheaviestWeight(current.getValue());
				 if(next == null)
					 break;
				 sentence.append(next.getValue());
				 sentence.append(" ");
				 //System.out.println(sentence.toString());
			 }
			 i++;
			 bestSentence.add(sentence.toString());
		 }
		 String[] array = new String[bestSentence.size()];
		 array = bestSentence.toArray(array);
		 return array;
	 }
	
	 String randomSentence(int n) // tworzy losowo zdanie zaczynaj�ce si� od <s> o d�ugo�ci n s��w
	 {
		 GraphNode next = graph.GraphNodeStart();
		 GraphNode current;
		 int i=1;
		 StringBuilder sentence= new StringBuilder();
		 sentence.append("<s>");
		 sentence.append(" ");
		 sentence.append(next.getValue());
		 sentence.append(" ");
		 while(!next.getValue().equals("</s>") && i < n)
		 {
			 current = next;
			 next = graph.randEdge(current.getValue());
			 sentence.append(next.getValue());
			 sentence.append(" ");
			 //System.out.println(sentence.toString());
			 i++;
		 }
		 return sentence.toString();
	 }

}
