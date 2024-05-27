package practicaFinal;

/*
 * @author Joan Enric Soler Carvajal
 */
public class GestorJuego {
    private PanelJuego panelJuego;
    private boolean[][] matrizJuego = new boolean[20][20];

    public GestorJuego() {
        this.panelJuego = new PanelJuego();
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
    }
}