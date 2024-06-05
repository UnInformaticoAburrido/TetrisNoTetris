package practicaFinal;

import java.io.Serializable;


public class Configuracion implements Serializable {
    private int puntuacionCasillasEliminadas;
    private int puntuacionRotarForma;
    private int puntuacionNuevaForma;
    private String imagenCasillasFormas;
    private int tiempoPartida;

    public Configuracion() {
        this.puntuacionCasillasEliminadas = 1;
        this.puntuacionRotarForma = 1;
        this.puntuacionNuevaForma = 5;
        this.imagenCasillasFormas = "img/piece.png";
        this.tiempoPartida = 30;
        
    }

    public int getPuntuacionCasillasEliminadas() {
        return puntuacionCasillasEliminadas;
    }

    public void setPuntuacionCasillasEliminadas(int puntuacionCasillasEliminadas) {
        this.puntuacionCasillasEliminadas = puntuacionCasillasEliminadas;
    }

    public int getPuntuacionRotarForma() {
        return puntuacionRotarForma;
    }

    public void setPuntuacionRotarForma(int puntuacionRotarForma) {
        this.puntuacionRotarForma = puntuacionRotarForma;
    }

    public int getPuntuacionNuevaForma() {
        return puntuacionNuevaForma;
    }

    public void setPuntuacionNuevaForma(int puntuacionNuevaForma) {
        this.puntuacionNuevaForma = puntuacionNuevaForma;
    }

    public String getImagenCasillasFormas() {
        return imagenCasillasFormas;
    }

    public void setImagenCasillasFormas(String imagenCasillasFormas) {
        this.imagenCasillasFormas = imagenCasillasFormas;
    }

    public int getTiempoPartida() {
        return tiempoPartida;
    }

    public void setTiempoPartida(int tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }
    
    
}
