public class Edge {

    private Pair pair;
    private double weight;

    public Edge(String a, String b, double weight) {
        pair = new Orderedpair(a, b);
        this.weight = weight;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double Comp(Edge other) {
        return weight - other.getWeight();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(pair.toString());
        sb.append("(");
        sb.append(weight);
        sb.append(")");
        return sb.toString();
    }

    public String toString2() {
        return pair.toString();
    }

}
