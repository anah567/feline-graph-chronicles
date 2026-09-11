package mision1.modelo;

import java.util.Collections;
import java.util.List;

public final class SearchResult {

    private final boolean reachable;
    private final int bfsMoves;
    private final int dfsMoves;
    private final List<Point> bfsPath;
    private final List<Point> dfsPath;

    private SearchResult(
            boolean reachable,
            int bfsMoves,
            int dfsMoves,
            List<Point> bfsPath,
            List<Point> dfsPath) {

        this.reachable = reachable;
        this.bfsMoves = bfsMoves;
        this.dfsMoves = dfsMoves;
        this.bfsPath = Collections.unmodifiableList(bfsPath);
        this.dfsPath = Collections.unmodifiableList(dfsPath);
    }

    public static SearchResult unreachable() {
        return new SearchResult(
                false,
                -1,
                -1,
                List.of(),
                List.of()
        );
    }

    public static SearchResult found(
            int bfsMoves,
            int dfsMoves,
            List<Point> bfsPath,
            List<Point> dfsPath) {

        return new SearchResult(
                true,
                bfsMoves,
                dfsMoves,
                bfsPath,
                dfsPath
        );
    }

    public boolean isReachable() {
        return reachable;
    }

    public int getBfsMoves() {
        return bfsMoves;
    }

    public int getDfsMoves() {
        return dfsMoves;
    }

    public List<Point> getBfsPath() {
        return bfsPath;
    }

    public List<Point> getDfsPath() {
        return dfsPath;
    }
}