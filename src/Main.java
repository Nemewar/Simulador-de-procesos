
/**
 *
 * @author Frank Pizarro
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) {
      
        int numeroProcesos = Integer.parseInt
                            (JOptionPane.showInputDialog("Ingrese el numero de procesos"));
        ListaES<Proceso> listaProcesos = new ListaES<Proceso>();        
        
        /*creacion de los procesos
        según el numero de procesos indicados
        y con todos sus atributos iniciales en cero*/
        for(int i=1;i<=numeroProcesos;i++)
        {
            Proceso p = new Proceso();
            listaProcesos.insertarFinal(p);
        }
        
        /*creación de la tabla simulador de procesos*/
        VentanaSimulador ventana = new VentanaSimulador(numeroProcesos,listaProcesos);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        

    }
}
