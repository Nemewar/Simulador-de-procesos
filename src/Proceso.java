
/**
 *
 * @author Frank Pizarro
 */
public class Proceso implements Comparable<Proceso>{
    
    private static int contadorProcesos;
    private final String PID;
    private int tiempoLlegada;
    private int duracion;
    private int duracionActual;
    private int prioridad;
    private int tiempoSalida;
    private EstadoProceso estado;
    
    
    static
    {
        contadorProcesos = 0;
    }
    
    {
        tiempoLlegada = 0;
        duracion = 0;
        duracionActual = 0;
        prioridad = 0;
        tiempoSalida = 0;
        estado = EstadoProceso.INICIO;
    }
    
    public Proceso()
    {
        contadorProcesos++;
        StringBuilder pid = new StringBuilder();
        pid.append("P");
        pid.append(contadorProcesos);
        this.PID = pid.toString();
    }

    
    /*setters*/
    
    
    public void setDuracionActual(int duracionActual) {
        this.duracionActual = duracionActual;
    }

    public void setEstado(EstadoProceso estado) {
        this.estado = estado;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setTiempoSalida(int tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
    }
    
    
    /*getters*/
    
  
    public int getDuracionActual() {
        return duracionActual;
    }

    public EstadoProceso getEstado() {
        return estado;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }
    
    public int getTiempoSalida()
    {
        return tiempoSalida;
    }
    
    public String getPID()
    {
        return this.PID;
    }
    
    public static int getContadorProcesos()
    {
        return contadorProcesos;
    }
    
    
    @Override
    public int compareTo(Proceso p) {
        
        if(this.getTiempoLlegada()>p.getTiempoLlegada())
        {
            return 1;
        }
        else
        {
            if(this.getTiempoLlegada()==p.getTiempoLlegada())
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }
    
    
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj==null)
        {
            return false;
        }
        else
        {
            if(obj instanceof Proceso)
            {
                Proceso p = (Proceso)obj;
                if(this.PID.equals(p.PID))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
            
    }
    
    
    
    public String toString()
    {
        return "PID: "+this.PID+
                "\ntiempoSalida: "+tiempoSalida+"\n";
    }



    

    
}
