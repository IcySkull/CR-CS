package grafos;

import java.util.List;
import java.util.Set;

public class Cycle<V> {
    Set<V> marked;
    List<V> cycle;

    public Cycle(List<V> cycle) {
        this.cycle = new java.util.ArrayList<>(cycle);
        this.marked = new java.util.HashSet<>(this.cycle);
        if (marked.size() < 3)
            throw new IllegalArgumentException("Cycle must have at least 3 vertices");
    }

    @Override
    public String toString() {
        return cycle.toString();
    }

    @Override
    public int hashCode() {
        return marked.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        Cycle<?> other = (Cycle<?>) obj;
        return marked.equals(other.marked);
    }
}
