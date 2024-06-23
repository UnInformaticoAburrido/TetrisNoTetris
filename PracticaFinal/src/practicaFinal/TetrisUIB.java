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
    private static final String CAMINO_CONFIG = "tetrisuib.conf";
    private static final String FICHERO_HISTORIAL = "partidasTetrisUIB.dat";
    private static Configuracion configuracion;

    private static VentanaPrincipal ventana;

    // Constantes de colores:
    private static final Color COLOR_PRINCIPAL = new Color(255, 138, 103); // #FF8A67
    private static final Color COLOR_SECUNDARIO = new Color(251, 204, 175); // #FBCCAF
    private static final Color COLOR_TERCIARIO = new Color(39, 105, 28); // #27691C
    private static final Color COLOR_FONDOS = new Color(254, 240, 231); // #FEF0E7
    private static final Color COLOR_RESALTADO = new Color(125, 255, 210); // #7DFFD2

    public static void main(String[] args) {
        if (!(new File(CAMINO_CONFIG).exists())) {
            configuracion = new Configuracion();

            try {
                ConfiguracionFicheroEscritura escritura = new ConfiguracionFicheroEscritura(CAMINO_CONFIG);
                escritura.escribir(configuracion);
                escritura.cerrarFichero();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No se ha posido crear el fichero de configuración.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ConfiguracionFicheroLectura lectura = null;

            try {
                lectura = new ConfiguracionFicheroLectura(CAMINO_CONFIG);
                configuracion = lectura.leer();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "No se ha posido leer el fichero de configuración.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    lectura.cerrarFichero();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "No se ha posido cerrar el fichero de configuración.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    public static Color getColorPrincipal() {
        return COLOR_PRINCIPAL;
    }

    public static Color getColorSecundario() {
        return COLOR_SECUNDARIO;
    }

    public static Color getColorTerciario() {
        return COLOR_TERCIARIO;
    }

    public static Color getColorFondos() {
        return COLOR_FONDOS;
    }

    public static Color getColorResaltado() {
        return COLOR_RESALTADO;
    }
}
