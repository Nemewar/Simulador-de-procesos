
/**
 *
 * @author Frank Pizarro
 */
public class Proceso {
    
    private static int contadorProcesos;
    private final String PID;
    private int tiempoLlegada;
    private int tiempoPermanencia;
    private int prioridad;
    private int tiempoSalida;
    
    
    static
    {
        contadorProcesos = 0;
    }
    
    {
        tiempoLlegada = 0;
        tiempoPermanencia = 0;
        prioridad = 0;
    }
    
    public Proceso()
    {
        contadorProcesos++;
        StringBuilder pid = new StringBuilder();
        pid.append("P");
        pid.append(contadorProcesos);
        this.PID = pid.toString();
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public void setTiempoPermanencia(int tiempoPermanencia) {
        this.tiempoPermanencia = tiempoPermanencia;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setTiempoSalida(int tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
    }
    

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getTiempoPermanencia() {
        return tiempoPermanencia;
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
        return "PID: "+this.PID;
    }
}
