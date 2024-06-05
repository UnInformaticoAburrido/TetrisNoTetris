package practicaFinal;

import java.awt.Color;
import java.util.Random;

public class Casilla {
    private boolean ocupada;
    private Color color;

    // Random que se utiliza para generar los colores de fondo de las piezas.
    private static Random random = new Random();

    public Casilla() {
        ocupada = false;

        // Genera un color aleatorio:

        float[] compColor = new float[3];

        compColor[0] = random.nextFloat(0, 1);
        compColor[1] = random.nextFloat(0.3f, 0.6f);
        compColor[2] = random.nextFloat(0.7f, 1);

        // Reordena de forma aleatoria los números generados.
        for (int i = 0; i < 3; i++) {
            int pos = random.nextInt(3);

            if (i == pos) {
                continue;
            }

            float aux = compColor[i];
            compColor[i] = compColor[pos];
            compColor[pos] = aux;
        }

        color = new Color(compColor[0], compColor[1], compColor[2]);
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Color getColor() {
        return color;
    }
}
