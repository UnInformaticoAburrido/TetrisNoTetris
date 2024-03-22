/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author dimit
 */
public class Ventana extends Opciones{

    public Ventana() {
        JFrame ventana = new JFrame("Tetris UIB");
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenido = ventana.getContentPane();
        contenido.add(panelDeBotones());
        ventana.setVisible(true);
    }

    public JPanel panelDeBotones() {
        JPanel panelbotones = new JPanel(new GridBagLayout());
        //Boton de Partida
        JButton Partida = new JButton("Partida");
        Partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida(panelbotones);
            }
        });
        panelbotones.add(Partida);
        //Boton de Configuracion
        JButton Configuracion = new JButton("Configuración");
        Configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion();
            }
        });
        panelbotones.add(Configuracion);
        //Boton de Historial
        JButton Historial = new JButton("Historial");
        Historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historial();
            }
        });
        panelbotones.add(Historial);
        //Boton de Informacion
        JButton Informacion = new JButton("Informacion");
        Informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informacion();
            }
        });
        panelbotones.add(Informacion);
        //Boton de Salir
        JButton Salir = new JButton("Salir");
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        panelbotones.add(Salir);
        return panelbotones;
    }
}
