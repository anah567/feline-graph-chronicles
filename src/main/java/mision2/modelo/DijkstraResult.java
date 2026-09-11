package mision2.modelo;

import java.util.Collections;
import java.util.List;

public final class DijkstraResult {

    private final boolean reachable;
    private final long cost;
    private final List<Integer> path;

    private DijkstraResult(
            boolean reachable,
            long cost,
            List<Integer> path) {

        this.reachable = reachable;
        this.cost = cost;
        this.path = Collections.unmodifiableList(path);
    }

    public static DijkstraResult unreachable() {
        return new DijkstraResult(
                false,
                -1L,
                List.of()
        );
    }

    public static DijkstraResult found(
            long cost,
            List<Integer> path) {

        return new DijkstraResult(
                true,
                cost,
                path
        );
    }

    public boolean isReachable() {
        return reachable;
    }

    public long getCost() {
        return cost;
    }

    public List<Integer> getPath() {
        return path;
    }
}