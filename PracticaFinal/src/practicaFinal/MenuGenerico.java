/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practicaFinal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author dimitry
 */
public abstract class MenuGenerico {
        public void partida(JPanel panel){
            //Esta funcion deve iniciar la partida
        }
        public boolean configuracion(){
            //Esta funcion deve  habrir la configuracion
            JFrame ventana = new JFrame("Configuracion tetris UIB");
            ventana.setSize(500, 400);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Antes de mostrar la ventana al usuario cargamos las confis actuales
            File config = new File("configfile.dat");
            try {
                BufferedReader configBufer = new BufferedReader(new FileReader(config));
            } catch (FileNotFoundException ex) {
                
            }
            
            ventana.setVisible(true);
            return true;
        }
        public void historial(){
            //Esta funcion deve habrir el historial
            System.out.println("Funcionalidad no implementada");
        }
        public void informacion(){
            //Esta funcion deve mostrar la informacion
            System.out.println("Funcionalidad no implementada");
        }
        public void salir(){
            //Esta funcion deve finalizar el progrma
            System.out.println("Funcionalidad no implementada");
        }
}
