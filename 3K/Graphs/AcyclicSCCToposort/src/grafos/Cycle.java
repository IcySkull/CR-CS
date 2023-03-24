package grafos;

public class Cycle<V> {
    java.util.Set<V> marked;
    java.util.List<V> cycle;

    public Cycle(java.util.Collection<V> cycle) {
        this.cycle = new java.util.ArrayList<>(cycle);
        this.marked = new java.util.HashSet<>(this.cycle);
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
