package mision3.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision3.modelo.ChurunResult;
import mision3.modelo.DirectedGraph;
import mision3.modelo.FloydWarshallResult;

public final class FloydWarshall {

    /*
     * Representa que no existe un camino entre dos nodos.
     *
     * En la matriz visual este valor se mostrará como "-".
     */
    public static final long NEGATIVE_INFINITY =
            Long.MIN_VALUE / 4;

    private FloydWarshall() {
    }

    /*
     * Algoritmo de Floyd-Warshall
     *
     * Complejidad temporal: O(V^3)
     * Complejidad espacial: O(V^2)
     *
     * Se utiliza Floyd-Warshall porque permite calcular los mejores valores
     * entre todos los pares de nodos mediante una matriz.
     *
     * En esta implementación se maximiza la cantidad de churun acumulada,
     * en lugar de minimizar una distancia.
     *
     * Después de calcular la matriz se identifican los ciclos positivos.
     * Si un nodo i puede llegar a un ciclo positivo y desde ese ciclo se
     * puede llegar a un nodo j, entonces el máximo churun entre i y j es
     * ilimitado.
     *
     * Estos pares se almacenan por separado para poder mostrarlos como
     * "inf" en la matriz final.
     */
    public static FloydWarshallResult solve(
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

        long[][] distances =
                new long[n][n];

        for (int i = 0;
             i < n;
             i++) {

            for (int j = 0;
                 j < n;
                 j++) {

                if (i == j) {

                    distances[i][j] =
                            0L;

                } else {

                    distances[i][j] =
                            NEGATIVE_INFINITY;
                }
            }
        }

        for (Arista edge
                : graph.getEdges()) {

            int u =
                    edge.getOrigen();

            int v =
                    edge.getDestino();

            long weight =
                    edge.getPeso();

            if (weight
                    > distances[u][v]) {

                distances[u][v] =
                        weight;
            }
        }

        for (int k = 0;
             k < n;
             k++) {

            for (int i = 0;
                 i < n;
                 i++) {

                if (distances[i][k]
                        == NEGATIVE_INFINITY) {

                    continue;
                }

                for (int j = 0;
                     j < n;
                     j++) {

                    if (distances[k][j]
                            == NEGATIVE_INFINITY) {

                        continue;
                    }

                    long newDistance =
                            distances[i][k]
                                    + distances[k][j];

                    if (newDistance
                            > distances[i][j]) {

                        distances[i][j] =
                                newDistance;
                    }
                }
            }
        }

        /*
         * infinitePairs[i][j] será true cuando:
         *
         * i puede llegar a un ciclo positivo
         * y ese ciclo puede llegar hasta j.
         */
        boolean[][] infinitePairs =
                new boolean[n][n];

        for (int k = 0;
             k < n;
             k++) {

            if (distances[k][k] <= 0) {
                continue;
            }

            for (int i = 0;
                 i < n;
                 i++) {

                if (distances[i][k]
                        == NEGATIVE_INFINITY) {

                    continue;
                }

                for (int j = 0;
                     j < n;
                     j++) {

                    if (distances[k][j]
                            == NEGATIVE_INFINITY) {

                        continue;
                    }

                    infinitePairs[i][j] =
                            true;
                }
            }
        }

        ChurunResult result;

        if (distances[source][destination]
                == NEGATIVE_INFINITY) {

            result =
                    ChurunResult.blocked();

        } else if (infinitePairs[source][destination]) {

            result =
                    ChurunResult.infinite();

        } else {

            result =
                    ChurunResult.finite(
                            distances[source][destination]
                    );
        }

        return new FloydWarshallResult(
                result,
                distances,
                infinitePairs
        );
    }

    /*
     * Este método se mantiene para que otras partes del proyecto puedan
     * obtener únicamente el resultado cuando no necesitan la matriz.
     */
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