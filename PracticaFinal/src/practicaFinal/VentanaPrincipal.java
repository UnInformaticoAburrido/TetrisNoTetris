package practicaFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class VentanaPrincipal {
    private CardLayout centralLayout = new CardLayout();
    private JPanel centralPanel = new JPanel(centralLayout);
    private JTextArea historialTextArea = new JTextArea();
    private PanelJuego panelJuego = new PanelJuego(new Partida());

    private JFrame ventana = new JFrame("Tetris UIB");

    // Definimos las acciones que hacen los botones.
    // Se define como atributo de la clase para poder ser usado múltiples veces.
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            // Impedimos que el usuario pueda hacer alguna acción que no sea salir
            // cuando hay una partida en curso.
            if (!actionCommand.equals("Salir") && panelJuego.isPartidaEnCurso()) {
                JOptionPane.showMessageDialog(ventana,
                        "Debes terminar la partida antes de poder hacer cualquier otra acción.", "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE);

                return;
            }

            switch (e.getActionCommand()) {
                case "Nueva Partida" -> AccionesBotones.empezarPartida(ventana);
                case "Configuración" -> AccionesBotones.configuracion(ventana);
                case "Historial" -> AccionesBotones.historial(historialTextArea);
                case "Información" -> cambiarPanel("InfoPanel");
                case "Salir" -> System.exit(0);
            }
        }
    };

    // Permite cambiar el panel que se muestra en el centro desde una clase externa.
    public void cambiarPanel(String nombre) {
        centralLayout.show(centralPanel, nombre);
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
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
        centralPanel.add(panelJuego, "JuegoPanel");
        // Insertamos el panel de informacion
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(TetrisUIB.COLOR_FONDOS);
        infoPanel.add(informacionTextArea(), BorderLayout.CENTER);

        centralPanel.add(infoPanel, "InfoPanel");

        // Insertamos el panel de historial
        JPanel historialPanel = new JPanel();
        historialPanel.setBackground(TetrisUIB.COLOR_FONDOS);
        historialTextArea.setEditable(false);
        historialTextArea.setOpaque(false);
        historialTextArea.setForeground(TetrisUIB.COLOR_TERCIARIO);
        historialTextArea.setFont(new Font("SansSerif", Font.PLAIN, 15));

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

    private JTextArea informacionTextArea() {

        JTextArea textArea = new JTextArea(
                "DISCLAIMER:\n"
                        + "Esta aplicación ha sido realizada por un grupo de estudiantes en el contexto de práctica del primer curso de los estudios de\n"
                        + "Ingeniería Informática de la UNIVERSITAT DE LES ILLES BALEARS (UIB) para el curso académico 2023-24.\n\n"
                        + "Los objetivos de esta práctica pasan por trabajar con un entorno gráfico e interactivo utilizando las prestaciones que ofrecen\n"
                        + "las librerías gráficas de Java (swing y awt) y la aplicación de los conceptos de programación orientada a objetos.\n\n"
                        + "El objetivo de este juego se basa en encajar múltiples piezas generadas de manera aleatoria las cuales se mostrarán en el panel\n"
                        + "de piezas correspondiente y colocarlas en el tablero.\n"
                        + "Al conseguir formar una columna o una fila, todas las piezas que formen la fila / columna desaparecerán y otorgarán\n"
                        + "puntos por cada celda.\n\n"
                        + "De ser preciso, se pueden hacer las siguientes acciones adicionales haciendo uso de una determinada cantidad de puntos:\n\n"
                        + "\t- Se pueden generar nuevas piezas.\n"
                        + "\t- Se pueden rotar las piezas en el sentido horario.\n\n"
                        + "El coste de las acciones se puede personalizar en el apartado de configuración.\n\n"
                        + "El juego terminará al terminarse el tiempo de juego.\n"
                        + "Este también se puede modificar en el apartado de configuración correspondiente.");

        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(TetrisUIB.COLOR_TERCIARIO);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        textArea.setMargin(new Insets(30, 30, 0, 0)); // Insets es para poner margenes, similar a la clase Rectangle.

        return textArea;
    }
}
