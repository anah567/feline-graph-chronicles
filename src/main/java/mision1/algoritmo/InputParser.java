package mision1.algoritmo;

import app.EntradaInvalidaException;
import mision1.modelo.GridCase;
import mision1.modelo.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public final class InputParser {

    private final StringTokenizer tokens;

    public InputParser(String rawInput) {
        this.tokens = new StringTokenizer(
                rawInput == null ? "" : rawInput
        );
    }

    public List<GridCase> parseAll() {
        List<GridCase> cases = new ArrayList<>();

        while (true) {
            int rows = nextInt("numero de filas");
            int cols = nextInt("numero de columnas");

            if (rows == 0 && cols == 0) {
                break;
            }

            if (rows < 1 || rows > 1000
                    || cols < 1 || cols > 1000) {

                throw new EntradaInvalidaException(
                        "R y C deben estar entre 1 y 1000"
                );
            }

            Set<Point> bombs = new HashSet<>();

            int bombRows =
                    nextInt("numero de filas con bombas");

            if (bombRows < 0 || bombRows > rows) {
                throw new EntradaInvalidaException(
                        "Numero de filas con bombas invalido"
                );
            }

            for (int i = 0; i < bombRows; i++) {
                int row =
                        nextInt("fila con bombas");

                int bombCount =
                        nextInt("numero de bombas");

                if (row < 0 || row >= rows) {
                    throw new EntradaInvalidaException(
                            "Fila de bomba fuera del grid"
                    );
                }

                if (bombCount < 0 || bombCount > cols) {
                    throw new EntradaInvalidaException(
                            "Numero de bombas invalido"
                    );
                }

                for (int j = 0; j < bombCount; j++) {
                    int col =
                            nextInt("columna de bomba");

                    if (col < 0 || col >= cols) {
                        throw new EntradaInvalidaException(
                                "Columna de bomba fuera del grid"
                        );
                    }

                    bombs.add(
                            new Point(row, col)
                    );
                }
            }

            int startRow =
                    nextInt("fila inicial");

            int startCol =
                    nextInt("columna inicial");

            int destRow =
                    nextInt("fila destino");

            int destCol =
                    nextInt("columna destino");

            if (!inBounds(
                    startRow,
                    startCol,
                    rows,
                    cols)) {

                throw new EntradaInvalidaException(
                        "Posicion inicial fuera del grid"
                );
            }

            if (!inBounds(
                    destRow,
                    destCol,
                    rows,
                    cols)) {

                throw new EntradaInvalidaException(
                        "Posicion destino fuera del grid"
                );
            }

            Point start =
                    new Point(startRow, startCol);

            Point destination =
                    new Point(destRow, destCol);

            cases.add(
                    new GridCase(
                            rows,
                            cols,
                            bombs,
                            start,
                            destination
                    )
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

    private boolean inBounds(
            int row,
            int col,
            int rows,
            int cols) {

        return row >= 0
                && row < rows
                && col >= 0
                && col < cols;
    }
}