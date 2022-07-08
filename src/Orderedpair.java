
public class Orderedpair implements Pair{
	
	private String first;
	private String second;
	
	public Orderedpair(String a, String b)
	{
		first=a;
		second=b;
	}
	
	public String getFirst() {
		return first;
	}
	public String getSecond() {
		return second;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Orderedpair) return equals((Orderedpair)o);
		else return false;
	}
	
	public boolean equals(Orderedpair other)
	{	
		if(first.equals(other.getFirst()) && second.equals(other.getSecond())) return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(first.toString());
		sb.append(",");
		sb.append(second.toString());
		sb.append(">");
		return sb.toString();
	}
}

