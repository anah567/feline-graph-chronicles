package mision3.modelo;

public final class FloydWarshallResult {

    private final ChurunResult result;
    private final long[][] matrix;
    private final boolean[][] infinitePairs;

    public FloydWarshallResult(
            ChurunResult result,
            long[][] matrix,
            boolean[][] infinitePairs) {

        this.result = result;
        this.matrix = copyMatrix(matrix);
        this.infinitePairs =
                copyBooleanMatrix(infinitePairs);
    }

    public ChurunResult getResult() {
        return result;
    }

    public long[][] getMatrix() {
        return copyMatrix(matrix);
    }

    public boolean[][] getInfinitePairs() {
        return copyBooleanMatrix(infinitePairs);
    }

    public boolean isInfinite(
            int from,
            int to) {

        return infinitePairs[from][to];
    }

    private static long[][] copyMatrix(
            long[][] original) {

        long[][] copy =
                new long[original.length][];

        for (int i = 0;
             i < original.length;
             i++) {

            copy[i] =
                    original[i].clone();
        }

        return copy;
    }

    private static boolean[][] copyBooleanMatrix(
            boolean[][] original) {

        boolean[][] copy =
                new boolean[original.length][];

        for (int i = 0;
             i < original.length;
             i++) {

            copy[i] =
                    original[i].clone();
        }

        return copy;
    }
}