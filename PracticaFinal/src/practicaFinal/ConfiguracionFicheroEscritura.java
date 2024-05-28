package practicaFinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ConfiguracionFicheroEscritura {
    private ObjectOutputStream stream;
    
    public ConfiguracionFicheroEscritura(String camino) throws IOException {
        FileOutputStream inputStream = new FileOutputStream(camino);
        stream = new ObjectOutputStream(inputStream);
    }
    
    public void escribir(Configuracion config) throws IOException {
        stream.writeObject(config);
    }
    
    public void cerrarFichero() throws IOException {
        stream.close();
    }    
}
