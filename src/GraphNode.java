
public class GraphNode {
	String value;
	Double edgeWeight;
	GraphNode next;
	boolean checked=false;
	
	public GraphNode(String val)
	{
		value=val;
		edgeWeight=null;
		next=null;
		checked = false;
	}
	
	public GraphNode(String val,double edgeWeight)
	{
		value=val;
		this.edgeWeight=edgeWeight;
		next=null;
		checked = false;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(value);
		sb.append("/");
		sb.append(edgeWeight);
		sb.append("]");
		return sb.toString();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getEdgeWeight() {
		return edgeWeight;
	}

	public void setEdgeWeight(Double edgeWeight) {
		this.edgeWeight = edgeWeight;
	}

	public GraphNode getNext() {
		return next;
	}

	public void setNext(GraphNode next) {
		this.next = next;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
