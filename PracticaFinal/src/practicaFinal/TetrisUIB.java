package practicaFinal;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

/*
 * @author Dimitri Company Cifre
 * @author Joan Enric Soler Carvajal
 */
public class TetrisUIB {
    public static final String CAMINO_CONFIG = "tetrisuib.conf";
    private static final String FICHERO_HISTORIAL = "partidasTetrisUIB.dat";
    private static Configuracion configuracion;

    private static VentanaPrincipal ventana;

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

        ventana = new VentanaPrincipal();

    }

    // Guarda la configuración actual a un fichero en memoria secundaria.
    public static void guardarConfiguracionAFichero() {
        ConfiguracionFicheroEscritura escritura = null;

        try {
            escritura = new ConfiguracionFicheroEscritura(CAMINO_CONFIG);
            escritura.escribir(configuracion);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido guardar la configuración.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (escritura != null) {
                try {
                    escritura.cerrarFichero();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "No se ha podido cerrar el fichero de configuración.", "Error",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }

    public static Configuracion getConfiguracion() {
        return configuracion;
    }

    public static void setConfiguracion(Configuracion config) {
        configuracion = config;
    }

    public static String getHistoria() {
        return FICHERO_HISTORIAL;
    }

    public static VentanaPrincipal getVentana() {
        return ventana;
    }
}
