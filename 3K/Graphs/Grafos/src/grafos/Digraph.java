package grafos;

import java.util.Collection;
import java.util.Set;

import grafos.edges.Diedge;


public abstract class Digraph<V> extends AbstractGraph<V, Diedge<V>> {
    public Digraph() {
        super();
    }

    /**
     * Returns the set of edges that are adjacent to the given vertex.
     * @param u The vertex given.
     */
    public abstract Collection<Diedge<V>> incidentEdges(V u);

    public int inDegree(V u) {
        return incidentEdges(u).size();
    }

    public int outDegree(V u) {
        return adjacentEdges(u).size();
    }

    /**
     *  Returns the transposed graph of this graph. The transposed graph is the graph
     *  with all edges reversed.
     */
    public abstract Digraph<V> transposed();

    /**
     * Returns the strongly connected components of the graph from the given vertex.
     * @param root The vertex from which the search will start.
     */
    public abstract Set<Collection<V>> stronglyConnectedComponents(V root);
}
