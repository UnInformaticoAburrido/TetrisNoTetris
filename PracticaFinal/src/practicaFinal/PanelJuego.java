package practicaFinal;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        GridBagLayout layoutPanelInferiorTexto = new GridBagLayout();

        layoutPanelInferiorTexto.columnWidths = new int[] {
                1, 2, 1, 1
        };

        panelInferiorTexto.setLayout(layoutPanelInferiorTexto);

        layoutPanelInferiorTexto.location(0, 0);
        JLabel labelTituloJugador = new JLabel("Jugador: ");
        panelInferior.add(labelTituloJugador);

        layoutPanelInferiorTexto.location(0, 1);
        JLabel labelJugador = new JLabel(partida.getNombre());
        panelInferior.add(labelJugador);

        layoutPanelInferiorTexto.location(0, 2);
        JLabel labelTituloPuntacion = new JLabel("Puntuación: ");
        panelInferior.add(labelTituloPuntacion);

        layoutPanelInferiorTexto.location(0, 3);
        panelInferior.add(labelPuntuacion);

        panelInferiorTiempo.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar(0, partida.getTiempo());

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int tiempoTotal = partida.getTiempo();
                int valorActual = progressBar.getValue();
                progressBar.setValue(++valorActual);
                progressBar.setString(tiempoTotal - valorActual + " s");
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
    }

    public void empezarTiempo() {
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
