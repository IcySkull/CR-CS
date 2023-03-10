import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A graph is a set of vertices and a set of edges. Each edge connects two
 * vertices. Note that AbstractGraph ignores if directed or undirected.
 * 
 */
public abstract class AbstractGraph<V, W extends Number> {
    private Set<V> vertices;
    private Set<Edge> edges;

    public AbstractGraph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public AbstractGraph(Set<V> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void setVertices(Set<V> vertices) {
        this.vertices = vertices;
    }

    public Set<V> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void addVertices(V... vs) {
        for (V v : vs)
            vertices.add(v);
    }

    public void addVertex(V v) {
        addVertices(v);
    }

    public void addEdges(Edge... es) {
        for (Edge e : es)
            edges.add(e);
    }

    public void addEdge(V v, V w, W weight) {
        if (!vertices.contains(v))
            throw new IllegalArgumentException("Vertex " + v + " is not in the graph");
        if (!vertices.contains(w))
            throw new IllegalArgumentException("Vertex " + w + " is not in the graph");
        Edge e = new Edge(v, w, weight) {};
        edges.add(e);
    }

    public void addEdge(V v, V w) {
        addEdge(v, w, (W) Double.valueOf(1));
    }

    /**
     * Returns the number of vertices in the graph.
     */
    public int order() {
        return vertices.size();
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int numEdges() {
        return edges.size();
    }

    /**
     * Returns the set of edges incident to vertex v.
     */
    public Set<Edge> incidentEdges(V v) {
        return edges.stream().filter(e -> e.isIncident(v)).collect(Collectors.toSet());
    }

    /**
     * Returns the set of edges adjacent to edge e.
     */
    public Set<Edge> adjacentEdges(Edge e) {
        return edges.stream().filter(e2 -> e2.isIncident(e.v) && e2.isIncident(e.w)).collect(Collectors.toSet());
    }

    /**
     * Returns the set of vertices adjacent to vertex v.
     */
    public Set<V> adjacentVertices(V v) {
        return edges.stream().filter(e -> e.isIncident(v)).map(e -> e.adj(v)).collect(Collectors.toSet());
    }

    /**
     * Returns the degree of verte cx v.
     */
    public int degree(V v) {
        return incidentEdges(v).size();
    }

    public <T extends Collection<V>, R> List<V> search (V start, V end, T traverse, Function<Edge, W> heuristic, Consumer<AbstractGraph<V, W>> forEachVisited) {
        if (!vertices.contains(start))
            throw new IllegalArgumentException("Start vertex is not in the graph");
        if (!vertices.contains(end))
            throw new IllegalArgumentException("End vertex is not in the graph");
        Map<V, V> parent = new HashMap<>();
        Map<V, W> cost = new HashMap<>();
        traverse.add(start);
        cost.put(start, (W) Double.valueOf(0));

        while (!traverse.isEmpty()) {
            V v = traverse.iterator().next();
            traverse.remove(v);

            forEachVisited.accept(this);

            if (v.equals(end))
                return backtrace(parent, end);

            for (Edge e : incidentEdges(v)) {
                V w = e.adj(v);
                W newCost = (W) Double.valueOf(cost.get(v).doubleValue() + e.getWeight().doubleValue());
                if (!cost.containsKey(w) || newCost.doubleValue() < cost.get(w).doubleValue()) {
                    parent.put(w, v);
                    cost.put(w, (W) Double.valueOf(newCost.doubleValue() + heuristic.apply(e).doubleValue()));
                    traverse.add(w);
                }
            }
        }

        return null;
    }

    /*
     * Backtrace the path from start to end. Helper function for search.
     */
    public List<V> backtrace(Map<V, V> parent, V end) {
        List<V> path = new ArrayList<>();
        V v = end;
        while (v != null) {
            path.add(v);
            v = parent.get(v);
        }
        Collections.reverse(path);
        return path;
    }

    public List<V> bfs(V start, V end) {
        return search(start, end, (Queue)(new LinkedList<>()), e -> (W) Double.valueOf(0), (graph) -> {});
    }

    public List<V> dfs(V start, V end) {
        return search(start, end, new Stack<>(), e -> (W) Double.valueOf(0), (graph) -> {});
    }


    public abstract class Edge {
        private V v;
        private V w;
        private W weight;
        
        public Edge(V v, V w, W weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public Edge(V v, V w) {
            this(v, w, (W) Double.valueOf(1));
        }

        /*
         * Return the adjacent vertex to u, this is the most fundamental operation
         * on an edge.
         */
        public V adj(V u) {
            if (u.equals(v))
                return this.w;
            else if (u.equals(w))
                return this.v;
            throw new IllegalArgumentException("Vertex is not adjacent to this edge");
        }

        /*
         * true if u is one of the vertices of this edge, false otherwise.
         */
        public boolean isIncident(V u) {
            return u.equals(v) || u.equals(w);
        }

        public int hashCode() {
            return Objects.hash(v, w);
        }
        
        public W getWeight() {
            return weight;
        }
    }

    public static void main(String[] args) {

        AbstractGraph<Integer, Number> graph = new AbstractGraph<Integer, Number>() {};
        graph.addVertices(1, 2, 3, 5, 3, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);
        graph.addEdge(1, 3);


        System.out.println(graph.dfs(1, 4));
    }
}
