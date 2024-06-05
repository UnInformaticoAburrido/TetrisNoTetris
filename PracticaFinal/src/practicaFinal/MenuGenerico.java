package practicaFinal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MenuGenerico {

    public static void empezarPartida(JFrame padre) {
        //Creamos un jugador
        Partida partida = new Partida("", TetrisUIB.getConfiguracion().getTiempoPartida(), 0);

        JDialog preInicio = new JDialog(padre, "Tetris UIB");
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
        panelBotones.add(confirmarButton);
        panelBotones.add(cancelarButton);
        preInicio.add(panelBotones);
        preInicio.pack();
        preInicio.setVisible(true);
    }

    public static boolean configuracion(JFrame padre) {
        //Generamos la ventana de selecion
        JDialog ventanaPreEntrada = new JDialog(padre, "Configuracion");
        ventanaPreEntrada.setSize(400, 100);
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
        //Modificar tiempo empezarPartida
        JButton mtp = new JButton("Modificar tiempo partida");
        mtp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurarTiempoVentana ventana = new ConfigurarTiempoVentana();
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
        return true;
    }

    public static void historial() {

        JPanel historialPanel = new JPanel();

        System.out.println("Funcionalidad no implementada");
    }

    public static JTextArea informacion() {

        JTextArea text = new JTextArea(
                "DISCLAIMER: Esta aplicacion ha sido tealizada por un grupo de estudientes en el contexto de practica del primer curso de los estudios de ingenieria informatica de la UNIVERSITAT DE LES ILLES BALEARS para el curso academico 2023-24. Los objetivos de esta practica pasan por trabajar con un entorno grafico he interactivo utilizando las prestaciones que ofrecen las livrerias graficas de java y la aplucacion de los conceptos de objetos y tipos de datos abstractos correspondientes a la programacion orientada a objertos.\n"
                        + "El bojetivo de este juego se basa en encajar multiples piezas aleatorias las cuales se mostraran en el panel de piezas y la deveras colocar en tablero.\n"
                        + "Al conseguir formar una columna o una fila todas las piezas que formen la fila/columna desapareceran y te otorgaran puntos por cada celda.\n"
                        + "Podras generar una nueva pieza consumiendo puntos en el proceso.\n"
                        + "Podras rotar las piezas perdiendo puntos en el proceso.\n"
                        + "El juego terminara cuando se termina al terminarse el tiempo de juego.\n");

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
