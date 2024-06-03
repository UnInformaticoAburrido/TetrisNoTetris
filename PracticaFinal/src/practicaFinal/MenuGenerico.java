package practicaFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

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
        cej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPreEntrada.dispose();
                ConfigVentana ventana = new ConfigVentana();
            }
        });
        //Modificar tiempo partida
        JButton mtp = new JButton("Modificar tiempo partida");
        mtp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurarTiempoVentana ventana = new ConfigurarTiempoVentana();
                ventanaPreEntrada.dispose();
            }
        });
        //Nada
        JButton nada = new JButton("Nada");
        nada.addActionListener(new ActionListener() {
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

    public static JTextArea informacion() {
        
        JTextArea text = new JTextArea("DISCLAIMER: Esta aplicacion ha sido tealizada por un grupo de estudientes en el contexto de practica del primer curso de los estudios de ingenieria informatica de la UNIVERSITAT DE LES ILLES BALEARS para el curso academico 2023-24."+
"Los objetivos de esta practica pasan por trabajar con un entorno grafico he interactivo utilizando las prestaciones que ofrecen las livrerias graficas de java y la aplucacion de los conceptos de objetos y tipos de datos abstractos correspondientes a la programacion orientada a objertos.\n"+
"El bojetivo de este juego se basa en encajar multiples piezas aleatorias las cuales se mostraran en el panel de piezas y la deveras colocar en tablero.\n"+
"Al conseguir formar una columna o una fila todas las piezas que formen la fila/columna desapareceran y te otorgaran puntos por cada celda.\n"+
"Podras generar una nueva pieza consumiendo puntos en el proceso.\n"+
"Podras rotar las piezas perdiendo puntos en el proceso.\n"+
"El juego terminara cuando se termina al terminarse el tiempo de juego.\n");
        
        text.setEditable(false);
        text.setOpaque(false);
        text.setLineWrap(true);
        
        return text;
    }

    public static void salir() {
        // Esta función permite salir del programa.
        System.out.println("Se ha salido del programa. (Code 0)");
        System.exit(0);
    }
}
