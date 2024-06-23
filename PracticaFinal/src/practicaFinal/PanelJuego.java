package practicaFinal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class PanelJuego extends JPanel {
    private Partida partida;
    private Timer timer;
    private JProgressBar progressBar = new JProgressBar();
    private JLabel labelJugador = new JLabel();
    private JLabel labelPuntuacion = new JLabel();
    private PanelTablero panelTablero = new PanelTablero(this);
    private boolean partidaEnCurso = false;

    public PanelJuego(Partida partida) {
        this.partida = partida;

        // ------------------------------
        // | Panel inferior
        // ------------------------------

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(TetrisUIB.getColorPrincipal());

        panelInferior.setLayout(new GridLayout(2, 1));

        JPanel panelInferiorTexto = new JPanel();
        JPanel panelInferiorTiempo = new JPanel();

        panelInferiorTexto.setLayout(new GridLayout(1, 4));
        panelInferiorTexto.setOpaque(false);

        Font fuenteEtiquetasTitulos = new Font("SansSerif", Font.BOLD, 20);
        Font fuenteEtiquetas = new Font("Monospaced", Font.PLAIN, 20);

        JLabel labelTituloJugador = new JLabel("Jugador: ");
        labelTituloJugador.setFont(fuenteEtiquetasTitulos);
        panelInferiorTexto.add(labelTituloJugador);

        labelJugador.setFont(fuenteEtiquetas);
        panelInferiorTexto.add(labelJugador);

        JLabel labelTituloPuntacion = new JLabel("Puntuación: ");
        labelTituloPuntacion.setFont(fuenteEtiquetasTitulos);
        panelInferiorTexto.add(labelTituloPuntacion);

        labelPuntuacion.setFont(fuenteEtiquetas);
        panelInferiorTexto.add(labelPuntuacion);

        panelInferiorTiempo.setLayout(new BorderLayout());
        panelInferiorTiempo.setOpaque(false);

        progressBar.setString(partida.getTiempo() + " s");
        progressBar.setFont(fuenteEtiquetas);
        progressBar.setStringPainted(true);
        progressBar.setOpaque(false);
        progressBar.setForeground(TetrisUIB.getColorTerciario());

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int tiempoTotal = partida.getTiempo();
                int valorActual = progressBar.getValue();
                progressBar.setValue(++valorActual);
                progressBar.setString(tiempoTotal - valorActual + " s");

                if (valorActual == tiempoTotal) {
                    PartidaFicheroInOut fichero = null;

                    try {
                        fichero = new PartidaFicheroInOut(TetrisUIB.getHistoria());
                        fichero.irAlFinal();
                        fichero.escritura(partida);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog((Component) event.getSource(),
                                "No se ha podido escribir la partida al historial.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        if (fichero != null) {
                            try {
                                fichero.cerrarFichero();
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog((Component) event.getSource(),
                                        "No se ha podido cerrar el fichero del historial.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                    finalizaPartida();
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
        botonGirarPieza.setIcon(new ImageIcon("img/rotate.png"));
        botonGirarPieza.setBackground(TetrisUIB.getColorSecundario());

        botonGirarPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int penalizacion = TetrisUIB.getConfiguracion().getPuntuacionRotarForma();
                partida.incrementaPuntuacion(penalizacion);
                actualizaPuntuacion();

                panelTablero.rotarPieza();
            }
        });

        JButton botonNuevaPieza = new JButton();
        botonNuevaPieza.setIcon(new ImageIcon("img/newpiece.png"));
        botonNuevaPieza.setBackground(TetrisUIB.getColorSecundario());

        botonNuevaPieza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int penalizacion = TetrisUIB.getConfiguracion().getPuntuacionNuevaForma();
                partida.incrementaPuntuacion(penalizacion);
                actualizaPuntuacion();

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

    public void empezarPartida(String nombre) {
        panelTablero.restablecerTablero();
        panelTablero.actualizaImagenCasilla();
        panelTablero.generaPieza();

        int tiempo = TetrisUIB.getConfiguracion().getTiempoPartida();

        partida.setNombre(nombre);
        partida.setTiempo(tiempo);
        partida.setPuntuacion(0);

        progressBar.setValue(0);
        progressBar.setMaximum(tiempo);
        labelJugador.setText(nombre);

        actualizaPuntuacion();

        timer.start();
        partidaEnCurso = true;
    }

    public boolean isPartidaEnCurso() {
        return partidaEnCurso;
    }

    public void incrementaPuntuacion(int valor) {
        partida.incrementaPuntuacion(valor);
        actualizaPuntuacion();
    }

    private void actualizaPuntuacion() {
        int puntuacion = partida.getPuntuacion();
        labelPuntuacion.setText(puntuacion + " puntos");
    }

    private void finalizaPartida() {
        timer.stop();

        JOptionPane.showMessageDialog(this, "¡Se ha terminado el tiempo!\n"
                + "Has conseguido un total de " + partida.getPuntuacion() + " puntos.", "Final de la partida",
                JOptionPane.INFORMATION_MESSAGE);

        TetrisUIB.getVentana().cambiarPanel("LogoPanel");
        partidaEnCurso = false;
    }
}
