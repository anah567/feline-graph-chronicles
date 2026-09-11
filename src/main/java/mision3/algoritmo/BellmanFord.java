package mision3.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision3.modelo.BellmanFordResult;
import mision3.modelo.ChurunResult;
import mision3.modelo.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class BellmanFord {

    private static final long NEGATIVE_INFINITY =
            Long.MIN_VALUE / 4;

    private BellmanFord() {
    }

    /*
     * Algoritmo de Bellman-Ford
     *
     * Complejidad temporal: O(V * E)
     * Complejidad espacial: O(V)
     *
     * Se utiliza Bellman-Ford porque el grafo puede contener pesos positivos
     * y negativos.
     *
     * En esta implementación se maximiza la cantidad de churun acumulada.
     *
     * También se guardan los predecesores de los nodos para reconstruir:
     *
     * - La ruta máxima cuando el resultado es finito.
     * - Un ciclo positivo cuando el resultado es infinito.
     *
     * Se usa long para almacenar los valores acumulados.
     */
    public static BellmanFordResult solve(
            DirectedGraph graph,
            int source,
            int destination) {

        if (graph == null) {

            throw new EntradaInvalidaException(
                    "El grafo no puede ser null"
            );
        }

        int n =
                graph.getN();

        validateNode(
                source,
                n
        );

        validateNode(
                destination,
                n
        );

        long[] distances =
                new long[n];

        int[] predecessor =
                new int[n];

        Arrays.fill(
                distances,
                NEGATIVE_INFINITY
        );

        Arrays.fill(
                predecessor,
                -1
        );

        distances[source] =
                0L;

        for (int i = 1;
             i < n;
             i++) {

            boolean changed =
                    false;

            for (Arista edge
                    : graph.getEdges()) {

                int u =
                        edge.getOrigen();

                int v =
                        edge.getDestino();

                long weight =
                        edge.getPeso();

                if (distances[u]
                        == NEGATIVE_INFINITY) {

                    continue;
                }

                long newDistance =
                        distances[u]
                                + weight;

                if (newDistance
                        > distances[v]) {

                    distances[v] =
                            newDistance;

                    predecessor[v] =
                            u;

                    changed =
                            true;
                }
            }

            if (!changed) {
                break;
            }
        }

        if (distances[destination]
                == NEGATIVE_INFINITY) {

            return new BellmanFordResult(
                    ChurunResult.blocked(),
                    Collections.emptyList(),
                    Collections.emptyList()
            );
        }

        boolean[] affectedByCycle =
                new boolean[n];

        int[] cyclePredecessor =
                predecessor.clone();

        for (Arista edge
                : graph.getEdges()) {

            int u =
                    edge.getOrigen();

            int v =
                    edge.getDestino();

            long weight =
                    edge.getPeso();

            if (distances[u]
                    == NEGATIVE_INFINITY) {

                continue;
            }

            if (distances[u] + weight
                    > distances[v]) {

                affectedByCycle[v] =
                        true;

                cyclePredecessor[v] =
                        u;
            }
        }

        for (int i = 0;
             i < n;
             i++) {

            for (Arista edge
                    : graph.getEdges()) {

                int u =
                        edge.getOrigen();

                int v =
                        edge.getDestino();

                if (affectedByCycle[u]) {

                    affectedByCycle[v] =
                            true;
                }
            }
        }

        if (affectedByCycle[destination]) {

            List<Integer> positiveCycle =
                    findPositiveCycle(
                            graph,
                            distances,
                            predecessor
                    );

            return new BellmanFordResult(
                    ChurunResult.infinite(),
                    Collections.emptyList(),
                    positiveCycle
            );
        }

        List<Integer> path =
                reconstructPath(
                        predecessor,
                        source,
                        destination
                );

        return new BellmanFordResult(
                ChurunResult.finite(
                        distances[destination]
                ),
                path,
                Collections.emptyList()
        );
    }

    public static ChurunResult findMaximum(
            DirectedGraph graph,
            int source,
            int destination) {

        return solve(
                graph,
                source,
                destination
        ).getResult();
    }

    private static List<Integer> reconstructPath(
            int[] predecessor,
            int source,
            int destination) {

        List<Integer> path =
                new ArrayList<>();

        int current =
                destination;

        int steps =
                0;

        while (current != -1
                && steps <= predecessor.length) {

            path.add(
                    current
            );

            if (current == source) {
                break;
            }

            current =
                    predecessor[current];

            steps++;
        }

        if (path.isEmpty()
                || path.get(
                path.size() - 1
        ) != source) {

            return Collections.emptyList();
        }

        Collections.reverse(
                path
        );

        return path;
    }

    private static List<Integer> findPositiveCycle(
            DirectedGraph graph,
            long[] originalDistances,
            int[] originalPredecessor) {

        int n =
                graph.getN();

        long[] distances =
                originalDistances.clone();

        int[] predecessor =
                originalPredecessor.clone();

        int updatedVertex =
                -1;

        /*
         * Una relajación adicional permite encontrar
         * un nodo afectado por un ciclo positivo.
         */
        for (Arista edge
                : graph.getEdges()) {

            int u =
                    edge.getOrigen();

            int v =
                    edge.getDestino();

            long weight =
                    edge.getPeso();

            if (distances[u]
                    == NEGATIVE_INFINITY) {

                continue;
            }

            if (distances[u] + weight
                    > distances[v]) {

                distances[v] =
                        distances[u]
                                + weight;

                predecessor[v] =
                        u;

                updatedVertex =
                        v;
            }
        }

        if (updatedVertex == -1) {

            return Collections.emptyList();
        }

        /*
         * Retrocedemos V veces para garantizar que
         * terminemos dentro del ciclo.
         */
        int cycleVertex =
                updatedVertex;

        for (int i = 0;
             i < n;
             i++) {

            cycleVertex =
                    predecessor[cycleVertex];

            if (cycleVertex == -1) {

                return Collections.emptyList();
            }
        }

        List<Integer> cycle =
                new ArrayList<>();

        int current =
                cycleVertex;

        do {

            cycle.add(
                    current
            );

            current =
                    predecessor[current];

            if (current == -1) {

                return Collections.emptyList();
            }

        } while (current != cycleVertex
                && cycle.size() <= n + 1);

        cycle.add(
                cycleVertex
        );

        Collections.reverse(
                cycle
        );

        return cycle;
    }

    private static void validateNode(
            int node,
            int n) {

        if (node < 0
                || node >= n) {

            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}