package graph;
import java.util.AbstractMap;
import java.util.Collection;

/*
 * Essentially a tuple of four elements. The first element is the adjacent vertex
 * to the vertex being searched. The second element is the parent entry of the
 * adjacent vertex. The third element is the label entry of the adjacent vertex.
 * The fourth element is a boolean indicating whether the adjacent vertex is the
 */
public abstract class SearchResult<V, L> {
    public final V vertex;
    public final V adjacentVertex;
    public final AbstractMap.SimpleEntry<V, V> parentEntry;
    public final AbstractMap.SimpleEntry<V, L> labelEntry;
    public final Boolean found;
    
    public SearchResult(V vertex, V adjacentVertex, AbstractMap.SimpleEntry<V, V> parentEntry, AbstractMap.SimpleEntry<V, L> labelEntry, Boolean found) {
        this.vertex = vertex;
        this.adjacentVertex = adjacentVertex;
        this.parentEntry = parentEntry;
        this.labelEntry = labelEntry;
        this.found = found;
    }

    @Override
    public String toString() {
        return String.format("SearchResult(%s, %s, %s, %s, %s)", vertex, adjacentVertex, parentEntry, labelEntry, found);
    }
}
