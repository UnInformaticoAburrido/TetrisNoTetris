package practicaFinal;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MenuGenerico {

    public static void empezarPartida(JFrame padre) {
        // Creamos una partida
        Partida partida = new Partida("", TetrisUIB.getConfiguracion().getTiempoPartida());

        JDialog preInicio = new JDialog(padre, "Tetris UIB");
        preInicio.setLayout(new GridLayout(2, 1));
        preInicio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nombreLabel = new JLabel("Inserta tu nombre: ");
        JTextField nombre = new JTextField();
        GridLayout layout = new GridLayout(1, 2);
        JPanel contenedor = new JPanel(layout);
        contenedor.add(nombreLabel);
        contenedor.add(nombre);
        preInicio.add(contenedor);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.setNombre(nombre.getText());
                preInicio.dispose();

                // Empieza la partida:

                PanelJuego panelJuego = new PanelJuego(partida);
                TetrisUIB.getVentana().setPanelJuego(panelJuego);
            }
        });

        JButton cancelarButton = new JButton("Cancelar");

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preInicio.dispose();
            }
        });

        JPanel panelBotones = new JPanel();

        contenedor.add(nombreLabel);

        contenedor.add(nombre);

        preInicio.add(contenedor);

        panelBotones.add(confirmarButton);

        panelBotones.add(cancelarButton);

        preInicio.add(panelBotones);

        preInicio.pack();

        preInicio.setLocationRelativeTo(padre);

        preInicio.setVisible(true);
    }

    public static boolean configuracion(JFrame padre) {

        // Generamos la ventana de selecion
        JDialog ventanaPreEntrada = new JDialog(padre, "Configuracion");

        ventanaPreEntrada.setSize(400, 100);

        ventanaPreEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Generamos el unico panel de la configuracion
        JPanel central = new JPanel();

        // Configuración específica juego
        JButton cej = new JButton("Configuración específica juego");
        cej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPreEntrada.dispose();
                ConfigVentana ventana = new ConfigVentana(padre);
            }
        });

        // Modificar tiempo empezarPartida
        JButton mtp = new JButton("Modificar tiempo partida");
        mtp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurarTiempoVentana ventana = new ConfigurarTiempoVentana(padre);
                ventanaPreEntrada.dispose();
            }
        });

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
        ventanaPreEntrada.setLocationRelativeTo(padre);

        ventanaPreEntrada.setVisible(true);

        return true;
    }

    public static void historial(JTextArea partidaTextArea) {

        boolean continuar = true;

        HistorialFicheroLectura lector = null;

        try {

            lector = new HistorialFicheroLectura(TetrisUIB.getHistoria());

            String text = "";

            while (continuar) {

                try {

                    Partida partida = lector.leer();

                    text += partida.toString() + '\n';

                } catch (EOFException e) {

                    continuar = false;

                } catch (ClassNotFoundException ex) {

                }

            }

            partidaTextArea.setText(text);

            lector.cerrarFichero();

        } catch (IOException ex) {

        } finally {
            if (lector != null) {
                try {
                    lector.cerrarFichero();
                } catch (IOException e) {
                }
            }
        }
    }

    public static JTextArea informacion() {

        JTextArea text = new JTextArea(
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

        text.setEditable(false);
        text.setOpaque(false);
        text.setFont(new Font("SansSerif", Font.PLAIN, 15));
        text.setMargin(new Insets(30, 30, 0, 0));

        return text;
    }

    public static void salir() {
        // Esta función permite salir del programa.
        System.out.println("Se ha salido del programa. (Code 0)");
        System.exit(0);
    }
}
