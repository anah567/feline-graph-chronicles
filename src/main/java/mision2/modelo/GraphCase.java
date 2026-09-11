package mision2.modelo;

public final class GraphCase {

    private final UndirectedGraph graph;
    private final int source;
    private final int destination;

    public GraphCase(
            UndirectedGraph graph,
            int source,
            int destination) {

        this.graph = graph;
        this.source = source;
        this.destination = destination;
    }

    public UndirectedGraph getGraph() {
        return graph;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }
}