package practicaFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class VentanaPrincipal extends JFrame {
    private CardLayout centralLayout = new CardLayout();
    private JPanel centralPanel = new JPanel(centralLayout);
    private JTextArea historialTextArea = new JTextArea();
    private PanelJuego panelJuego = new PanelJuego(new Partida());

    // Permite acceder a la ventana dentro de un ActionListener.
    private JFrame ventana = this;

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
                case "Nueva Partida" -> nuevaPartida();
                case "Configuración" -> configuracion();
                case "Historial" -> historial();
                case "Información" -> cambiarPanel("InfoPanel");
                case "Salir" -> System.exit(0);
            }
        }
    };

    // Permite cambiar el panel que se muestra en el centro, incluso desde una clase externa.
    public void cambiarPanel(String nombre) {
        centralLayout.show(centralPanel, nombre);
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public VentanaPrincipal() {
        setTitle("Tetris UIB");
        setSize(1120, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(crearMenu());

        Container contenido = getContentPane();
        contenido.add(crearPanelDeBotones(), BorderLayout.WEST);
        contenido.add(centralPanel, BorderLayout.CENTER);
        contenido.add(crearToolBar(), BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);

        // Generamos las cartas
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(TetrisUIB.getColorFondos());

        JLabel imagenLogo = new JLabel();
        imagenLogo.setIcon(new ImageIcon("img/logo.png"));
        logoPanel.add(imagenLogo);

        centralPanel.add(logoPanel, "LogoPanel");
        // Insertar panel de juego
        centralPanel.add(panelJuego, "JuegoPanel");
        // Insertamos el panel de informacion
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(TetrisUIB.getColorFondos());
        infoPanel.add(informacionTextArea(), BorderLayout.CENTER);

        centralPanel.add(infoPanel, "InfoPanel");

        // Insertamos el panel de historial
        JPanel historialPanel = new JPanel();
        historialPanel.setBackground(TetrisUIB.getColorFondos());
        historialTextArea.setEditable(false);
        historialTextArea.setOpaque(false);
        historialTextArea.setForeground(TetrisUIB.getColorTerciario());
        historialTextArea.setFont(new Font("SansSerif", Font.PLAIN, 15));

        historialPanel.add(historialTextArea);
        centralPanel.add(historialPanel, "HistorialPanel");
    }

    // Funcion dedicada a crear el panel de botones laterales
    private JPanel crearPanelDeBotones() {
        JPanel panelbotones = new JPanel(new GridLayout(5, 1));

        Font fuenteBotones = new Font("SansSerif", Font.BOLD, 16);

        for (int i = 0; i < 5; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.getColorSecundario());
            boton.setForeground(TetrisUIB.getColorTerciario());
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
    private JMenuBar crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(TetrisUIB.getColorSecundario());

        JMenu menu = new JMenu("Menú");
        menu.setForeground(TetrisUIB.getColorTerciario());

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
    private JToolBar crearToolBar() {
        JToolBar iconBar = new JToolBar();
        iconBar.setBackground(TetrisUIB.getColorPrincipal());
        iconBar.setFloatable(false); // Quita la barra que permite mover la JToolBar.

        for (int i = 0; i < 5; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.getColorSecundario());
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

    private void nuevaPartida() {
        JDialog preInicio = new JDialog(this, "Tetris UIB");
        preInicio.setLayout(new GridLayout(2, 1));
        preInicio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contenedor = new JPanel(new GridLayout(1, 2));
        contenedor.setBackground(TetrisUIB.getColorFondos());

        JLabel nombreLabel = new JLabel("Inserta tu nombre: ");
        nombreLabel.setForeground(TetrisUIB.getColorTerciario());
        JTextField nombreCampoDeTexto = new JTextField();

        contenedor.add(nombreLabel);
        contenedor.add(nombreCampoDeTexto);

        preInicio.add(contenedor);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TetrisUIB.getColorFondos());

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(TetrisUIB.getColorSecundario());
        confirmarButton.setForeground(TetrisUIB.getColorTerciario());
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreCampoDeTexto.getText();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes introducir un nombre.", "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                if (nombre.length() > 20) {
                    JOptionPane.showMessageDialog(null, "El nombre debe ser menor a 20 carácteres.", "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                preInicio.dispose();

                // Empieza la partida:

                panelJuego.empezarPartida(nombreCampoDeTexto.getText());
                cambiarPanel("JuegoPanel");
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(TetrisUIB.getColorSecundario());
        cancelarButton.setForeground(TetrisUIB.getColorTerciario());
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preInicio.dispose();
            }
        });

        panelBotones.add(confirmarButton);
        panelBotones.add(cancelarButton);

        preInicio.add(panelBotones);

        preInicio.pack();
        preInicio.setLocationRelativeTo(this);
        preInicio.setVisible(true);
    }

    private boolean configuracion() {

        // Generamos la ventana de selección del tipo de configuración a modificar:

        JDialog ventanaPreEntrada = new JDialog(this, "Configuracion");
        ventanaPreEntrada.setSize(485, 100);
        ventanaPreEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Generamos el unico panel de la configuracion
        JPanel panel = new JPanel();
        panel.setBackground(TetrisUIB.getColorFondos());

        // Definimos las acciones de los botones.
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "Configuración específica del juego" -> new ConfigGeneralVentana(ventana);
                    case "Modificar tiempo de la partida" -> new ConfigTiempoVentana(ventana);
                }

                ventanaPreEntrada.dispose();
            }
        };

        // Generamos los botones.
        for (int i = 0; i < 3; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.getColorSecundario());
            boton.setForeground(TetrisUIB.getColorTerciario());
            boton.addActionListener(actionListener);

            switch (i) {
                case 0 -> boton.setText("Configuración específica del juego");
                case 1 -> boton.setText("Modificar tiempo de la partida");
                case 2 -> boton.setText("Nada");
            }

            panel.add(boton);
        }

        ventanaPreEntrada.add(panel);
        ventanaPreEntrada.setLocationRelativeTo(this);

        ventanaPreEntrada.setVisible(true);

        return true;
    }

    private void historial() {

        PartidaFicheroInOut fichero = null;

        try {
            fichero = new PartidaFicheroInOut(TetrisUIB.getCaminoHistorial());

            String text = "\n Historial de partidas:\n\n";

            boolean continuar = true;
            while (continuar) {
                try {
                    Partida partida = fichero.lectura();
                    text += partida.toString() + '\n';
                } catch (EOFException e) {
                    continuar = false;
                }
            }

            historialTextArea.setText(text);
            cambiarPanel("HistorialPanel");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "No se ha podido leer el fichero de partidas.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (fichero != null) {
                try {
                    fichero.cerrarFichero();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "No se ha podido cerrar el fichero de partidas.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private JTextArea informacionTextArea() {

        JTextArea textArea = new JTextArea(
                "\n\n\n           Esta aplicación ha sido realizada por un grupo de estudiantes en el contexto de práctica del primer curso de los estudios de\n"
                        + "           Ingeniería Informática de la UNIVERSITAT DE LES ILLES BALEARS (UIB) para el curso académico 2023-24.\n\n"
                        + "           Los objetivos de esta práctica pasan por trabajar con un entorno gráfico e interactivo utilizando las prestaciones que ofrecen\n"
                        + "           las librerías gráficas de Java (swing y awt), la aplicación de los conceptos de programación orientada a objetos y\n"
                        + "           el uso de ficheros para almacenar información en memória secundária.\n\n"
                        + "           El objetivo de este juego se basa en encajar múltiples piezas generadas de manera aleatoria las cuales se mostrarán en el panel\n"
                        + "           de piezas correspondiente y colocarlas en el tablero.\n"
                        + "           Al conseguir formar una columna o una fila, todas las piezas que formen la fila / columna desaparecerán y otorgarán\n"
                        + "           puntos por cada celda.\n\n"
                        + "           De ser preciso, se pueden hacer las siguientes acciones adicionales haciendo uso de una determinada cantidad de puntos:\n\n"
                        + "           \t- Se pueden generar nuevas piezas.\n"
                        + "           \t- Se pueden rotar las piezas en el sentido horario.\n\n"
                        + "           El coste de las acciones se puede personalizar en el apartado de configuración.\n\n"
                        + "           El juego terminará al terminarse el tiempo de juego, modificable mediante la ventana de configuración correspondiente.\n\n"
                        + "           Las partidas finalizadas se guardarán en el historial que se puede consultar mediante los diferentes botones.");

        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(TetrisUIB.getColorTerciario());
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 15));

        return textArea;
    }
}
