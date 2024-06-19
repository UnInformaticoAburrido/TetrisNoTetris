package practicaFinal;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

/*
 * @author Dimitri Company Cifre
 * @author Joan Enric Soler Carvajal
 */
public class TetrisUIB {
    public static final String CAMINO_CONFIG = "tetrisuib.conf";
    private static final String FICHERO_HISTORIAL = "partidasTetrisUIB.dat";
    private static Configuracion configuracion;

    private static GestorVentanas ventana;

    // Constantes de colores:
    public static final Color COLOR_PRINCIPAL = new Color(255, 138, 103); // #FF8A67
    public static final Color COLOR_SECUNDARIO = new Color(251, 204, 175); // #FBCCAF
    public static final Color COLOR_TERCIARIO = new Color(39, 105, 28); // #27691C
    public static final Color COLOR_FONDOS = new Color(254, 240, 231); // #FEF0E7
    public static final Color COLOR_RESALTADO = new Color(125, 255, 210); // #7DFFD2

    public static void main(String[] args) {
        if (!(new File(CAMINO_CONFIG).exists())) {
            configuracion = new Configuracion();
            try {
                ConfiguracionFicheroEscritura escritura = new ConfiguracionFicheroEscritura(CAMINO_CONFIG);
                escritura.escribir(configuracion);
                escritura.cerrarFichero();
            } catch (IOException e) {
                System.err.println("La configuracion no se a podido crear");
            }
        } else {
            ConfiguracionFicheroLectura temp = null;
            try {
                temp = new ConfiguracionFicheroLectura(CAMINO_CONFIG);
                configuracion = temp.leer();
            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {

            } finally {
                try {
                    temp.cerrarFichero();
                } catch (IOException ex) {

                }
            }
        }
        File file = new File(FICHERO_HISTORIAL);
        if (!file.exists()) {
            try {
                HistorialFicheroEscritura escritura = new HistorialFicheroEscritura(FICHERO_HISTORIAL);
            } catch (IOException ex) {

            }
        }

        ///////////////////Eliminar
        Partida partida = new Partida("Jose", 10, 10);
        HistorialFicheroEscritura escritura;
        try {
            escritura = new HistorialFicheroEscritura(FICHERO_HISTORIAL);
            escritura.escribir(partida);
            escritura.escribir(partida);
            escritura.escribir(partida);
            escritura.escribir(partida);
            escritura.escribir(partida);
            escritura.cerrarFichero();
        } catch (IOException ex) {
        }
        ///////////////////Eliminar

        ventana = new GestorVentanas();

    }

    public static Configuracion getConfiguracion() {
        return configuracion;
    }

    public static String getHistoria() {
        return FICHERO_HISTORIAL;
    }

    public static GestorVentanas getVentana() {
        return ventana;
    }
}
