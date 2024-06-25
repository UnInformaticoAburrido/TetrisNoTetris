package practicaFinal;

import java.io.Serializable;

public class Partida implements Serializable {
    private String nombre; // 20 caracteres = 40 bytes.
    private int tiempo; // 4 bytes
    private int puntuacion; // 4 bytes

    private static final int DIMENSION = 48;

    public Partida() {
        nombre = "";
        tiempo = 0;
        puntuacion = 0;
    }

    public Partida(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.puntuacion = 0;
    }

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

    public static int getDimension() {
        return DIMENSION;
    }

    public String toString() {
        return "> Partida jugada por " + this.nombre + " con un tiempo de [" + this.tiempo + "] segundos ha consegido ["
                + this.puntuacion + "] puntos";
    }
}
