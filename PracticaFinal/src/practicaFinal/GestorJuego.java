package practicaFinal;

import javax.swing.JPanel;

/*
 * @author Joan Enric Soler Carvajal
 */
public class GestorJuego {
    private JPanel panelJuego;
    private boolean[][] matrizJuego = new boolean[20][20];

    public GestorJuego() {
        this.panelJuego = new JPanel();
    }

    public JPanel getPanelJuego() {
        return panelJuego;
    }
}