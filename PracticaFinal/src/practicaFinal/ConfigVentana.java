package practicaFinal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author dima
 */
public class ConfigVentana extends JFrame {

    private Configuracion configuracion;
    private String lastPath="";

    public ConfigVentana() {
        this.configuracion = TetrisUIB.getConfiguracion();
        setTitle("Configuracion");
        setSize(900, 400);
        setDefaultCloseOperation(ConfigVentana.DISPOSE_ON_CLOSE);
        add(GPP());
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //Generar Panel Principal
    private JPanel GPP(){
        JPanel principal = new JPanel();
        GridLayout grid = new GridLayout(6, 8);
        principal.setLayout(grid);
        
        //PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO:
        JLabel pcfe = new JLabel("PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO: "+configuracion.getPuntuacionCasillasEliminadas());
        JTextField pcfeIn = new JTextField();
        pcfeIn.setColumns(10);
        
        //Insertamos los campos
        principal.add(pcfe);
        principal.add(pcfeIn);
        
        //PUNTUACIÓN ROTAR FORMA:
        JLabel prf = new JLabel("PUNTUACIÓN ROTAR FORMA: "+configuracion.getPuntuacionRotarForma());
        JTextField prfIn = new JTextField();
        prfIn.setColumns(10);
        
        //Insertamos los campos
        principal.add(prf);
        principal.add(prfIn);
        
        //PUNTUACIÓN NUEVA FORMA:
        JLabel pnf = new JLabel("PUNTUACIÓN NUEVA FORMA: "+configuracion.getPuntuacionNuevaForma());
        JTextField pnfIn=new JTextField();
        pnfIn.setColumns(10);
        
        //Insertamos los campos
        principal.add(pnf);
        principal.add(pnfIn);
        
        //IMAGEN CASILLAS FORMAS: 
        
        //Generamos un panel para almacenar dos metodos de entrada del archivo en una unica linea.
        JPanel icfPanel = new JPanel();
        JLabel icf = new JLabel("IMAGEN CASILLAS FORMAS ["+configuracion.getImagenCasillasFormas()+"]");
        JTextField icfIn = new JTextField();
        icfIn.setColumns(20);
        JButton icfButon = new JButton("Buscar Archivo");
        icfPanel.add(icf);
        icfPanel.add(icfIn);
        icfPanel.add(icfButon);
        principal.add(icfPanel);
        
        //Generamos el menu
        JPanel botones = new JPanel(new GridLayout(1,3));
        JButton botonAplicar = new JButton("Aplicar cambios");
        //Funcionalidad
        botonAplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean modified=false;
                if (!pcfeIn.getText().isEmpty()) {
                    configuracion.setPuntuacionCasillasEliminadas(Integer.parseInt(pcfeIn.getText()));
                    modified=true;
                    pcfe.setText("PUNTUACIÓN CASILLAS FORMAS ELIMINADAS DEL TABLERO: "+configuracion.getPuntuacionCasillasEliminadas());
                }
                if (!prfIn.getText().isEmpty()) {
                    configuracion.setPuntuacionRotarForma(Integer.parseInt(prfIn.getText()));
                    modified=true;
                    prf.setText("PUNTUACIÓN ROTAR FORMA: "+configuracion.getPuntuacionRotarForma());
                }
                if(!pnfIn.getText().isEmpty()){
                    configuracion.setPuntuacionNuevaForma(Integer.parseInt(pnfIn.getText()));
                    modified=true;
                    pnf.setText("PUNTUACIÓN NUEVA FORMA: "+configuracion.getPuntuacionNuevaForma());
                }
                if (!(lastPath.isEmpty())) {
                    configuracion.setImagenCasillasFormas(lastPath);
                    modified=true;
                    icf.setText("IMAGEN CASILLAS FORMAS ["+configuracion.getImagenCasillasFormas()+"]");
                }
                try {
                    if (modified) {
                        ConfiguracionFicheroEscritura escritura = new ConfiguracionFicheroEscritura(TetrisUIB.CAMINO_CONFIG);
                        escritura.escribir(configuracion);
                        escritura.cerrarFichero();
                    }
                } catch (IOException ex) {
                    System.err.println("No se ha podido encontrar la ruta");
                }finally{
                    
                }
            }
            
        });
        //Aplica los cambios y cierra el menu
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean modified=false;
                if (!pcfeIn.getText().isEmpty()) {
                    configuracion.setPuntuacionCasillasEliminadas(Integer.parseInt(pcfeIn.getText()));
                    modified=true;
                }
                if (!prfIn.getText().isEmpty()) {
                    configuracion.setPuntuacionRotarForma(Integer.parseInt(prfIn.getText()));
                    modified=true;
                }
                if(!pnfIn.getText().isEmpty()){
                    configuracion.setPuntuacionNuevaForma(Integer.parseInt(pnfIn.getText()));
                    modified=true;
                }
                if (!(lastPath.isEmpty())) {
                    configuracion.setImagenCasillasFormas(lastPath);
                    modified=true;
                }
                try {
                    if (modified) {
                        ConfiguracionFicheroEscritura escritura = new ConfiguracionFicheroEscritura(TetrisUIB.CAMINO_CONFIG);
                        escritura.escribir(configuracion);
                        escritura.cerrarFichero();
                    }
                } catch (IOException ex) {
                    System.err.println("No se ha podido encontrar la ruta");
                }finally{
                    dispose();
                }
            }
        });
        //
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
        botones.add(botonAplicar);
        botones.add(botonAceptar);
        botones.add(botonCancelar);
        
        principal.add(botones);
        
        return principal;
    }
}
