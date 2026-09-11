package mision1.modelo;

import java.util.Collections;
import java.util.Set;

public final class GridCase {

    private final int rows;
    private final int cols;
    private final Set<Point> bombs;
    private final Point start;
    private final Point destination;

    public GridCase(
            int rows,
            int cols,
            Set<Point> bombs,
            Point start,
            Point destination) {

        this.rows = rows;
        this.cols = cols;
        this.bombs = Collections.unmodifiableSet(bombs);
        this.start = start;
        this.destination = destination;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Set<Point> getBombs() {
        return bombs;
    }

    public Point getStart() {
        return start;
    }

    public Point getDestination() {
        return destination;
    }

    public boolean hasBomb(int row, int col) {
        return bombs.contains(new Point(row, col));
    }

    public boolean inBounds(int row, int col) {
        return row >= 0
                && row < rows
                && col >= 0
                && col < cols;
    }
}