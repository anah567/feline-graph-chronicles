package mision3.modelo;

public final class GraphCase {

    private final DirectedGraph graph;
    private final int source;
    private final int destination;

    public GraphCase(
            DirectedGraph graph,
            int source,
            int destination) {

        this.graph = graph;
        this.source = source;
        this.destination = destination;
    }

    public DirectedGraph getGraph() {
        return graph;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }
}