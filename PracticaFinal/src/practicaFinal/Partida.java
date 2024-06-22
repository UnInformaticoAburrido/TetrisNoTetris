package practicaFinal;

import java.io.Serializable;

public class Partida implements Serializable {
    private String nombre;
    private int tiempo;
    private int puntuacion = 0;

    public Partida() {
        nombre = "";
        tiempo = 0;
    }

    public Partida(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
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
                + this.puntuacion + "] puntos";
    }
}
