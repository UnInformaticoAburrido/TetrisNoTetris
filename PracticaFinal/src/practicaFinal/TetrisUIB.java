package practicaFinal;

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
    
    /**
     * @param args the command line arguments 
     */
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
                
            }finally{
                try {
                    temp.cerrarFichero();
                } catch (IOException ex) {
                    
                }
            }
        }
        File file = new File(FICHERO_HISTORIAL);
        if (!file.exists()) {
            
        }
        GestorVentanas ventana = new GestorVentanas();
        
    }

    public static Configuracion getConfiguracion() {
        return configuracion;
    }
}
