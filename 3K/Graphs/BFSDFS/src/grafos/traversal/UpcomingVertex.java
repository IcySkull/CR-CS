package grafos.traversal;

import grafos.edges.AbstractEdge;

public final class UpcomingVertex<V, E extends AbstractEdge<V>> {
    public final V srcV;
    public final E srcE;
    public final V v;

    public UpcomingVertex(V srcV, E srcE, V v) {
        this.srcV = srcV;
        this.srcE = srcE;
        this.v = v;
    }
}
