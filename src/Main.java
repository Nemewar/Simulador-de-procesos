
/**
 *
 * @author Frank Pizarro
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) {
        
        /*SOLO FUNCIONA EL ALGORITMO PARA 
        TIEMPOS DE LLEGADAS DIFERENTES ENTRE LOS PROCESOS*/
        
        
//        Scanner entrada = new Scanner(System.in);
//        ListaES<Proceso> listaProcesos = new ListaES<Proceso>();
//        int n,tiempoLlegada,duracion,prioridad;
//        
//        /*creacion de los procesos*/
//        System.out.println("ingrese el numero de procesos:");
//        n = entrada.nextInt();
//        for(int i=1;i<=n;i++)
//        {
//            Proceso p = new Proceso();
//            listaProcesos.insertarFinal(p);
//        }
//        
//        
//        /*asignar valores a los atributos de los procesos*/
//        for(int i=1;i<=Proceso.getContadorProcesos();i++)
//        {
//            Proceso p = listaProcesos.devolverElementoPorPosicion(i);
//            System.out.println("Proceso "+i+":");
//            System.out.print("tiempoLlegada: ");
//            tiempoLlegada = entrada.nextInt();
//            p.setTiempoLlegada(tiempoLlegada);
//            System.out.print("duracion: ");
//            duracion = entrada.nextInt();
//            p.setDuracion(duracion);
//            System.out.print("prioridad: ");
//            prioridad = entrada.nextInt();
//            p.setPrioridad(prioridad);
//            System.out.println("");
//        }
//        
//        
//        
//        /*ordenar por tiempo de llegada*/
//        listaProcesos.ordenar();
//        
//        
//        
//        /*cola de ejecucion*/
//        ColaDeEjecucion<Proceso> colaDeEjecucion = new ColaDeEjecucion<>(listaProcesos);
//        
//        colaDeEjecucion.pasarProcesos();//pasar procesos de la lista a la cola
//        
//        colaDeEjecucion.ejecutarCola();//calcular los tiempos de salida
//        
//        
//        
//        /*mostrar la lista final con los tiempos de salida*/
//        System.out.println("");
//        System.out.println("Lista final");
//        listaProcesos.mostrarLista();
        
     
        
        
        
        
        
        
        
        
        int numeroProcesos = Integer.parseInt
                            (JOptionPane.showInputDialog("Ingrese el numero de procesos"));
        ListaES<Proceso> listaProcesos = new ListaES<Proceso>();        
        
        /*creacion de los procesos*/
        for(int i=1;i<=numeroProcesos;i++)
        {
            Proceso p = new Proceso();
            listaProcesos.insertarFinal(p);
        }
        
        VentanaSimulador ventana = new VentanaSimulador(numeroProcesos,listaProcesos);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        
        
    }
}
