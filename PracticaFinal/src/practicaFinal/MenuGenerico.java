package practicaFinal;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author dimitry Esta clase tiene como objetivo quitarnos trabajo
 * centraliznado las funcionalidades
 */
public class MenuGenerico {

    public static void partida() {
        //Esta funcion deve iniciar la partida
        System.out.println("Funcionalidad no implementada");
    }

    public static boolean configuracion() {
        //Generamos la ventana de selecion
        JFrame ventanaPreEntrada = new JFrame();
        ventanaPreEntrada.setSize(300, 200);
        ventanaPreEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPreEntrada.setVisible(true);
        
        //Generamos el unico panel de la configuracion
        JPanel central = new JPanel();
        //Configuración específica juego
        JButton cej = new JButton("Configuración específica juego");
        cej.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPreEntrada.dispose();
                ConfigVentana ventana = new ConfigVentana();
            }
        });
        //Modificar tiempo partida
        JButton mtp = new JButton("Modificar tiempo partida");
        mtp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurarTiempoVentana ventana = new  ConfigurarTiempoVentana();
                ventanaPreEntrada.dispose();
            }
        });
        //Nada
        JButton nada = new JButton("Nada");
        nada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPreEntrada.dispose();
            }
        });
        central.add(cej);
        central.add(mtp);
        central.add(nada);
        ventanaPreEntrada.add(central);
        return true;
    }
        

    public static void historial() {
        //Esta funcion deve habrir el historial
        System.out.println("Funcionalidad no implementada");
    }

    public static void informacion() {
        //Esta funcion deve mostrar la informacion
        System.out.println("Funcionalidad no implementada");
    }

    public static void salir() {
        // Esta función permite salir del programa.
        System.out.println("Se ha salido del programa. (Code 0)");
        System.exit(0);
    }
}
