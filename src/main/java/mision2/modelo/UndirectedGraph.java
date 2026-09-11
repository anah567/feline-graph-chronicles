package mision2.modelo;

import app.Arista;
import app.EntradaInvalidaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UndirectedGraph {

    private final int n;
    private final List<List<Arista>> adjacency;

    public UndirectedGraph(int n) {
        if (n < 1 || n > 10000) {
            throw new EntradaInvalidaException(
                    "N debe estar entre 1 y 10000"
            );
        }

        this.n = n;
        this.adjacency = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int a, int b, long weight) {
        validateNode(a);
        validateNode(b);

        if (weight < 0 || weight > 1000000) {
            throw new EntradaInvalidaException(
                    "El peso debe estar entre 0 y 1000000"
            );
        }

        adjacency.get(a).add(
                new Arista(a, b, weight)
        );

        adjacency.get(b).add(
                new Arista(b, a, weight)
        );
    }

    public List<Arista> getNeighbors(int node) {
        validateNode(node);

        return Collections.unmodifiableList(
                adjacency.get(node)
        );
    }

    public int getN() {
        return n;
    }

    private void validateNode(int node) {
        if (node < 0 || node >= n) {
            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}