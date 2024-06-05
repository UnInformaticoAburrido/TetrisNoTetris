package practicaFinal;

import java.io.Serializable;

public class Partida implements Serializable {
    private String nombre;
    private int tiempo;
    private int puntuacion;

    public Partida(String nombre, int tiempo, int puntuacion) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.puntuacion = puntuacion;
    }

    public void incrementaPuntuacion(int valor) {
        puntuacion += valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String toString() {
        return "> Partida jugada por " + this.nombre + " con un tiempo de [" + this.tiempo + "] segundos ha consegido ["
                + this.puntuacion + "] puntus\n";
    }
}
