package practicaFinal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author dima
 */
public class ConfigVentana extends JFrame {

    private boolean modified = false;

    public ConfigVentana() {
        setTitle("Configuracion");
        setSize(700, 300);
        setDefaultCloseOperation(ConfigVentana.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.add(anadirContenidos());
        setVisible(true);
    }

    private JPanel anadirContenidos() {
        Configuracion configuracion = TetrisUIB.getConfiguracion();

        JPanel principal = new JPanel();

        principal.setLayout(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();

        this.setLayout(new BorderLayout());

        this.add(principal, BorderLayout.CENTER);

        bag.gridx = 0;
        bag.gridy = 0;
        JLabel puntuacion = new JLabel("PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO:  [" + configuracion.getPuntuacionCasillasEliminadas() + ']');
        principal.add(puntuacion, bag);

        bag.gridx = 1;
        bag.gridy = 0;
        JTextField puntuacion_in = new JTextField(configuracion.getPuntuacionCasillasEliminadas());
        puntuacion_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (IOException ex) {
                    System.err.println(".actionPerformed()");
                    //Hacer cosas pendientes
                }

            }
        });
        puntuacion_in.setColumns(10);
        principal.add(puntuacion_in, bag);

        bag.gridx = 0;
        bag.gridy = 1;
        JLabel rotar = new JLabel("PUNTUACIÓN ROTAR FORMA: [" + configuracion.getPuntuacionRotarForma() + ']');
        principal.add(rotar, bag);

        bag.gridx = 1;
        bag.gridy = 1;
        JTextField rotar_in = new JTextField(configuracion.getPuntuacionRotarForma());
        rotar_in.setColumns(10);
        principal.add(rotar_in, bag);

        bag.gridx = 0;
        bag.gridy = 2;
        JLabel nueva = new JLabel("PUNTUACIÓN NUEVA FORMA: [" + configuracion.getPuntuacionNuevaForma() + ']');
        principal.add(nueva, bag);

        bag.gridx = 1;
        bag.gridy = 2;
        JTextField nueva_in = new JTextField(configuracion.getPuntuacionNuevaForma());
        nueva_in.setColumns(10);
        principal.add(nueva_in, bag);

        bag.gridx = 0;
        bag.gridy = 3;
        JLabel casillas = new JLabel("IMAGEN CASILLAS FORMAS: [" + configuracion.getImagenCasillasFormas() + ']');
        principal.add(casillas, bag);

        bag.gridx = 1;
        bag.gridy = 3;
        JFileChooser casillas_in = new JFileChooser(configuracion.getImagenCasillasFormas());
        principal.add(casillas_in, bag);

        bag.gridx = 1;
        bag.gridy = 4;
        JButton botonAplicar = new JButton("");
        botonAplicar.setEnabled(modified);
        botonAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfiguracionFicheroEscritura escritura;
                try {
                    escritura = new ConfiguracionFicheroEscritura(TetrisUIB.CAMINO_CONFIG);
                    escritura.escribir(configuracion);
                    escritura.cerrarFichero();
                } catch (IOException ex) {

                }

            }
        });
        JButton botonAceeptar = new JButton("Aceptar");
        botonAceeptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfiguracionFicheroEscritura escritura;
                try {
                    escritura = new ConfiguracionFicheroEscritura(TetrisUIB.CAMINO_CONFIG);
                    escritura.escribir(configuracion);
                    escritura.cerrarFichero();
                } catch (IOException ex) {

                }

            }
        });

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfiguracionFicheroEscritura escritura;

            }
        });
        
        return principal;
    }
    private void cerar(boolean mod){
        if (mod) {
            
        }else{
            this.dispose();
        }
    }
}
