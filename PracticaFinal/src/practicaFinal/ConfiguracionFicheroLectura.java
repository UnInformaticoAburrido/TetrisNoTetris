package practicaFinal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ConfiguracionFicheroLectura {
    private ObjectInputStream stream;
    
    public ConfiguracionFicheroLectura(String camino) throws IOException {
        FileInputStream inputStream = new FileInputStream(camino);
        stream = new ObjectInputStream(inputStream);
    }
    
    public Configuracion leer() throws IOException, ClassNotFoundException {
        return (Configuracion) stream.readObject();
    }
    
    public void cerrarFichero() throws IOException {
        stream.close();
    }
}
