package grafos.function;


import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.edges.AbstractEdge;

@FunctionalInterface
public interface TraverseFunction<V, E extends AbstractEdge<V>> {
    public void traverse(
        TraverseFunction<V,E> self,
        V from,
        E adjEdge,
        TraverseFunction.Start<V,E> fstart,
        TraverseFunction.End<V,E> fend,
        Consumer<V> cycle
    );

    @FunctionalInterface
    public interface Start<V, E extends AbstractEdge<V>> {
        public void start(V startVertex);
    }

    @FunctionalInterface
    public interface End<V, E extends AbstractEdge<V>> {
        public void end(V startVertex);
    }
}

