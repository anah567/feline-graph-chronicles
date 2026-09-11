package app;

public class EntradaInvalidaException extends RuntimeException {

    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }

    public EntradaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}