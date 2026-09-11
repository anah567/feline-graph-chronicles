package mision3.modelo;



public final class ChurunResult {

    public enum Status {
        BLOCKED,
        INFINITE,
        FINITE
    }

    private final Status status;
    private final long maxChurun;

    private ChurunResult(
            Status status,
            long maxChurun) {

        this.status = status;
        this.maxChurun = maxChurun;
    }

    public static ChurunResult blocked() {
        return new ChurunResult(
                Status.BLOCKED,
                0L
        );
    }

    public static ChurunResult infinite() {
        return new ChurunResult(
                Status.INFINITE,
                0L
        );
    }

    public static ChurunResult finite(long maxChurun) {
        return new ChurunResult(
                Status.FINITE,
                maxChurun
        );
    }

    public Status getStatus() {
        return status;
    }

    public long getMaxChurun() {
        return maxChurun;
    }

    public boolean isBlocked() {
        return status == Status.BLOCKED;
    }

    public boolean isInfinite() {
        return status == Status.INFINITE;
    }

    public boolean isFinite() {
        return status == Status.FINITE;
    }
}