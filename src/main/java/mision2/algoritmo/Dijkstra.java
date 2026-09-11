package mision2.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision2.modelo.DijkstraResult;
import mision2.modelo.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public final class Dijkstra {

    private static final long INFINITY = Long.MAX_VALUE;

    private Dijkstra() {
    }

    /*
     * Algoritmo de Dijkstra
     *
     * Complejidad temporal: O((V + E) log V)
     * Complejidad espacial: O(V + E)
     *
     * Se utiliza Dijkstra porque necesitamos encontrar el camino de menor costo
     * entre dos nodos y todas las conexiones tienen pesos no negativos.
     *
     * La PriorityQueue permite seleccionar eficientemente el siguiente nodo
     * con la menor distancia conocida, evitando una implementación O(V^2).
     *
     * Se usa long para las distancias acumuladas porque el costo total de un
     * camino puede superar el rango de un int.
     */

    public static DijkstraResult findShortestPath(
            UndirectedGraph graph,
            int source,
            int destination) {

        if (graph == null) {
            throw new EntradaInvalidaException(
                    "El grafo no puede ser null"
            );
        }

        int n = graph.getN();

        validateNode(source, n);
        validateNode(destination, n);

        long[] distances = new long[n];
        int[] previous = new int[n];

        Arrays.fill(distances, INFINITY);
        Arrays.fill(previous, -1);

        distances[source] = 0L;

        PriorityQueue<long[]> queue = new PriorityQueue<>(
                (a, b) -> Long.compare(a[0], b[0])
        );

        queue.add(new long[]{0L, source});

        while (!queue.isEmpty()) {
            long[] current = queue.poll();

            long currentDistance = current[0];
            int currentNode = (int) current[1];

            if (currentDistance > distances[currentNode]) {
                continue;
            }

            if (currentNode == destination) {
                break;
            }

            for (Arista edge : graph.getNeighbors(currentNode)) {
                int neighbor = edge.getDestino();

                long newDistance =
                        currentDistance + edge.getPeso();

                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    previous[neighbor] = currentNode;

                    queue.add(
                            new long[]{
                                    newDistance,
                                    neighbor
                            }
                    );
                }
            }
        }

        if (distances[destination] == INFINITY) {
            return DijkstraResult.unreachable();
        }

        List<Integer> path = reconstructPath(
                previous,
                source,
                destination
        );

        return DijkstraResult.found(
                distances[destination],
                path
        );
    }

    private static List<Integer> reconstructPath(
            int[] previous,
            int source,
            int destination) {

        List<Integer> path = new ArrayList<>();

        int current = destination;

        while (current != -1) {
            path.add(current);

            if (current == source) {
                break;
            }

            current = previous[current];
        }

        Collections.reverse(path);

        return path;
    }

    private static void validateNode(int node, int n) {
        if (node < 0 || node >= n) {
            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}