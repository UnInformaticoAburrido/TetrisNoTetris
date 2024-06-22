package practicaFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
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

public class VentanaPrincipal extends MenuGenerico {
    private CardLayout centralLayout = new CardLayout();
    private JPanel centralPanel = new JPanel(centralLayout);
    private JPanel panelContenedorJuego = new JPanel(new BorderLayout());
    private JTextArea historialTextArea = new JTextArea();
    private PanelJuego panelJuego = null;

    private JFrame ventana = new JFrame("Tetris UIB");

    // Definimos las acciones que hacen los botones.
    // Se define como atributo de la clase para poder ser usado múltiples veces.
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Nueva Partida" -> empezarPartida(ventana);
                case "Configuración" -> configuracion(ventana);
                case "Historial" -> historial(historialTextArea);
                case "Información" -> cambiarPanel("InfoPanel");
                case "Salir" -> System.exit(0);
            }
        }
    };

    public void setPanelJuego(PanelJuego panelJuego) {
        if (this.panelJuego != null) {
            // Quitamos el panel de juego del contenedor.
            panelContenedorJuego.remove(this.panelJuego);
        }

        this.panelJuego = panelJuego;

        panelContenedorJuego.add(panelJuego, BorderLayout.CENTER);
        panelContenedorJuego.updateUI(); // Parecido al update pero sin parámetros.
    }

    // Permite cambiar el panel que se muestra en el centro desde una clase externa.
    public void cambiarPanel(String nombre) {
        centralLayout.show(centralPanel, nombre);
    }

    public VentanaPrincipal() {
        ventana.setSize(1120, 840);
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

        Font fuenteBotones = new Font("SansSerif", Font.BOLD, 16);

        for (int i = 0; i < 5; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.COLOR_SECUNDARIO);
            boton.setForeground(TetrisUIB.COLOR_TERCIARIO);
            boton.setFont(fuenteBotones);
            boton.addActionListener(actionListener);

            switch (i) {
                case 0 -> boton.setText("Nueva Partida");
                case 1 -> boton.setText("Configuración");
                case 2 -> boton.setText("Historial");
                case 3 -> boton.setText("Información");
                case 4 -> boton.setText("Salir");
            }

            panelbotones.add(boton);
        }

        return panelbotones;
    }

    // Funcion para crear el menu
    public JMenuBar crearMenu(Container panelPrincipal, JFrame padre) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(TetrisUIB.COLOR_SECUNDARIO);

        JMenu menu = new JMenu("Menú");
        menu.setForeground(TetrisUIB.COLOR_TERCIARIO);

        for (int i = 0; i < 5; i++) {
            JMenuItem item = new JMenuItem();
            item.addActionListener(actionListener);

            switch (i) {
                case 0 -> item.setText("Nueva Partida");
                case 1 -> item.setText("Configuración");
                case 2 -> item.setText("Historial");
                case 3 -> item.setText("Información");
                case 4 -> item.setText("Salir");
            }

            menu.add(item);
        }

        menuBar.add(menu);
        return menuBar;
    }

    // Funcion para crear el panel de botones con iconos
    public JToolBar menuIconos(JFrame padre) {
        JToolBar iconBar = new JToolBar();
        iconBar.setBackground(TetrisUIB.COLOR_PRINCIPAL);
        iconBar.setFloatable(false); // Quita la barra que permite mover la JToolBar.

        for (int i = 0; i < 5; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.COLOR_SECUNDARIO);
            boton.addActionListener(actionListener);

            // NOTA: setActionCommand() se usa para que el actionListener sepa en qué
            // botón estamos pulsando.
            switch (i) {
                case 0:
                    boton.setIcon(new ImageIcon("img/play.png"));
                    boton.setActionCommand("Nueva Partida");
                    break;

                case 1:
                    boton.setIcon(new ImageIcon("img/settings.png"));
                    boton.setActionCommand("Configuración");
                    break;

                case 2:
                    boton.setIcon(new ImageIcon("img/history.png"));
                    boton.setActionCommand("Historial");
                    break;

                case 3:
                    boton.setIcon(new ImageIcon("img/info.png"));
                    boton.setActionCommand("Información");
                    break;

                case 4:
                    boton.setIcon(new ImageIcon("img/exit.png"));
                    boton.setActionCommand("Salir");
                    break;
            }

            iconBar.add(boton);
        }

        return iconBar;
    }
}
