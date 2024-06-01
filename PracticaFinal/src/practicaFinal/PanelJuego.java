package practicaFinal;

import java.awt.*;

import javax.swing.JPanel;

public class PanelJuego extends JPanel {
    public PanelJuego() {
        super();
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.BLACK);

        // Calcula dimensiones del tablero:
        int width = 30 * 20, height = 30 * 20;

        // Dibuja las líneas verticales.
        for (int i = 0; i < 21; i++) {
            int x = i * 30;

            graphics2D.drawLine(x, 0, x, height);
        }

        // Dibuja las líneas horizontales.
        for (int i = 0; i < 21; i++) {
            int y = i * 30;

            graphics2D.drawLine(0, y, width, y);
        }
    }
}
