package practicaFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author dimit
 */
public class GestorVentanas extends MenuGenerico {
    private boolean mostrarIconos = true;
    private CardLayout centralLayaut = new CardLayout();
    private JPanel centralPanel = new JPanel(centralLayaut);

    public GestorVentanas() {
        JFrame ventana = new JFrame("Tetris UIB");
        Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setSize(dimensiones.width / 2, dimensiones.height / 2);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setJMenuBar(crearMenu(ventana,ventana));
        Container contenido = ventana.getContentPane();
        contenido.add(panelDeBotones(ventana), BorderLayout.WEST);
        
        //Insertamos el card layaut
        contenido.add(centralPanel, BorderLayout.CENTER);
        
        contenido.add(menuIconos(ventana), BorderLayout.NORTH);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        //Generamos las cartas
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.black);
        centralPanel.add(logoPanel, "LogoPanel");
        JPanel juegoPanel = new JPanel();
        //Insertar panel de juego
        centralPanel.add(juegoPanel,"JuegoPanel");
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(informacion(), BorderLayout.CENTER);
        centralPanel.add(infoPanel, "InfoPanel");
        
        JPanel historialPanel = new JPanel();
        historialPanel.add(historial());
        centralPanel.add(historialPanel, "HistorialPanel");
    }
    //Funcion para crear el menu
    //Funcion para crear el panel de botones con iconos
    //Funcion dedicada a crear el panel de botones laterales
    public JPanel panelDeBotones(JFrame padre) {
        JPanel panelbotones = new JPanel(new GridLayout(5, 1));
        //Boton de Partida
        JButton Partida = new JButton("Partida");
        Partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partida partida = empezarPartida(padre);
                //Aqui va la el metodo que llama al juego.
                try {
                    HistorialFicheroEscritura escribir = new HistorialFicheroEscritura(TetrisUIB.getHistoria());
                    escribir.escribir(partida);
                    escribir.cerrarFichero();
                } catch (IOException ex) {
                    
                }
            }
        });

        panelbotones.add(Partida);
        //Boton de Configuracion
        JButton Configuracion = new JButton("Configuración");
        Configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion(padre);
            }
        });

        panelbotones.add(Configuracion);
        //Boton de Historial
        JButton Historial = new JButton("Historial");
        Historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                centralLayaut.show(centralPanel, "HistorialPanel");
            }
        });

        panelbotones.add(Historial);
        //Boton de Informacion
        JButton Informacion = new JButton("Informacion");
        Informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayaut.show(centralPanel, "InfoPanel");
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

    public JMenuBar crearMenu(Container panelPrincipal,JFrame padre) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        //Menu general
        JMenuItem partida = new JMenuItem("Partida");
        partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida(padre);
            }
        });

        menu.add(partida);
        JMenuItem configuracion = new JMenuItem("Configuracion");
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion(padre);
            }
        });

        menu.add(configuracion);
        JMenuItem historial = new JMenuItem("Historial");
        historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historial();
            }
        });

        menu.add(historial);
        JMenuItem informacion = new JMenuItem("Informacion");
        informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayaut.show(centralPanel, "InfoPanel");
            }
        });

        menu.add(informacion);
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        menu.add(salir);
        //Segunda lista de menus porque me parece horrible tener un menu tan vacio.
        JMenu menuIconos = new JMenu("Barra Iconos");
        JCheckBoxMenuItem mostrar = new JCheckBoxMenuItem("Mostrar barra de iconos");
        mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(mostrarIconos);
                mostrarIconos = !mostrarIconos;
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                System.out.println(mostrarIconos);
            }
        });

        menuIconos.add(mostrar);
        menuBar.add(menu);
        menuBar.add(menuIconos);
        return menuBar;
    }

    public JToolBar menuIconos(JFrame padre) {
        JToolBar iconBar = new JToolBar();
        JButton Partida = new JButton("Partida");
        Partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida(padre);
            }
        });

        iconBar.add(Partida);
        //Boton de Configuracion
        JButton Configuracion = new JButton("Configuración");
        Configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion(padre);
            }
        });

        iconBar.add(Configuracion);
        //Boton de Historial
        JButton Historial = new JButton("Historial");
        Historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historial();
            }
        });

        iconBar.add(Historial);
        //Boton de Informacion
        JButton Informacion = new JButton("Informacion");
        Informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayaut.show(centralPanel, "InfoPanel");
            }
        });

        iconBar.add(Informacion);
        //Boton de Salir
        JButton Salir = new JButton("Salir");
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        iconBar.add(Salir);
        return iconBar;
    }
    
}
