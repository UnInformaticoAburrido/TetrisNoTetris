/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaFinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author dima
 */
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
