package grafos.function;


import grafos.edges.AbstractEdge;

@FunctionalInterface
public interface TraverseFunction<V, E extends AbstractEdge<V>> {
    public void traverse(
        TraverseFunction<V,E> self,
        TraverseState<V,E>.Upcoming state, 
        TraverseFunction.Start<V,E> fstart,
        TraverseFunction.End<V,E> fend
    );

    @FunctionalInterface
    public interface Start<V, E extends AbstractEdge<V>> {
        public void start(V startVertex, TraverseState<V,E> state);
    }

    @FunctionalInterface
    public interface End<V, E extends AbstractEdge<V>> {
        public void end(V startVertex, TraverseState<V,E> state);
    }
}

