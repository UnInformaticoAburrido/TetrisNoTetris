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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccionesBotones {

    public static void empezarPartida(JFrame padre) {
        JDialog preInicio = new JDialog(padre, "Tetris UIB");
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

                VentanaPrincipal ventana = TetrisUIB.getVentana();
                ventana.getPanelJuego().empezarPartida(nombreCampoDeTexto.getText());
                ventana.cambiarPanel("JuegoPanel");
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
        preInicio.setLocationRelativeTo(padre);
        preInicio.setVisible(true);
    }

    public static boolean configuracion(JFrame padre) {

        // Generamos la ventana de selección del tipo de configuración a modificar:

        JDialog ventanaPreEntrada = new JDialog(padre, "Configuracion");
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
                    case "Configuración específica del juego" -> new ConfigVentana(padre);
                    case "Modificar tiempo de la partida" -> new ConfigurarTiempoVentana(padre);
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
        ventanaPreEntrada.setLocationRelativeTo(padre);

        ventanaPreEntrada.setVisible(true);

        return true;
    }

    public static void historial(JTextArea partidaTextArea) {

        PartidaFicheroInOut fichero = null;

        try {
            fichero = new PartidaFicheroInOut(TetrisUIB.getHistoria());

            String text = "";

            boolean continuar = true;
            while (continuar) {
                try {
                    Partida partida = fichero.lectura();
                    text += partida.toString() + '\n';
                } catch (EOFException e) {
                    continuar = false;
                }
            }

            partidaTextArea.setText(text);
            TetrisUIB.getVentana().cambiarPanel("HistorialPanel");

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
}
