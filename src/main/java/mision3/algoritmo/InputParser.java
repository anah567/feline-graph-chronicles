package mision3.algoritmo;

import app.EntradaInvalidaException;
import mision3.modelo.DirectedGraph;
import mision3.modelo.GraphCase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class InputParser {

    private final StringTokenizer tokens;

    public InputParser(String rawInput) {
        this.tokens = new StringTokenizer(
                rawInput == null ? "" : rawInput
        );
    }

    public List<GraphCase> parseAll() {
        List<GraphCase> cases = new ArrayList<>();

        int testCases = nextInt("numero de casos");

        if (testCases < 0) {
            throw new EntradaInvalidaException(
                    "El numero de casos no puede ser negativo"
            );
        }

        for (int test = 0; test < testCases; test++) {

            int n = nextInt("numero de nodos");
            int m = nextInt("numero de pasajes");
            int source = nextInt("nodo inicial");
            int destination = nextInt("nodo destino");

            if (n < 1 || n > 100) {
                throw new EntradaInvalidaException(
                        "N debe estar entre 1 y 100"
                );
            }

            if (m < 0 || m > 5000) {
                throw new EntradaInvalidaException(
                        "M debe estar entre 0 y 5000"
                );
            }

            validateNode(source, n);
            validateNode(destination, n);

            DirectedGraph graph =
                    new DirectedGraph(n);

            for (int i = 0; i < m; i++) {

                int edgeSource =
                        nextInt("nodo origen del pasaje");

                int edgeDestination =
                        nextInt("nodo destino del pasaje");

                long weight =
                        nextLong("churun del pasaje");

                validateNode(edgeSource, n);
                validateNode(edgeDestination, n);

                if (weight < -1000 || weight > 1000) {
                    throw new EntradaInvalidaException(
                            "El churun debe estar entre -1000 y 1000"
                    );
                }

                graph.addEdge(
                        edgeSource,
                        edgeDestination,
                        weight
                );
            }

            cases.add(
                    new GraphCase(
                            graph,
                            source,
                            destination
                    )
            );
        }

        if (tokens.hasMoreTokens()) {
            throw new EntradaInvalidaException(
                    "Hay datos adicionales despues de los casos"
            );
        }

        return cases;
    }

    private int nextInt(String field) {
        if (!tokens.hasMoreTokens()) {
            throw new EntradaInvalidaException(
                    "Falta un valor para " + field
            );
        }

        String token = tokens.nextToken();

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(
                    "Se esperaba un numero entero para " + field,
                    e
            );
        }
    }

    private long nextLong(String field) {
        if (!tokens.hasMoreTokens()) {
            throw new EntradaInvalidaException(
                    "Falta un valor para " + field
            );
        }

        String token = tokens.nextToken();

        try {
            return Long.parseLong(token);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(
                    "Se esperaba un numero entero para " + field,
                    e
            );
        }
    }

    private void validateNode(
            int node,
            int n) {

        if (node < 0 || node >= n) {
            throw new EntradaInvalidaException(
                    "Nodo fuera de rango"
            );
        }
    }
}