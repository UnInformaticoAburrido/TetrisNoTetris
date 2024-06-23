package practicaFinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelTablero extends JPanel implements MouseListener, MouseMotionListener {
    // Constantes que definen los márgenes del tablero según la
    // esquina superior izquierda.
    private final int DESFASE_X = 50, DESFASE_Y = 40;

    // Constantes que definen las dimensiones de cada casilla.
    private final int CASILLA_X = 30, CASILLA_Y = 30;

    // Constantes que definen cuantas casillas tiene el tablero.
    private final int TABLERO_X = 20, TABLERO_Y = 20;

    // Constantes que definen cuantas casillas tiene cada pieza.
    private final int PIEZA_X = 3, PIEZA_Y = 3;

    // Constantes que definen la anchura y la altura del tablero.
    private final int ANCHURA = CASILLA_X * TABLERO_X;
    private final int ALTURA = CASILLA_Y * TABLERO_Y;

    // Constantes que definen la anchura y la altura de las piezas.
    private final int PIEZA_ANCHURA = CASILLA_X * PIEZA_X;
    private final int PIEZA_ALTURA = CASILLA_Y * PIEZA_Y;

    // Constantes que definen la posición del sitio de la nueva pieza.
    private final int NUEVA_PIEZA_POS_X = DESFASE_X * 2 + ANCHURA;
    private final int NUEVA_PIEZA_POS_Y = DESFASE_Y * 2 + ALTURA / 2 - PIEZA_ALTURA;

    private Casilla[][] matrizJuego = new Casilla[TABLERO_X][TABLERO_Y];
    private Casilla[][] piezaActual = new Casilla[PIEZA_X][PIEZA_Y];

    private BufferedImage imagenCasilla;

    // Variables que se usan para guardar dónde se encentra la pieza actualmente.
    private int nuevaPiezaPosActualX = NUEVA_PIEZA_POS_X;
    private int nuevaPiezaPosActualY = NUEVA_PIEZA_POS_Y;

    // Variable que indica si se está moviendo una pieza con el ratón.
    private boolean moviendoPieza = false;

    private PanelJuego panelJuego;

    public PanelTablero(PanelJuego panelJuego) {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);

        this.panelJuego = panelJuego;

        // Inicializa la matriz de casillas.
        restablecerTablero();

        // Lee la imagen de las casillas.
        actualizaImagenCasilla();

        // Genera la primera pieza.
        generaPieza();
    }

    /**
     * Lee la nueva imagen de la casilla según la configuración.
     */
    public void actualizaImagenCasilla() {
        // Consigue el camino de la imagen de la casilla.
        String caminoImagen = TetrisUIB.getConfiguracion().getImagenCasillasFormas();
        File imagenFile = new File(caminoImagen);

        // Lee la imagen:
        if (imagenFile.exists()) {
            try {
                imagenCasilla = ImageIO.read(imagenFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "No se ha podido leer la imagen para las casillas. (" + caminoImagen + ")",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se ha encontrado la imagen para las casillas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Genera una nueva pieza aleatoria.
     */
    public void generaPieza() {
        Random random = new Random();

        for (int i = 0; i < PIEZA_X; i++) {
            for (int j = 0; j < PIEZA_Y; j++) {
                Casilla casilla = new Casilla();
                casilla.setOcupada(random.nextBoolean());

                piezaActual[i][j] = casilla;
            }
        }

        restablecerPieza();
    }

    /**
     * Rota la pieza en sentido de las agujas del reloj.
     */
    public void rotarPieza() {
        Casilla[][] resultado = new Casilla[3][3];

        resultado[0][0] = piezaActual[0][2];
        resultado[1][0] = piezaActual[0][1];
        resultado[2][0] = piezaActual[0][0];
        resultado[0][1] = piezaActual[1][2];
        resultado[1][1] = piezaActual[1][1];
        resultado[2][1] = piezaActual[1][0];
        resultado[0][2] = piezaActual[2][2];
        resultado[1][2] = piezaActual[2][1];
        resultado[2][2] = piezaActual[2][0];

        piezaActual = resultado;
        repaint();
    }

    // Restablece el tablero, liberando todas las casillas de este.
    // Se crean nuevas casillas en vez de cambiar su valor para regenerar los
    // colores de estas.
    public void restablecerTablero() {
        for (int i = 0; i < TABLERO_X; i++) {
            for (int j = 0; j < TABLERO_Y; j++) {
                matrizJuego[i][j] = new Casilla();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(TetrisUIB.getColorFondos());
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        graphics2D.setColor(Color.BLACK);

        // Dibuja las líneas verticales.
        for (int i = 0; i <= TABLERO_X; i++) {
            int x = i * CASILLA_X + DESFASE_X;

            graphics2D.drawLine(x, DESFASE_Y, x, ALTURA + DESFASE_Y);
        }

        // Dibuja las líneas horizontales.
        for (int i = 0; i <= TABLERO_Y; i++) {
            int y = i * CASILLA_Y + DESFASE_Y;

            graphics2D.drawLine(DESFASE_X, y, ANCHURA + DESFASE_X, y);
        }

        // Dibuja las casillas.
        for (int i = 0; i < TABLERO_X; i++) {
            for (int j = 0; j < TABLERO_Y; j++) {
                Casilla casilla = matrizJuego[i][j];

                // Ignoramos las casillas que no estén ocupadas.
                if (!casilla.isOcupada()) {
                    continue;
                }

                int x = i * CASILLA_X + DESFASE_X;
                int y = j * CASILLA_Y + DESFASE_Y;

                dibujarCasilla(graphics2D, casilla, x, y);
            }
        }

        graphics2D.setColor(TetrisUIB.getColorTerciario());
        graphics2D.setStroke(new BasicStroke(5));

        // Dibuja la parte donde aparecen las nuevas piezas:
        graphics2D.drawRect(
                NUEVA_PIEZA_POS_X - 3,
                NUEVA_PIEZA_POS_Y - 3,
                PIEZA_ANCHURA + 4,
                PIEZA_ALTURA + 4);

        for (int i = 0; i < PIEZA_X; i++) {
            for (int j = 0; j < PIEZA_Y; j++) {
                Casilla casilla = piezaActual[i][j];

                // Ignoramos las casillas que no estén ocupadas.
                if (!casilla.isOcupada()) {
                    continue;
                }

                int x = i * CASILLA_X + nuevaPiezaPosActualX;
                int y = j * CASILLA_Y + nuevaPiezaPosActualY;

                dibujarCasilla(graphics2D, casilla, x, y);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX(), y = e.getY();

        // Si la pieza se ha soltado fuera del tablero,
        // se devuelve a donde estaba.
        if (!dentroTablero(x, y) || !moviendoPieza) {
            restablecerPieza();
            return;
        }

        int columna = columnaEn(x) - 1, fila = filaEn(y) - 1;

        // Comprueba si hay casillas ocupadas.
        for (int i = 0; i < PIEZA_X; i++) {
            for (int j = 0; j < PIEZA_Y; j++) {
                Casilla casillaPieza = piezaActual[i][j];

                // Ignora las casillas que no están ocupadas en la pieza.
                if (!casillaPieza.isOcupada()) {
                    continue;
                }

                int columnaActual = columna + i;
                int filaActual = fila + j;

                // Comprueba que la casilla esté dentro del tablero:
                if (columnaActual < 0 || columnaActual >= TABLERO_X ||
                        filaActual < 0 || filaActual >= TABLERO_Y) {

                    restablecerPieza();
                    return;
                }

                Casilla casillaTablero = matrizJuego[columnaActual][filaActual];

                if (casillaTablero.isOcupada()) {
                    restablecerPieza();
                    return;
                }
            }
        }

        // Aplica las casillas al tablero.
        for (int i = 0; i < PIEZA_X; i++) {
            for (int j = 0; j < PIEZA_Y; j++) {
                Casilla casilla = piezaActual[i][j];

                // Ignora las casillas que no están ocupadas en la pieza.
                if (!casilla.isOcupada()) {
                    continue;
                }

                matrizJuego[columna + i][fila + j] = casilla;
            }
        }

        int puntosPorCasilla = TetrisUIB.getConfiguracion().getPuntuacionCasillasEliminadas();

        // Comprueba el tablero para puntuar:
        for (int i = 0; i < TABLERO_X; i++) {
            for (int j = 0; j < TABLERO_Y; j++) {
                // Ignora las casillas que no están ocupadas.
                if (!matrizJuego[i][j].isOcupada()) {
                    continue;
                }

                // Si encuentra una casilla ocupada,
                // comprueba las filas y las columnas.

                boolean columnaOcupada = true;

                // Mira si hay alguna casilla no ocupada en la columna.
                for (int k = 0; k < TABLERO_X; k++) {
                    if (!matrizJuego[k][j].isOcupada()) {
                        columnaOcupada = false;
                        break;
                    }
                }

                boolean filaOcupada = true;

                // Mira si hay alguna casilla no ocupada en la fila.
                for (int k = 0; k < TABLERO_Y; k++) {
                    if (!matrizJuego[i][k].isOcupada()) {
                        filaOcupada = false;
                        break;
                    }
                }

                // Elimina la columna si está totalmente ocupada:
                if (columnaOcupada) {
                    for (int k = 0; k < TABLERO_X; k++) {
                        matrizJuego[k][j].setOcupada(false);
                        panelJuego.incrementaPuntuacion(puntosPorCasilla);
                    }
                }

                // Elimina la fila si está totalmente ocupada:
                if (filaOcupada) {
                    for (int k = 0; k < TABLERO_Y; k++) {
                        matrizJuego[i][k].setOcupada(false);
                        panelJuego.incrementaPuntuacion(puntosPorCasilla);
                    }
                }
            }
        }

        // Genera la nueva pieza:
        generaPieza();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX(), y = e.getY();

        if (!dentroPieza(x, y) && !moviendoPieza) {
            return;
        }

        moviendoPieza = true;

        if (dentroTablero(x, y)) {
            int columna = columnaEn(x) - 1, fila = filaEn(y) - 1;

            nuevaPiezaPosActualX = columna * CASILLA_X + DESFASE_X;
            nuevaPiezaPosActualY = fila * CASILLA_Y + DESFASE_Y;
        } else {
            nuevaPiezaPosActualX = x - (PIEZA_X * CASILLA_X) / 2;
            nuevaPiezaPosActualY = y - (PIEZA_Y * CASILLA_Y) / 2;
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // En caso de que el jugador encuentre algun error de dibujado,
        // se intenta solucionar redibujando el panel al clicar.
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void dibujarCasilla(Graphics2D graphics2D, Casilla casilla, int x, int y) {
        graphics2D.setColor(casilla.getColor());
        graphics2D.fillRect(x, y, CASILLA_X, CASILLA_Y);

        if (imagenCasilla != null) {
            graphics2D.drawImage(imagenCasilla, x, y, null);
        }
    }

    // Restablece la pieza a su posición original.
    private void restablecerPieza() {
        moviendoPieza = false;
        nuevaPiezaPosActualX = NUEVA_PIEZA_POS_X;
        nuevaPiezaPosActualY = NUEVA_PIEZA_POS_Y;

        repaint();
    }

    /**
     * Comprueba si la posición dada está dentro del tablero.
     */
    private boolean dentroTablero(int x, int y) {
        int minX = DESFASE_X - 1 * CASILLA_X;
        int minY = DESFASE_Y - 1 * CASILLA_Y;
        int maxX = minX + ANCHURA + 2 * CASILLA_X;
        int maxY = minY + ALTURA + 2 * CASILLA_Y;

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**
     * Comprueba si la posición dada está dentro del sitio de la pieza.
     */
    private boolean dentroPieza(int x, int y) {
        int minX = NUEVA_PIEZA_POS_X;
        int minY = NUEVA_PIEZA_POS_Y;
        int maxX = minX + PIEZA_ANCHURA;
        int maxY = minY + PIEZA_ALTURA;

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**
     * Devuelve la columna correspondiente a la posición x dada.
     */
    private int columnaEn(int x) {
        if ((x - DESFASE_X) < 0) {
            return -1;
        }

        return (x - DESFASE_X) / CASILLA_X;
    }

    /**
     * Devuelve la fila correspondiente a la posición y dada.
     */
    private int filaEn(int y) {
        if ((y - DESFASE_Y) < 0) {
            return -1;
        }

        return (y - DESFASE_Y) / CASILLA_Y;
    }
}
