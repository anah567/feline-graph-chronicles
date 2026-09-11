package mision1.algoritmo;

import mision1.modelo.GridCase;
import mision1.modelo.SearchResult;
import mision1.modelo.Point;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class PathFinder {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public SearchResult solve(GridCase testCase) {
        Point start = testCase.getStart();
        Point destination = testCase.getDestination();

        if (testCase.hasBomb(start.getRow(), start.getCol())
                || testCase.hasBomb(destination.getRow(), destination.getCol())) {
            return SearchResult.unreachable();
        }

        List<Point> bfsPath = bfs(testCase);

        if (bfsPath == null) {
            return SearchResult.unreachable();
        }

        List<Point> dfsPath = dfs(testCase);

        int bfsMoves = bfsPath.size() - 1;
        int dfsMoves = dfsPath.size() - 1;

        return SearchResult.found(
                bfsMoves,
                dfsMoves,
                bfsPath,
                dfsPath
        );
    }

    /*
     * BFS (Breadth-First Search)
     *
     * Complejidad temporal: O(R * C)
     * Complejidad espacial: O(R * C)
     *
     * Se utiliza BFS porque la cuadrícula funciona como un grafo no ponderado,
     * donde cada movimiento tiene el mismo costo. Por esta razón, BFS permite
     * encontrar el camino más corto entre el punto inicial y el destino.
     *
     * La cola procesa las posiciones por niveles, visitando primero las
     * posiciones que están a menor cantidad de movimientos del inicio.
     */

    private List<Point> bfs(GridCase testCase) {
        int rows = testCase.getRows();
        int cols = testCase.getCols();

        Point start = testCase.getStart();
        Point destination = testCase.getDestination();

        boolean[][] visited = new boolean[rows][cols];
        Point[][] parent = new Point[rows][cols];

        Deque<Point> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.getRow()][start.getCol()] = true;

        if (start.equals(destination)) {
            return List.of(start);
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newRow = current.getRow() + DR[dir];
                int newCol = current.getCol() + DC[dir];

                if (!testCase.inBounds(newRow, newCol)) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                if (testCase.hasBomb(newRow, newCol)) {
                    continue;
                }

                visited[newRow][newCol] = true;
                parent[newRow][newCol] = current;

                Point next = new Point(newRow, newCol);

                if (next.equals(destination)) {
                    return reconstructPath(
                            parent,
                            start,
                            destination
                    );
                }

                queue.add(next);
            }
        }

        return null;
    }


    /*
     * DFS (Depth-First Search)
     *
     * Complejidad temporal: O(R * C)
     * Complejidad espacial: O(R * C)
     *
     * Se utiliza DFS para explorar la cuadrícula avanzando en profundidad
     * antes de regresar y probar otros caminos.
     *
     * La implementación es iterativa y utiliza una pila explícita para evitar
     * problemas de desbordamiento de la pila de llamadas en cuadrículas grandes.
     *
     * El orden de expansión requerido es:
     * arriba, abajo, izquierda y derecha.
     */

    private List<Point> dfs(GridCase testCase) {
        int rows = testCase.getRows();
        int cols = testCase.getCols();

        Point start = testCase.getStart();
        Point destination = testCase.getDestination();

        boolean[][] visited = new boolean[rows][cols];
        Point[][] parent = new Point[rows][cols];

        Deque<StackEntry> stack = new ArrayDeque<>();
        stack.push(new StackEntry(start, null));

        while (!stack.isEmpty()) {
            StackEntry entry = stack.pop();
            Point current = entry.node;

            if (visited[current.getRow()][current.getCol()]) {
                continue;
            }

            visited[current.getRow()][current.getCol()] = true;
            parent[current.getRow()][current.getCol()] = entry.parent;

            if (current.equals(destination)) {
                return reconstructPath(
                        parent,
                        start,
                        destination
                );
            }

            for (int dir = 3; dir >= 0; dir--) {
                int newRow = current.getRow() + DR[dir];
                int newCol = current.getCol() + DC[dir];

                if (!testCase.inBounds(newRow, newCol)) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                if (testCase.hasBomb(newRow, newCol)) {
                    continue;
                }

                stack.push(
                        new StackEntry(
                                new Point(newRow, newCol),
                                current
                        )
                );
            }
        }

        return null;
    }

    private List<Point> reconstructPath(
            Point[][] parent,
            Point start,
            Point destination) {

        List<Point> path = new ArrayList<>();
        Point current = destination;

        while (current != null && !current.equals(start)) {
            path.add(current);
            current = parent[current.getRow()][current.getCol()];
        }

        path.add(start);
        Collections.reverse(path);

        return path;
    }

    private static final class StackEntry {

        private final Point node;
        private final Point parent;

        private StackEntry(Point node, Point parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}