package practicaFinal;

public class PosicionIncorrectaException extends Exception {
    public PosicionIncorrectaException() {
        super();
    }

    public PosicionIncorrectaException(String mensaje) {
        super(mensaje);
    }
}
