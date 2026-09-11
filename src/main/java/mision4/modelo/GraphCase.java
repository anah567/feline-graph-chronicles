package mision4.modelo;

import app.Arista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GraphCase {

    private final int n;
    private final List<Arista> edges;

    public GraphCase(
            int n,
            List<Arista> edges) {

        this.n = n;

        this.edges =
                Collections.unmodifiableList(
                        new ArrayList<>(edges)
                );
    }

    public int getN() {
        return n;
    }

    public List<Arista> getEdges() {
        return edges;
    }
}