/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaFinal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author dima
 */
public class HistorialFicheroLectura {
    private RandomAccessFile file;

    public HistorialFicheroLectura(String path) throws FileNotFoundException {
        this.file = new RandomAccessFile(path, "w");
    }

    /*public Partida leerSiguietePartida() throws IOException, ClassNotFoundException {
        
    }*/

    public void cerrarFichero() throws IOException {
        
    }
}
