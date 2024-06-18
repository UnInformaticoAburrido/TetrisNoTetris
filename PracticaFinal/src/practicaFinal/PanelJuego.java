package practicaFinal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class PanelJuego extends JPanel {
    private Partida partida;
    private Timer timer;
    private JLabel labelPuntuacion = new JLabel();
    private PanelTablero panelTablero = new PanelTablero();

    public PanelJuego(Partida partida) {
        this.partida = partida;

        // ------------------------------
        // | Panel inferior
        // ------------------------------

        JPanel panelInferior = new JPanel();

        panelInferior.setLayout(new GridLayout(2, 1));

        JPanel panelInferiorTexto = new JPanel();
        JPanel panelInferiorTiempo = new JPanel();

        GridLayout layoutPanelInferiorTexto = new GridLayout(1, 4);
        panelInferiorTexto.setLayout(layoutPanelInferiorTexto);

        Font fuenteEtiquetasTitulos = new Font("SansSerif", Font.BOLD, 20);
        Font fuenteEtiquetas = new Font("Monospaced", Font.PLAIN, 20);

        JLabel labelTituloJugador = new JLabel("Jugador: ");
        labelTituloJugador.setFont(fuenteEtiquetasTitulos);
        panelInferiorTexto.add(labelTituloJugador);

        JLabel labelJugador = new JLabel(partida.getNombre());
        labelJugador.setFont(fuenteEtiquetas);
        panelInferiorTexto.add(labelJugador);

        JLabel labelTituloPuntacion = new JLabel("Puntuación: ");
        labelTituloPuntacion.setFont(fuenteEtiquetasTitulos);
        panelInferiorTexto.add(labelTituloPuntacion);

        labelPuntuacion.setFont(fuenteEtiquetas);
        panelInferiorTexto.add(labelPuntuacion);

        panelInferiorTiempo.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar(0, partida.getTiempo());
        progressBar.setString(partida.getTiempo() + " s");
        progressBar.setFont(fuenteEtiquetas);
        progressBar.setStringPainted(true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int tiempoTotal = partida.getTiempo();
                int valorActual = progressBar.getValue();
                progressBar.setValue(++valorActual);
                progressBar.setString(tiempoTotal - valorActual + " s");

                if (valorActual == tiempoTotal) {
                    HistorialFicheroEscritura escritura = null;

                    try {
                        escritura = new HistorialFicheroEscritura(TetrisUIB.getHistoria());
                        escritura.escribir(partida);
                    } catch (IOException ex) {

                    } finally {
                        if (escritura != null) {
                            try {
                                escritura.cerrarFichero();
                            } catch (IOException e) {

                            }
                        }
                    }

                    timer.stop();
                }
            }
        });

        panelInferiorTiempo.add(progressBar, BorderLayout.CENTER);

        panelInferior.add(panelInferiorTexto);
        panelInferior.add(panelInferiorTiempo);

        // ------------------------------
        // | Panel lateral
        // ------------------------------

        JPanel panelLateral = new JPanel();

        panelLateral.setLayout(new GridLayout(2, 1));

        JButton botonGirarPieza = new JButton();

        botonGirarPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int penalizacion = TetrisUIB.getConfiguracion().getPuntuacionRotarForma();
                partida.incrementaPuntuacion(-penalizacion);

                panelTablero.rotarPieza();
            }
        });

        JButton botonNuevaPieza = new JButton();

        botonNuevaPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int penalizacion = TetrisUIB.getConfiguracion().getPuntuacionNuevaForma();
                partida.incrementaPuntuacion(-penalizacion);

                panelTablero.generaPieza();
            }
        });

        panelLateral.add(botonGirarPieza);
        panelLateral.add(botonNuevaPieza);

        // ------------------------------
        // | Ensamblar el panel principal
        // ------------------------------

        this.setLayout(new BorderLayout());
        this.add(panelTablero, BorderLayout.CENTER);
        this.add(panelLateral, BorderLayout.EAST);
        this.add(panelInferior, BorderLayout.SOUTH);

        timer.start();
    }

    public int getPuntuacion() {
        return partida.getPuntuacion();
    }

    public void setPuntuacion(int puntuacion) {
        partida.setPuntuacion(puntuacion);
        labelPuntuacion.setText(puntuacion + " puntos");
    }
}
