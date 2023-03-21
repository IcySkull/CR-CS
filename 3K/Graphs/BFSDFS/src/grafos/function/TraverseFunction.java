package grafos.function;


import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

@FunctionalInterface
public interface TraverseFunction<V, E extends AbstractEdge<V>> {
    public void traverse(
        TraverseFunction<V,E> self,
        AbstractGraph<V, E>.UpcomingVertex v,
        TraverseFunction.Start<V,E> fstart,
        TraverseFunction.End<V,E> fend,
        Consumer<V> cycle
    );

    @FunctionalInterface
    public interface Start<V, E extends AbstractEdge<V>> {
        public void start(AbstractGraph<V, E>.UpcomingVertex v);
    }

    @FunctionalInterface
    public interface End<V, E extends AbstractEdge<V>> {
        public void end(AbstractGraph<V, E>.UpcomingVertex v);
    }
}

