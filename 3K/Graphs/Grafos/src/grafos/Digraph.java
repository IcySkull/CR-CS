package grafos;

import java.util.Collection;

import grafos.edges.Diedge;


public abstract class Digraph<V> extends AbstractGraph<V, Diedge<V>> {
    public Digraph() {
        super();
    }

    public abstract Collection<Diedge<V>> incidentEdges(V u);

    public int inDegree(V u) {
        return incidentEdges(u).size();
    }

    public int outDegree(V u) {
        return adjacentEdges(u).size();
    }

}
