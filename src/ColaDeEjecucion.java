
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
    private int contador;
    private Proceso procesoActual;
    private ListaES listaProcesos;
    private int pos;//posicion del siguiente proceso a ejecutarse
    
    public ColaDeEjecucion(ListaES<Proceso> listaProcesos)
    {
        this.padre = null;
        contador = 0;
        pos = 0;
        procesoActual = null;
        this.listaProcesos = listaProcesos;
    }
    
    public void pasarProcesos()
    {
        ListaES.Nodo nodoLista = listaProcesos.padre;
        insertarFinal((T)nodoLista.elemento);
        pos++;
        while(this.tamaño()!=listaProcesos.tamaño())
        {
            if(this.tamaño()==1)
            {
                procesoActual = padre.elemento;
                while(nodoLista.sgte.elemento.getTiempoLlegada()>=contador)
                {
                    contador = contador + procesoActual.getPrioridad();
                    procesoActual.setDuracionActual
                    (procesoActual.getDuracionActual()+procesoActual.getPrioridad());
                    
                    if(contador>=nodoLista.sgte.elemento.getTiempoLlegada())
                    {
                        nodoLista = nodoLista.sgte;
                        insertarFinal((T)nodoLista.elemento);
                        pos++;
                        break;
                    }
                }
            }
            else
            {
                procesoActual = devolverElementoPorPosicion(pos);
                while(nodoLista.sgte.elemento.getTiempoLlegada()>=contador)
                {
                    contador = contador + procesoActual.getPrioridad();
                    procesoActual.setDuracionActual
                    (procesoActual.getDuracionActual()+procesoActual.getPrioridad());
                    
                    
                    if(procesoActual.equals(devolverElementoPorPosicion(tamaño())))
                    {
                        pos=1;
                    }
                    else
                    {
                        pos++;
                    }
                    procesoActual = devolverElementoPorPosicion(pos);
                    
                    
                    if(contador>=nodoLista.sgte.elemento.getTiempoLlegada())
                    {
                        nodoLista = nodoLista.sgte;
                        insertarFinal((T)nodoLista.elemento);
                    }
                    
                    
                    if(tamaño()==listaProcesos.tamaño())
                    {
                        break;
                    }
                }
            }
            
        }
    }
    
    public void ejecutarCola()
    {
        while(this.tamaño()!=0)
        {
            contador = contador + procesoActual.getPrioridad();
            procesoActual.setDuracionActual
                    (procesoActual.getDuracionActual()+procesoActual.getPrioridad());
            
            if(procesoActual.getTiempoPermanencia()==procesoActual.getDuracionActual())
            {
               
                if(this.tamaño()==1)
                {
                    procesoActual.setTiempoSalida(contador);
                    eliminarElementoSinRepeticion((T)procesoActual);
                    listaProcesos.devolverElementoPorProcesoActual((T)procesoActual).setTiempoSalida(contador);
                    pos = 0;
                    procesoActual = null;
                    break;
                    
                }
                else
                {
                    eliminarElementoSinRepeticion((T)procesoActual);
                    if(devolverPosicion((T)procesoActual)==tamaño())
                    {
                        pos = pos - 1;
                        procesoActual.setTiempoSalida(contador);
                        listaProcesos.devolverElementoPorProcesoActual((T)procesoActual).setTiempoSalida(contador);
                        procesoActual = devolverElementoPorPosicion(pos);
                        System.out.println(pos);
                        System.out.println("tamaño"+this.tamaño());
                    }
                    else
                    {
                        procesoActual.setTiempoSalida(contador);
                        listaProcesos.devolverElementoPorProcesoActual((T)procesoActual).setTiempoSalida(contador);
                        procesoActual = devolverElementoPorPosicion(pos);
                        System.out.println(pos);
                        System.out.println("tamaño"+this.tamaño());
                    }
                    continue;
                }
            }
            
            if(procesoActual.equals(devolverElementoPorPosicion(tamaño())))
            {
                pos=1;
            }
            else
            {
                pos++;
            }
            procesoActual = devolverElementoPorPosicion(pos);
            
        }
    }
    
    
    public Proceso getProcesoActual()
    {
        return this.procesoActual;
    }
    
    public int getContador()
    {
        return this.contador;
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
    
    public int devolverPosicion(T elemento)
    {
        int i = 0;
        if(padre==null)
        {
            i=-1;
        }
        else
        {
            
            if(existeElemento(elemento))
            {
                Nodo<T> aux = padre;
                while(aux!=null && existeElemento(elemento))
                {
                    i++;
                    if(aux.elemento.equals(elemento))
                    {
                        System.out.println("nodo "+(i)+": "+aux.elemento);
                        break;
                    }
                    aux = aux.sgte;
                }
            }
            else
            {
                i = -2;
            }
        }
        return i;
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
