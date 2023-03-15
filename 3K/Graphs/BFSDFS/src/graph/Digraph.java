package graph;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Digraph<V> extends AbstractGraph<V, Digraph<V>.Diedge> {
    public Digraph() {
        super();
    }

    public Set<Diedge> incomingEdges(V u) {
        return getEdges().stream()
                .filter(e -> e.isHead(u))
                .collect(Collectors.toSet());
    }

    public Set<Diedge> outgoingEdges(V u) {
        return getEdges().stream()
                .filter(e -> e.isTail(u))
                .collect(Collectors.toSet());
    }

    public Set<V> incomingVertices(V u) {
        return getEdges().stream()
                .filter(e -> e.isHead(u))
                .map(e -> e.tail())
                .collect(Collectors.toSet());
    }

    public Set<V> outgoingVertices(V u) {
        return getEdges().stream()
                .filter(e -> e.isTail(u))
                .map(e -> e.head())
                .collect(Collectors.toSet());
    }

    public int inDegree(V u) {
        return incomingEdges(u).size();
    }

    public int outDegree(V u) {
        return outgoingEdges(u).size();
    }

    class Diedge extends AbstractEdge<V> {

        public Diedge(V from, V to) {
            super(from, to);
        }

        public V tail() {
            return v();
        }

        public V head() {
            return w();
        }

        public boolean isHead(V v) {
            return v.equals(head());
        }

        public boolean isTail(V v) {
            return v.equals(tail());
        }

        /**
         * Returns the end vertex of this edge if {@code u} is the start vertex.
         * 
         * @param u the start vertex
         * @return the end vertex of this edge if {@code u} is the start vertex, null if
         *         {@code u} is the end vertex
         * @throws IllegalArgumentException if {@code u} is not contained in this edge
         * 
         */
        @Override
        public V adj(V u) {
            if (isTail(u))
                return head();
            else if (!isHead(u))
                throw new IllegalArgumentException("Vertex " + u + " is not contained in this edge");
            throw new IllegalArgumentException("Vertex " + u + " is the end vertex of this edge");
        }

        @Override
        public int hashCode() {
            return Objects.hash(tail(), head());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof Digraph<?>.Diedge))
                return false;
            Digraph<?>.Diedge other = (Digraph<?>.Diedge) obj;
            return Objects.equals(tail(), other.tail()) && Objects.equals(head(), other.head());
        }
    }
}
