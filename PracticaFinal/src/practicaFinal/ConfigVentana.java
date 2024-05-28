package practicaFinal;

import javax.swing.JFrame;

/**
 *
 * @author dima
 */
public class ConfigVentana extends JFrame{
    public ConfigVentana(){
        setTitle("Configuracion");
        setSize(300, 200);
        setDefaultCloseOperation(ConfigVentana.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
