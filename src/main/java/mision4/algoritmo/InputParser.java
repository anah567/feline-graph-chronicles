package mision4.algoritmo;

import app.Arista;
import app.EntradaInvalidaException;
import mision4.modelo.GraphCase;

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

        int testCases =
                nextInt("numero de casos");

        if (testCases < 0) {
            throw new EntradaInvalidaException(
                    "El numero de casos no puede ser negativo"
            );
        }

        for (int test = 0; test < testCases; test++) {

            int n =
                    nextInt("numero de intersecciones");

            int c =
                    nextInt("numero de cables");

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

            List<Arista> edges =
                    new ArrayList<>();

            for (int i = 0; i < c; i++) {

                int a =
                        nextInt("interseccion A");

                int b =
                        nextInt("interseccion B");

                long weight =
                        nextLong("costo del cable");

                if (a < 1 || a > n
                        || b < 1 || b > n) {

                    throw new EntradaInvalidaException(
                            "Interseccion fuera de rango"
                    );
                }

                if (weight < 0 || weight > 1000000) {
                    throw new EntradaInvalidaException(
                            "El costo debe estar entre 0 y 1000000"
                    );
                }

                edges.add(
                        new Arista(
                                a,
                                b,
                                weight
                        )
                );
            }

            cases.add(
                    new GraphCase(
                            n,
                            edges
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

        String token =
                tokens.nextToken();

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

        String token =
                tokens.nextToken();

        try {
            return Long.parseLong(token);

        } catch (NumberFormatException e) {

            throw new EntradaInvalidaException(
                    "Se esperaba un numero entero para " + field,
                    e
            );
        }
    }
}