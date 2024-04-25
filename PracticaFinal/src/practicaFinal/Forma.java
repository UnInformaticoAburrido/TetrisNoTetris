package practicaFinal;

import java.util.Random;

/**
 * @author Joan Enric Soler Carvajal
 */
public class Forma {
    private boolean[][] matrizForma = new boolean[3][3];

    public Forma() {
        Random random = new Random();

        for(int i = 0; i < matrizForma.length; i++) {
            boolean[] fila = matrizForma[i];

            for(int j = 0; j < fila.length; i++) {
                fila[j] = random.nextBoolean();
            }
        }
    }
}
