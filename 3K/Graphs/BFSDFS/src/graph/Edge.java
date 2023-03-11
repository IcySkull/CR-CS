package graph;
import java.util.Objects;

/*
 * An edge is a pair of vertices. It is directed in the sense that vertices v
 * and w
 * are ordered, giving a diferent hashCode for (v, w) than for (w, v).
 * Nevertheless,
 * adj() method is undirected. Directed or undirected should be determined by
 * its
 * implementations, taking into account adj() method.
 */
abstract class Edge<V> {
    private V v;
    private V w;
    private Double weight;

    public Edge(V v, V w, Double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Edge(V v, V w) {
        this(v, w, Double.valueOf(1));
    }

    public V v() {
        return v;
    }

    public V w() {
        return w;
    }

    public Double weight() {
        return weight;
    }

    /*
     * Return the weight of this edge, adding the weight of the given edge. But
     * without
     * modifying the weight of the given edge.
     */
    public Double weight(Double weight) {
        return this.weight + weight;
    }

    public void addWeight(Double weight) {
        this.weight = this.weight.doubleValue() + weight.doubleValue();
    }

    /*
     * Return the adjacent vertex to u, this is the most fundamental operation
     * on an edge.
     */
    public V adj(V u) {
        if (u.equals(v))
            return this.w;
        else if (u.equals(w))
            return this.v;
        throw new IllegalArgumentException("Vertex is not adjacent to this edge");
    }

    /*
     * true if u is one of the vertices of this edge, false otherwise.
     */
    public boolean isIncident(V u) {
        return u.equals(v) || u.equals(w);
    }

    public int hashCode() {
        return Objects.hash(v, w);
    }
}