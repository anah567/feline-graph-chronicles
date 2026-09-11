package app;

public final class Arista {

    private final int origen;
    private final int destino;
    private final long peso;

    public Arista(int origen, int destino, long peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public long getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Arista{"
                + origen
                + " -> "
                + destino
                + ", peso="
                + peso
                + "}";
    }
}
