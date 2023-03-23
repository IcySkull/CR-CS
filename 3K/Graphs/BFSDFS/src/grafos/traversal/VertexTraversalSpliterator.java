package grafos.traversal;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public abstract class VertexTraversalSpliterator<V, E extends AbstractEdge<V>> implements Spliterator<UpcomingVertex<V, E>> {
    protected final AbstractGraph<V, E> graph;
    protected final CollectionVisitor<V, E> frontier;
    protected final Set<V> visited;
    protected boolean checkVisited = true;

    public enum Scope {
        GRAPH,
        VERTEX;
    }

    public enum Frontier {
        BFS,
        DFS,
        PRIORITY_QUEUE;
    }

    public enum Order {
        PREORDER,
        POSTORDER;
    }

    public VertexTraversalSpliterator() {
        graph = null;
        frontier = null;
        visited = null;
    }

    public VertexTraversalSpliterator(
            AbstractGraph<V, E> graph,
            V root,
            Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
    ) {
        this.graph = graph;
        this.frontier = CollectionVisitor.of(frontierSupplier.get());
        this.visited = visited;
        this.checkVisited = checkVisited;

        this.frontier.add(new UpcomingVertex<>(null, null, root));
    }

    public static <V, E extends AbstractEdge<V>> VertexTraversalSpliterator<V, E> of(
            AbstractGraph<V, E> graph,
            Scope scope,
            V root,
            Frontier frontier,
            Order order,
            Set<V> visited,
            boolean checkVisited
    ) {
        Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier = getFrontierSupplier(frontier);
        switch (order) {
            case PREORDER:
                switch (scope) {
                    case GRAPH:
                        return new GraphPreorderSpliterator<>(graph, frontierSupplier, visited, checkVisited);
                    case VERTEX:
                        return new VertexPreorderSpliterator<>(graph, root, frontierSupplier, visited, checkVisited);
                    default:
                        throw new IllegalArgumentException("Invalid scope type");
                }
            case POSTORDER:
                switch (scope) {
                    case GRAPH:
                        return new GraphPostorderSpliterator<>(graph, frontierSupplier, visited, checkVisited);
                    case VERTEX:
                        return new VertexPostorderSpliterator<>(graph, root, frontierSupplier, visited, checkVisited);
                    default:
                        throw new IllegalArgumentException("Invalid scope type");
                }
            default:
                throw new IllegalArgumentException("Invalid order type");
        }
    }

    public static <V, E extends AbstractEdge<V>> Supplier<Collection<UpcomingVertex<V, E>>> getFrontierSupplier(Frontier frontier) {
        switch (frontier) {
            case BFS:
                return () -> (Queue<UpcomingVertex<V, E>>) new LinkedList<UpcomingVertex<V,E>>();
            case DFS:
                return () -> (Stack<UpcomingVertex<V, E>>) new Stack<UpcomingVertex<V,E>>();
            case PRIORITY_QUEUE:
                return () -> (PriorityQueue<UpcomingVertex<V, E>>) new PriorityQueue<UpcomingVertex<V,E>>();
            default:
                throw new IllegalArgumentException("Invalid frontier type");
        }
    }

    
}
