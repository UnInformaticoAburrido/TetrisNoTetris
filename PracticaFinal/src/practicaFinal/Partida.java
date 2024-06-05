/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaFinal;

/**
 *
 * @author dima
 */
public class Partida {
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
}
