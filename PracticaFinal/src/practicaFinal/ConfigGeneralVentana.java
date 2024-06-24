package practicaFinal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigGeneralVentana extends JDialog {

    private Configuracion configActual;
    private String ultimoCaminoFileChooser;
    private JTextField[] camposDeTexto = new JTextField[4];

    public ConfigGeneralVentana(JFrame padre) {
        super(padre);

        configActual = TetrisUIB.getConfiguracion();

        File ficheroImagen = new File(configActual.getImagenCasillasFormas());

        ultimoCaminoFileChooser = ficheroImagen.getAbsolutePath();

        setTitle("Configuracion");
        setSize(790, 240);
        setDefaultCloseOperation(ConfigGeneralVentana.DISPOSE_ON_CLOSE);
        add(generarPanelPrincipal());

        setLocationRelativeTo(padre);
        setVisible(true);
    }

    private JPanel generarPanelPrincipal() {
        JPanel panelPrincipal = new JPanel(new GridLayout(5, 1));
        panelPrincipal.setBackground(TetrisUIB.getColorFondos());

        GridLayout layoutPaneles = new GridLayout(1, 2);

        // Añadimos los paneles para modificar puntuaciones:
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel(layoutPaneles);
            panel.setBackground(TetrisUIB.getColorFondos());

            JLabel label = new JLabel();
            label.setForeground(TetrisUIB.getColorTerciario());

            String texto;
            int valorEnConfig;

            switch (i) {
                case 0:
                    texto = "Puntuación por cada casilla eliminada del tablero";
                    valorEnConfig = configActual.getPuntuacionCasillasEliminadas();
                    break;

                case 1:
                    texto = "Puntuación por rotar la forma";
                    valorEnConfig = configActual.getPuntuacionRotarForma();
                    break;

                case 2:
                    texto = "Puntuación por generar una nueva forma";
                    valorEnConfig = configActual.getPuntuacionNuevaForma();
                    break;

                // Podemos evitar errores si ponemos valores por defecto:
                default:
                    texto = "";
                    valorEnConfig = 0;
            }

            texto += " [ " + valorEnConfig + " puntos ]";
            label.setText(texto);

            panel.add(label);

            JTextField campoDeTexto = new JTextField();
            campoDeTexto.setText(Integer.toString(valorEnConfig));

            panel.add(campoDeTexto);

            panelPrincipal.add(panel);

            // Permite acceder al contenido del campo de texto más tarde
            // a la hora de guardar las configuraciones.
            camposDeTexto[i] = campoDeTexto;
        }

        // Añadimos el panel para elegir la imagen de las casillas:

        JPanel panelImagenCasillas = new JPanel(new GridLayout(1, 3));
        panelImagenCasillas.setBackground(TetrisUIB.getColorFondos());

        String imagenCasillas = configActual.getImagenCasillasFormas();

        JLabel etiquetaImagen = new JLabel("Imagen de las casillas [" + imagenCasillas + "]");
        etiquetaImagen.setForeground(TetrisUIB.getColorTerciario());

        JTextField campoTextoImagen = new JTextField(imagenCasillas);

        JButton botonBuscarImagen = new JButton("Buscar Archivo");
        botonBuscarImagen.setBackground(TetrisUIB.getColorSecundario());
        botonBuscarImagen.setForeground(TetrisUIB.getColorTerciario());

        botonBuscarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivoSelecionado = new File(ultimoCaminoFileChooser);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(archivoSelecionado);

                int resultado = fileChooser.showOpenDialog(panelPrincipal);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    archivoSelecionado = fileChooser.getSelectedFile();

                    if (archivoSelecionado != null) {
                        ultimoCaminoFileChooser = archivoSelecionado.getAbsolutePath();
                        campoTextoImagen.setText(archivoSelecionado.getPath());
                    }
                }
            }
        });

        panelImagenCasillas.add(etiquetaImagen);
        panelImagenCasillas.add(botonBuscarImagen);
        panelImagenCasillas.add(campoTextoImagen);

        panelPrincipal.add(panelImagenCasillas);

        camposDeTexto[3] = campoTextoImagen;

        // Añadimos el panel de los botones inferiores:

        ActionListener accionesBotones = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "Aceptar":
                        if (aplicarCambios()) {
                            // Solo se cierra si se aplicaron los cambios con éxito.
                            dispose();
                        }
                        break;

                    case "Aplicar cambios":
                        // Aplica los cambios sin cerrar la ventana.
                        aplicarCambios();
                        break;

                    case "Cancelar":
                        // Cierra la ventana sin guardar los cambios.
                        dispose();
                        break;
                }
            }
        };

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(TetrisUIB.getColorFondos());

        for (int i = 0; i < 3; i++) {
            JButton boton = new JButton();
            boton.setBackground(TetrisUIB.getColorSecundario());
            boton.setForeground(TetrisUIB.getColorTerciario());
            boton.addActionListener(accionesBotones);

            switch (i) {
                case 0 -> boton.setText("Aplicar cambios");
                case 1 -> boton.setText("Aceptar");
                case 2 -> boton.setText("Cancelar");
            }

            panelBotones.add(boton);
        }

        panelPrincipal.add(panelBotones);

        return panelPrincipal;
    }

    // Aplica los cambios y devuelve true sí y solo sí no hubo ningún error.
    private boolean aplicarCambios() {
        Configuracion nuevaConfig = new Configuracion();

        // Ponemos el tiempo de la partida de modo que no lo perdemos.
        nuevaConfig.setTiempoPartida(configActual.getTiempoPartida());

        for (int i = 0; i < 4; i++) {
            String nuevoValor = camposDeTexto[i].getText();
            int valorNumerico = 0;

            // i == 3 quiere decir que estamos en la imagen de la casilla,
            // que no es un número.
            if (i != 3) {
                // Intentamos transformar el valor en un número:

                try {
                    valorNumerico = Integer.parseInt(nuevoValor);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "¡Debes insertar un número!", "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return false; // Ha habido un error: devolvemos false.
                }
            } else {
                // Miramos si la ruta del fichero existe:

                File archivo = new File(nuevoValor);

                if (!archivo.exists()) {
                    JOptionPane.showMessageDialog(null, "¡Debes insertar una ruta válida!", "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return false; // Ha habido un error: devolvemos false.
                }
            }

            switch (i) {
                case 0 -> nuevaConfig.setPuntuacionCasillasEliminadas(valorNumerico);
                case 1 -> nuevaConfig.setPuntuacionRotarForma(valorNumerico);
                case 2 -> nuevaConfig.setPuntuacionNuevaForma(valorNumerico);
                case 3 -> nuevaConfig.setImagenCasillasFormas(nuevoValor);
            }
        }

        TetrisUIB.setConfiguracion(nuevaConfig);
        TetrisUIB.guardarConfiguracionAFichero();
        return true;
    }
}
