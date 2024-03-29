package practicaFinal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Ventana extends MenuGenerico {

    private boolean mostrarIconos = true;

    public Ventana() {
        JFrame ventana = new JFrame("Tetris UIB");
        Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setSize(dimensiones.width / 2, dimensiones.height / 2);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setJMenuBar(crearMenu(ventana));
        Container contenido = ventana.getContentPane();
        contenido.add(panelDeBotones(), BorderLayout.WEST);
        contenido.add(crearPantallaJuego(), BorderLayout.CENTER);
        contenido.add(MenuIconos(), BorderLayout.NORTH);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    //Funcion para crear el menu
    //Funcion para crear el panel de botones con iconos
    //Funcion dedicada a crear el panel de botones laterales
    public JPanel panelDeBotones() {
        JPanel panelbotones = new JPanel(new GridLayout(5, 1));
        //Boton de Partida
        JButton Partida = new JButton("Partida");
        Partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida();
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

    public JMenuBar crearMenu(Container panelPrincipal) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        //Menu general
        JMenuItem partida = new JMenuItem("Partida");
        partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida();
            }
        });

        menu.add(partida);
        JMenuItem configuracion = new JMenuItem("Configuracion");
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion();
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
                informacion();
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

    public JToolBar MenuIconos() {
        JToolBar iconBar = new JToolBar();
        JButton Partida = new JButton("Partida");
        Partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida();
            }
        });

        iconBar.add(Partida);
        //Boton de Configuracion
        JButton Configuracion = new JButton("Configuración");
        Configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion();
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
                informacion();
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

    public JPanel crearPantallaJuego() {
        JPanel penelJuego = new JPanel();
        //Cremos el objeto tablero.
        return penelJuego;
    }
}
