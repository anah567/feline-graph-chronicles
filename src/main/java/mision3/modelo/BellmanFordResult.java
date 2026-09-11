package mision3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BellmanFordResult {

    private final ChurunResult result;
    private final List<Integer> path;
    private final List<Integer> positiveCycle;

    public BellmanFordResult(
            ChurunResult result,
            List<Integer> path,
            List<Integer> positiveCycle) {

        this.result = result;

        this.path = Collections.unmodifiableList(
                new ArrayList<>(path)
        );

        this.positiveCycle = Collections.unmodifiableList(
                new ArrayList<>(positiveCycle)
        );
    }

    public ChurunResult getResult() {
        return result;
    }

    public List<Integer> getPath() {
        return path;
    }

    public List<Integer> getPositiveCycle() {
        return positiveCycle;
    }

    public boolean hasPath() {
        return !path.isEmpty();
    }

    public boolean hasPositiveCycle() {
        return !positiveCycle.isEmpty();
    }
}