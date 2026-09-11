package mision3.modelo;

import app.Arista;
import app.EntradaInvalidaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DirectedGraph {

    private final int n;
    private final List<Arista> edges;

    public DirectedGraph(int n) {
        if (n < 1 || n > 100) {
            throw new EntradaInvalidaException(
                    "N debe estar entre 1 y 100"
            );
        }

        this.n = n;
        this.edges = new ArrayList<>();
    }

    public void addEdge(
            int source,
            int destination,
            long weight) {

        validateNode(source);
        validateNode(destination);

        if (weight < -1000 || weight > 1000) {
            throw new EntradaInvalidaException(
                    "El peso debe estar entre -1000 y 1000"
            );
        }

        edges.add(
                new Arista(
                        source,
                        destination,
                        weight
                )
        );
    }

    public int getN() {
        return n;
    }

    public List<Arista> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    private void validateNode(int node) {
        if (node < 0 || node >= n) {
            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}