package practicaFinal;

import java.awt.GridLayout;
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

public class AccionesBotones {

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
                VentanaPrincipal ventana = TetrisUIB.getVentana();
                ventana.setPanelJuego(panelJuego);
                ventana.cambiarPanel("JuegoPanel");

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
}
