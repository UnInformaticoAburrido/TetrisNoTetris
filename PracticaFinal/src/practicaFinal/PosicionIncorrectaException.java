package practicaFinal;

// Excepción personalizada que aparece cuando el usuario intenta acceder
// a una posición inexistente en el fichero.
public class PosicionIncorrectaException extends Exception {
    public PosicionIncorrectaException() {
        super();
    }

    public PosicionIncorrectaException(String mensaje) {
        super(mensaje);
    }
}
