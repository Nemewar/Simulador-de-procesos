
import java.util.Iterator;


/**
 *
 * @author Frank Pizarro
 */
public class ColaDeEjecucion<T extends Proceso> implements Iterable<T>{
    
    class Nodo<T extends Proceso>
    {
        T elemento;
        Nodo<T> sgte;
        
        public Nodo(T elemento)
        {
            this.elemento = elemento;
            this.sgte = null;
        }
    }
    
    Nodo<T> padre;
    private static int contador;
    private static Proceso ProcesoActual;
    private ListaES listaProcesos;
    
    public ColaDeEjecucion(ListaES<Proceso> listaProcesos)
    {
        this.padre = null;
        contador = 0;
        ProcesoActual = null;
        this.listaProcesos = listaProcesos;
        pasarProcesos();
    }
    
    private <T>void pasarProcesos()
    {
        while(this.tamaño()!=listaProcesos.tamaño())
        {
            ListaES.Nodo nodoLista = listaProcesos.padre;
            
        }
    }
    
    @Override
    public Iterator<T> iterator()
    {
        Iterator<T> it = new Iterator<T>()
        {
            Nodo<T> nodoActual = padre;
            Nodo<T> nodoAnterior = null;
            
            /*retorna true si nodoActual no es igual a null*/
            @Override
            public boolean hasNext() {
                return nodoActual!=null;
            }

            /*retorna el elemento del nodoactual,
            hace que nodoAnterior sea igual a nodoActual y
            hace que nodoActual pase al siguiente
            */
            @Override
            public T next() {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.sgte;
                return nodoAnterior.elemento;
            }
            
        };
        
        return it;
        
        /*MiIterator<T> it2 = new MiIterator<T>();
        return it;*/
    }
    
    
    public Nodo<T> crearNodo(T elemento)
    {
        Nodo<T> nodo = new Nodo<T>(elemento);
        return nodo;
    }
    
    public int tamaño()
    {
        int tamaño = 0;
        if(padre!=null)
        {
            Nodo nodoAux = padre;
            while(nodoAux!=null)
            {
                tamaño++;
                nodoAux = nodoAux.sgte;
            }
        }
        return tamaño;
    }
    
    public void insertarFinal(T elemento)
    {
        Nodo<T> nodo = crearNodo(elemento);
        nodo.elemento.setEstado(EstadoProceso.LISTO);
        if(padre==null)
        {
            padre = nodo;
        }
        else
        {
            /*
            aux.sgte!=null recorre n-1 veces
            aux!=null recorre n veces
            donde n es la cantidad de elementos
            */
            Nodo<T> nodoAux = padre;
            while(nodoAux.sgte!=null)
            {
                nodoAux = nodoAux.sgte;
            }
            nodoAux.sgte = nodo;
        }
    }
    
    public boolean existeElemento(T elemento)
    {
        boolean resultado = false;
        if(padre==null)
        {
            resultado = false;
        }
        else
        {
            Nodo<T> nodoAux = padre;
            while(nodoAux!=null)
            {
                if(nodoAux.elemento.equals(elemento))
                {
                    resultado = true;
                    break;
                }
                nodoAux = nodoAux.sgte;
            }
        }
        return resultado;
    }
    
    public T devolverElementoPorPosicion(int pos)
    {
        T elemento = null;
        if(pos<=0 || pos>tamaño())
        {
            throw new IndexOutOfBoundsException("pos fuera de rango o cola vacia");
        }
        else
        {
            if(pos==1)
            {
                elemento = padre.elemento;
            }
            else
            {
                /*
                posAux=1 porque nodoAux comienza con el elemento 1(padre)
                */
                Nodo<T> nodoAux = padre;
                int posAux = 1;
                while(nodoAux!=null)
                {
                    posAux++;
                    nodoAux = nodoAux.sgte;
                    if(posAux==pos)
                    {
                        elemento = nodoAux.elemento;
                        break;
                    }
                }
            }
        }
        return elemento;
    }
    
    public void eliminarElementoSinRepeticion(T elemento)
    {
        if(existeElemento(elemento))
        {
            if(padre.elemento.equals(elemento))
            {
                padre = padre.sgte;
            }
            else
            {
                Nodo<T> nodoActual = padre;
                Nodo<T> nodoAnterior = null;
                while(nodoActual!=null)
                {
                    if(nodoActual.elemento.equals(elemento))
                    {
                        nodoAnterior.sgte = nodoActual.sgte;
                        break;
                    }
                    nodoAnterior = nodoActual;
                    nodoActual = nodoActual.sgte;
                }
            }
        }
        else
        {
            System.out.println("elemento no existe o cola vacia");
        }
    }
    
    public void mostrarCola()
    {
        if(padre==null)
        {
            System.out.println("cola vacia");
        }
        else
        {
            Nodo<T> nodoAux = padre;
            int i = 0;
            while(nodoAux!=null)
            {
                i++;
                System.out.println("elemento "+i+":");
                System.out.println(nodoAux.elemento);
                nodoAux = nodoAux.sgte;
            }
        }
    }
}
