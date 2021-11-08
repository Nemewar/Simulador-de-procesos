
/**
 *
 * @author Frank Pizarro
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        ListaES<Proceso> listaProcesos = new ListaES<Proceso>();
        ColaDeEjecucion<Proceso> colaDeEjecucion = new ColaDeEjecucion<>(listaProcesos);
        int n,tiempoLlegada,tiempoPermanencia,prioridad;
        
        /*creacion de los procesos*/
        System.out.println("ingrese el numero de procesos:");
        n = entrada.nextInt();
        for(int i=1;i<=n;i++)
        {
            Proceso p = new Proceso();
            listaProcesos.insertarFinal(p);
        }
        
        
        /*asignar valores a los atributos de los procesos*/
        for(int i=1;i<=Proceso.getContadorProcesos();i++)
        {
            System.out.println("Proceso "+i+":");
            System.out.print("tiempoLlegada: ");
            tiempoLlegada = entrada.nextInt();
            System.out.print("tiempoPermanencia: ");
            tiempoPermanencia = entrada.nextInt();
            System.out.print("prioridad: ");
            prioridad = entrada.nextInt();
        }
        
        
        
        /*ordenar por tiempo de llegada*/
        listaProcesos.ordenar();
        
        for(Proceso p: listaProcesos)
        {
            System.out.println(p);
        }
        
        listaProcesos.mostrarLista();
        
    }
}
