package mision4.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision4.modelo.MSTResult;

import java.util.ArrayList;
import java.util.List;

public final class Kruskal {

    private Kruskal() {
    }

    /*
     * Algoritmo de Kruskal
     *
     * Complejidad temporal: O(E log E)
     * Complejidad espacial: O(V + E)
     *
     * Se utiliza Kruskal porque necesitamos encontrar un árbol de expansión
     * mínima (MST) que conecte todas las intersecciones utilizando el menor
     * costo total posible.
     *
     * El algoritmo ordena todas las aristas de menor a mayor costo y las
     * selecciona progresivamente, siempre que no formen un ciclo.
     *
     * Para detectar ciclos de manera eficiente se utiliza la estructura
     * Union-Find con compresión de caminos y unión por tamaño.
     *
     * La operación que domina el tiempo de ejecución es el ordenamiento
     * de las aristas, por lo que la complejidad temporal es O(E log E).
     *
     * Se utiliza long para almacenar el costo total del MST y evitar
     * problemas si la suma de los costos supera el rango de un int.
     */

    public static MSTResult findMST(
            int n,
            List<Arista> edges) {

        if (n < 1 || n > 10000) {
            throw new EntradaInvalidaException(
                    "N debe estar entre 1 y 10000"
            );
        }

        if (edges == null) {
            throw new EntradaInvalidaException(
                    "La lista de cables no puede ser null"
            );
        }

        List<Arista> sortedEdges =
                new ArrayList<>();

        for (Arista edge : edges) {

            if (edge == null) {
                throw new EntradaInvalidaException(
                        "La lista contiene una arista null"
                );
            }

            validateNode(
                    edge.getOrigen(),
                    n
            );

            validateNode(
                    edge.getDestino(),
                    n
            );

            if (edge.getPeso() < 0
                    || edge.getPeso() > 1000000) {

                throw new EntradaInvalidaException(
                        "El costo debe estar entre 0 y 1000000"
                );
            }

            sortedEdges.add(edge);
        }

        sortedEdges.sort(
                (a, b) -> Long.compare(
                        a.getPeso(),
                        b.getPeso()
                )
        );

        UnionFind unionFind =
                new UnionFind(n + 1);

        List<Arista> selectedEdges =
                new ArrayList<>();

        long totalCost = 0L;
        int edgesUsed = 0;

        for (Arista edge : sortedEdges) {

            if (edgesUsed == n - 1) {
                break;
            }

            int source =
                    edge.getOrigen();

            int destination =
                    edge.getDestino();

            if (source == destination) {
                continue;
            }

            if (unionFind.union(
                    source,
                    destination)) {

                selectedEdges.add(edge);

                totalCost +=
                        edge.getPeso();

                edgesUsed++;
            }
        }

        if (edgesUsed != n - 1) {

            return MSTResult.disconnected(
                    selectedEdges
            );
        }

        return MSTResult.connected(
                totalCost,
                selectedEdges
        );
    }

    private static void validateNode(
            int node,
            int n) {

        if (node < 1 || node > n) {
            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}