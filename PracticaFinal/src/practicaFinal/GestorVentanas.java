package practicaFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class GestorVentanas extends MenuGenerico {
    private CardLayout centralLayout = new CardLayout();
    private JPanel centralPanel = new JPanel(centralLayout);
    private JPanel panelContenedorJuego = new JPanel(new BorderLayout());
    private JTextArea historialTextArea = new JTextArea();
    private PanelJuego panelJuego = null;

    public void setPanelJuego(PanelJuego panelJuego) {
        if (this.panelJuego != null) {
            // Quitamos el panel de juego del contenedor.
            panelContenedorJuego.remove(this.panelJuego);
        }

        this.panelJuego = panelJuego;

        panelContenedorJuego.add(panelJuego, BorderLayout.CENTER);
        panelContenedorJuego.updateUI(); // Parecido al update pero sin parámetros.
    }

    public GestorVentanas() {
        JFrame ventana = new JFrame("Tetris UIB");
        ventana.setSize(1100, 840);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setJMenuBar(crearMenu(ventana, ventana));
        Container contenido = ventana.getContentPane();
        contenido.add(panelDeBotones(ventana), BorderLayout.WEST);

        // Insertamos el card layout
        contenido.add(centralPanel, BorderLayout.CENTER);

        contenido.add(menuIconos(ventana), BorderLayout.NORTH);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        // Generamos las cartas
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(TetrisUIB.COLOR_FONDOS);

        JLabel imagenLogo = new JLabel();
        imagenLogo.setIcon(new ImageIcon("img/logo.png"));
        logoPanel.add(imagenLogo);

        centralPanel.add(logoPanel, "LogoPanel");
        // Insertar panel de juego
        centralPanel.add(panelContenedorJuego, "JuegoPanel");
        // Insertamos el panel de informacion
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(TetrisUIB.COLOR_FONDOS);
        infoPanel.add(informacion(), BorderLayout.CENTER);
        centralPanel.add(infoPanel, "InfoPanel");

        // Insertamos el panel de historial
        JPanel historialPanel = new JPanel();
        historialTextArea.setEditable(false);
        historialPanel.add(historialTextArea);
        centralPanel.add(historialPanel, "HistorialPanel");
    }

    // Funcion dedicada a crear el panel de botones laterales
    public JPanel panelDeBotones(JFrame padre) {
        JPanel panelbotones = new JPanel(new GridLayout(5, 1));
        // Boton de Partida
        JButton partida = new JButton("Partida");
        partida.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        partida.setForeground(TetrisUIB.COLOR_TERCIARIO);
        partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida(padre);
                centralLayout.show(centralPanel, "JuegoPanel");
            }
        });

        panelbotones.add(partida);
        // Boton de Configuracion
        JButton configuracion = new JButton("Configuración");
        configuracion.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        configuracion.setForeground(TetrisUIB.COLOR_TERCIARIO);
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion(padre);
            }
        });

        panelbotones.add(configuracion);
        // Boton de Historial
        JButton historial = new JButton("Historial");
        historial.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        historial.setForeground(TetrisUIB.COLOR_TERCIARIO);
        historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historial(historialTextArea);
            }
        });

        panelbotones.add(historial);
        // Boton de Informacion
        JButton informacion = new JButton("Informacion");
        informacion.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        informacion.setForeground(TetrisUIB.COLOR_TERCIARIO);
        informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayout.show(centralPanel, "InfoPanel");
            }
        });

        panelbotones.add(informacion);

        // Boton de Salir
        JButton salir = new JButton("Salir");
        salir.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        salir.setForeground(TetrisUIB.COLOR_TERCIARIO);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        panelbotones.add(salir);
        return panelbotones;
    }

    // Funcion para crear el menu
    public JMenuBar crearMenu(Container panelPrincipal, JFrame padre) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        JMenu menu = new JMenu("Menu");
        // Menu general
        JMenuItem partida = new JMenuItem("Partida");
        partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida(padre);
                centralLayout.show(centralPanel, "JuegoPanel");
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
                centralLayout.show(centralPanel, "HistorialPanel");
            }
        });

        menu.add(historial);
        JMenuItem informacion = new JMenuItem("Informacion");
        informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayout.show(centralPanel, "InfoPanel");
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

        menuBar.add(menu);
        return menuBar;
    }

    // Funcion para crear el panel de botones con iconos
    public JToolBar menuIconos(JFrame padre) {
        JToolBar iconBar = new JToolBar();
        iconBar.setBackground(TetrisUIB.COLOR_PRINCIPAL);
        iconBar.setFloatable(false); // Quita la barra que permite mover la JToolBar.s
        JButton partida = new JButton();
        partida.setIcon(new ImageIcon("img/play.png"));
        partida.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        partida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartida(padre);
                centralLayout.show(centralPanel, "JuegoPanel");
            }
        });

        iconBar.add(partida);
        // Boton de Configuracion
        JButton configuracion = new JButton();
        configuracion.setIcon(new ImageIcon("img/settings.png"));
        configuracion.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        configuracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion(padre);
            }
        });

        iconBar.add(configuracion);
        // Boton de Historial
        JButton historial = new JButton();
        historial.setIcon(new ImageIcon("img/history.png"));
        historial.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayout.show(centralPanel, "HistorialPanel");
            }
        });

        iconBar.add(historial);
        // Boton de Informacion
        JButton informacion = new JButton();
        informacion.setIcon(new ImageIcon("img/info.png"));
        informacion.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        informacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralLayout.show(centralPanel, "InfoPanel");
            }
        });

        iconBar.add(informacion);
        // Boton de Salir
        JButton salir = new JButton();
        salir.setIcon(new ImageIcon("img/exit.png"));
        salir.setBackground(TetrisUIB.COLOR_SECUNDARIO);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        iconBar.add(salir);
        return iconBar;
    }
}
