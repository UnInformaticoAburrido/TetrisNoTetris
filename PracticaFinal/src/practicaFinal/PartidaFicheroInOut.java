package practicaFinal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PartidaFicheroInOut {
    private RandomAccessFile fichero;

    public PartidaFicheroInOut(String camino) throws FileNotFoundException {
        fichero = new RandomAccessFile(camino, "rw");
    }

    public Partida lectura() throws IOException {
        Partida partida = new Partida();

        partida.setNombre(lecturaString(20));
        partida.setTiempo(fichero.readInt());
        partida.setPuntuacion(fichero.readInt());

        return partida;
    }

    public Partida lectura(int posicion) throws IOException, PosicionIncorrectaException {
        long posicionMaxima = fichero.length() / Partida.getDimension();

        if (posicion <= 0 || posicion > posicionMaxima) {
            throw new PosicionIncorrectaException(
                    "La posición no está dentro del fichero.");
        }

        fichero.seek(posicion);
        return lectura();
    }

    public void escritura(Partida partida) throws IOException {
        escrituraString(partida.getNombre(), 20);
        fichero.writeInt(partida.getTiempo());
        fichero.writeInt(partida.getPuntuacion());
    }

    public void escritura(Partida partida, int posicion) throws IOException, PosicionIncorrectaException {
        long posicionMaxima = fichero.length() / Partida.getDimension();

        if (posicion <= 0 || posicion > posicionMaxima) {
            throw new PosicionIncorrectaException(
                    "La posición no está dentro del fichero.");
        }

        fichero.seek(posicion);
        escritura(partida);
    }

    // Permite mover el apuntador del fichero al final para poder
    // hacer la acción de "append".
    public void irAlFinal() throws IOException {
        fichero.seek(fichero.length());
    }

    public void cerrarFichero() throws IOException {
        fichero.close();
    }

    private String lecturaString(int longitud) throws IOException {
        char[] caracteres = new char[longitud];

        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] = fichero.readChar();
        }

        // trim() devuelve un String donde los espacios del principio
        // y del final se han eliminado.
        return new String(caracteres).trim();
    }

    private void escrituraString(String string, int longitud) throws IOException {
        for (int i = 0; i < string.length() && i < longitud; i++) {
            fichero.writeChar(string.charAt(i));
        }

        if (string.length() < longitud) {
            for (int i = string.length(); i < longitud; i++) {
                fichero.writeChar(' ');
            }
        }
    }
}
