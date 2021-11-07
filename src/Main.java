
/**
 *
 * @author Frank Pizarro
 */

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        ListaES<Proceso> listaProcesos = new ListaES<Proceso>();
        int n;
        System.out.println("ingrese el numero de procesos:");
        n = entrada.nextInt();
        for(int i=1;i<=n;i++)
        {
            Proceso p = new Proceso();
            listaProcesos.insertarFinal(p);
        }
        
        listaProcesos.mostrarLista();
        
        
        
    }
}
