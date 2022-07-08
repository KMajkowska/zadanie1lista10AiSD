import java.util.*;

public class Graph {
	
		private ArrayList<String> vertices;
		private ArrayList<Edge> edges;
		private ArrayList<GraphNode> adjacencyList;
		
		public Graph()
		{
			vertices = new ArrayList<String>();
			edges = new ArrayList< Edge >();
			adjacencyList = new ArrayList<GraphNode>();
			
		}
		
		public void addVertex(String value) {
			if(findElement(value)== null) {
				GraphNode added = new GraphNode(value);
				adjacencyList.add(added);
				vertices.add(value);
			}
			
		}

		public void addEdge(String a, String b, double weight) {
			GraphNode A = findElement(a);
			if(A==null) return;
			
			GraphNode addA = new GraphNode(b,weight);
			while(A.next!=null)
			{
				A=A.next;
			}
			A.next=addA;
			
			edges.add(new Edge(a,b,weight));
			
		}


		public void displayGraph() {
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			
			while(itr.hasNext())
			{
				StringBuilder sb = new StringBuilder();
				current = itr.next();
				
				while(current!=null)
				{
					sb.append(current.toString());
					sb.append("->");
					current=current.next;
				}
				sb.setLength(sb.length()-2);
				System.out.println(sb.toString());
			}
			
		}
		
		public GraphNode findElement(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			
			while(itr.hasNext())
			{
				current=itr.next();
				if(current.value.equals(value)) 
					return current;
			}
			return null;
		}
		
		public double getSumWeight(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			double result = 0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					
					while(current!=null)
					{
						if(current.getEdgeWeight() != null) {
								result += current.getEdgeWeight();
						}
						current = current.getNext();
					}
					return result;
				}
				
			}
			return 0; 
		}
		
		public int getNumberOfAdjency(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			int result = 0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					current = current.getNext();
					while(current!=null)
					{
						result++;
						current = current.getNext();
					}
					return result;
				}
				
			}
			return 0;
		}
		
		public void addEnd()
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getNext() == null && !current.getValue().equals("</s>")) {
					addEdge(current.getValue(), "</s>", 0.0);
					current.setEdgeWeight(0.0);
				}
			}
		}
		
		public void normalize(String value)
		{
			int number = getNumberOfAdjency(value);
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			double weight = 1.0/number;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					
					while(current!=null)
					{
						if(current.getEdgeWeight() != null)
							current.setEdgeWeight(weight);
						current = current.getNext();
					}
				}
				
			}
		}
		
		public boolean checkWeight(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			double weight=0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					current = current.getNext();
					weight = current.getEdgeWeight();
					while(current!=null)
					{
						if(current.getEdgeWeight() != weight)
							return false;
						current = current.getNext();
					}
				}
			}
			return true;
		}
		
		public GraphNode heaviestWeight(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current, result = null;
			double max=0.0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					if(!checkWeight(current.getValue())) {
						current = current.getNext();
						while(current!=null)
						{
							if(current.getEdgeWeight() != null && current.getEdgeWeight() >= max)
							{
								max = current.getEdgeWeight();
								result = current;
							}
							current = current.getNext();
						}
						return result;
					}
					else
					{
						
						Random g =new Random();
						int number = getNumberOfAdjency(current.getValue());
						int nextWord = g.nextInt(number) , i=0;
						current = current.getNext();
						while(current!=null)
						{
							if(i == nextWord)
								return current;
							current = current.getNext();
							i++;
						}
					}
				}
				
			}
			return null;
		}
		
		public GraphNode GraphNodeStart()
		{
			GraphNode current;
			Random g =new Random();
			int number = getNumberOfAdjency("<s>");
			Iterator<GraphNode> itr = adjacencyList.iterator();
			int nextWord = g.nextInt(number) , i=0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals("<s>")) {
					current = current.getNext();
					while(current!=null)
					{
						if(i == nextWord)
							return current;
						current = current.getNext();
						i++;
					}
				}
				
			}
			return null;
			
		}
		
		public GraphNode randEdge(String value)
		{
			GraphNode current;
			Random g =new Random();
			Iterator<GraphNode> itr = adjacencyList.iterator();
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					double tmp = maxWeight(current.getValue());
					current = current.getNext();
					double randomWeight = g.nextDouble();
					if(randomWeight > tmp)
						randomWeight = tmp;
					while(current!=null)
					{
						if(current.getEdgeWeight() >= randomWeight)
							return current;
						current = current.getNext();
					}
				}
				
			}
			return null;
		}
		
		private double maxWeight(String value)
		{
			Iterator<GraphNode> itr = adjacencyList.iterator();
			GraphNode current;
			double max=0.0;
			while(itr.hasNext())
			{
				current = itr.next();
				if(current.getValue().equals(value)) {
					current = current.getNext();
					while(current!=null)
					{
						if(current.getEdgeWeight() > max)
							max = current.getEdgeWeight();
						current = current.getNext();
						
					}
				}
			}
			return max;
		}

	public GraphNode getNheaviestWeight(String value)
	{
		Iterator<GraphNode> itr = adjacencyList.iterator();
		GraphNode current, result = null;
		double max=0.0;
		while(itr.hasNext())
		{
			current = itr.next();
			if(current.getValue().equals(value)) {
				if(!checkWeight(current.getValue())) {
					current = current.getNext();
					while(current!=null)
					{
						if(current.getEdgeWeight() != null && current.getEdgeWeight() >= max && current.isChecked()==false)
						{
							max = current.getEdgeWeight();
							result = current;
						}
						current = current.getNext();
					}
					if(result != null)
						result.setChecked(true);
					return result;
				}
				else
				{

					Random g =new Random();
					int number = getNumberOfAdjency(current.getValue());
					int nextWord = g.nextInt(number) , i=0;
					current = current.getNext();
					while(current!=null)
					{
						//System.out.println(current.getValue() + " " + current.isChecked());
						if(i == nextWord && !current.isChecked()){
							current.setChecked(true);
							return current;
						}
						current = current.getNext();
						i++;
					}

				}
			}

		}
		return null;
	}
		
}
