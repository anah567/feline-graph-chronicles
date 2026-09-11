package mision2.algoritmo;

import app.EntradaInvalidaException;
import mision2.modelo.GraphCase;
import mision2.modelo.UndirectedGraph;

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
            int c = nextInt("numero de conexiones");
            int source = nextInt("nodo origen");
            int destination = nextInt("nodo destino");

            if (n < 1 || n > 10000) {
                throw new EntradaInvalidaException(
                        "N debe estar entre 1 y 10000"
                );
            }

            if (c < 0 || c > 100000) {
                throw new EntradaInvalidaException(
                        "C debe estar entre 0 y 100000"
                );
            }

            if (source < 0 || source >= n
                    || destination < 0 || destination >= n) {

                throw new EntradaInvalidaException(
                        "Origen o destino fuera de rango"
                );
            }

            UndirectedGraph graph = new UndirectedGraph(n);

            for (int i = 0; i < c; i++) {
                int a = nextInt("nodo A");
                int b = nextInt("nodo B");
                long weight = nextLong("peso");

                if (a < 0 || a >= n || b < 0 || b >= n) {
                    throw new EntradaInvalidaException(
                            "Nodo fuera de rango"
                    );
                }

                if (weight < 0 || weight > 1000000) {
                    throw new EntradaInvalidaException(
                            "El peso debe estar entre 0 y 1000000"
                    );
                }

                graph.addEdge(a, b, weight);
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
                    "Se esperaba un numero entero para " + field
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
                    "Se esperaba un numero entero para " + field
            );
        }
    }
}