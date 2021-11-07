/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;

/**
 *
 * @author Frank Pizarro
 */
public class ListaES<T> implements Iterable<T>{
    
    class Nodo<T>
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
    
    public ListaES()
    {
        this.padre = null;
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
    
    private class MiIterator<T> implements Iterator<T>
    {
        Nodo<T> nodoActual = (Nodo<T>) padre;
        Nodo<T> nodoAnterior = null;
        
        @Override
        public boolean hasNext() {
            return nodoActual!=null;
        }

        @Override
        public T next() {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.sgte;
            return nodoAnterior.elemento;
        }
        
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
    
    public void insertarInicio(T elemento)
    {
        Nodo<T> nodo = crearNodo(elemento);
        if(padre==null)
        {
            padre = nodo;
        }
        else
        {
            nodo.sgte = padre;
            padre = nodo;
        }
    }
    
    public void insertarFinal(T elemento)
    {
        Nodo<T> nodo = crearNodo(elemento);
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
    
    public void insertarPorPosicion(int pos,T elemento)
    {
        if(pos<=0 || pos>tamaño())
        {
            throw new IndexOutOfBoundsException("pos fuera de rango");
        }
        else
        {
            if(pos==1)
            {
                insertarInicio(elemento);
            }
            else
            {
                /*
                posAux y nodoSiguiente, posAux indica la posicion de nodoSiguiente.
                */
                Nodo<T> nodo = crearNodo(elemento);
                Nodo<T> nodoSiguiente = padre;
                Nodo<T> nodoAnterior = null;
                int posAux = 1;//1 porque nodoSiguiente está en el primer elemento(padre)
                while(nodoSiguiente!=null)
                {
                    nodoAnterior = nodoSiguiente;
                    nodoSiguiente = nodoSiguiente.sgte;
                    posAux++;
                    if(posAux==pos)
                    {
                        nodoAnterior.sgte = nodo;
                        nodo.sgte = nodoSiguiente;
                        break;
                    }
                }
                
            }
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
            throw new IndexOutOfBoundsException("pos fuera de rango o lista vacia");
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
    
    /*
    public int[] devolverPosicionesDeElemento(T elemento)
    {
        
    }
    */
    
    /*elimina el elemento la primera vez q lo encuentra y termina el bucle*/
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
            System.out.println("elemento no existe o lista vacia");
        }
    }
    
    /*elimina todas las ocurrencias el elemento en la lista*/
    public void eliminarElementoConRepeticion(T elemento)
    {
        if(existeElemento(elemento))
        {
            while(existeElemento(elemento))
            {
                eliminarElementoSinRepeticion(elemento);
            }
        }
        else
        {
            System.out.println("elemento no existe o lista vacia");
        }
    }
    
    public void borrarTodo()
    {
        padre = null;
    }
    
    public void mostrarLista()
    {
        if(padre==null)
        {
            System.out.println("lista vacia");
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
