package practicaFinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HistorialFicheroEscritura {
    private ObjectOutputStream stream;
    
    public HistorialFicheroEscritura(String camino) throws IOException {
        FileOutputStream inputStream = new FileOutputStream(camino);
        stream = new ObjectOutputStream(inputStream);
    }
    
    public void escribir(Partida partida) throws IOException {
        stream.writeObject(partida);
    }
    
    public void cerrarFichero() throws IOException {
        stream.close();
    }
}
