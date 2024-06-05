package practicaFinal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HistorialFicheroLectura {
    private ObjectInputStream stream;
    
    public HistorialFicheroLectura(String camino) throws IOException {
        FileInputStream inputStream = new FileInputStream(camino);
        stream = new ObjectInputStream(inputStream);
    }
    
    public Partida leer() throws IOException, ClassNotFoundException {
        return (Partida) stream.readObject();
    }
    
    public void cerrarFichero() throws IOException {
        stream.close();
    }
}
