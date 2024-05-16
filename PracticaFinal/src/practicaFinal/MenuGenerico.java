/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practicaFinal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            //ConfigVentana();
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
