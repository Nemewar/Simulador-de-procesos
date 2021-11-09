
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
            Proceso p = listaProcesos.devolverElementoPorPosicion(i);
            System.out.println("Proceso "+i+":");
            System.out.print("tiempoLlegada: ");
            tiempoLlegada = entrada.nextInt();
            p.setTiempoLlegada(tiempoLlegada);
            System.out.print("tiempoPermanencia: ");
            tiempoPermanencia = entrada.nextInt();
            p.setTiempoPermanencia(tiempoPermanencia);
            System.out.print("prioridad: ");
            prioridad = entrada.nextInt();
            p.setPrioridad(prioridad);
            System.out.println("");
        }
        
        
        
        /*ordenar por tiempo de llegada*/
        listaProcesos.ordenar();
        
        
        
        /**/
        ColaDeEjecucion<Proceso> colaDeEjecucion = new ColaDeEjecucion<>(listaProcesos);
        
        colaDeEjecucion.pasarProcesos();

        colaDeEjecucion.ejecutarCola();
        
        
        System.out.println("");
        System.out.println("Lista final");
        listaProcesos.mostrarLista();
        
        
    }
}
