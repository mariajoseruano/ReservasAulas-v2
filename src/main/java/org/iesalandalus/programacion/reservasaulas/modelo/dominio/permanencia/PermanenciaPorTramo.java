
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class PermanenciaPorTramo extends Permanencia{
    
    private static final int PUNTOS=10;
    private Tramo tramo;
    
    
    
 public PermanenciaPorTramo(LocalDate dia, Tramo tramo){   
     
     super(dia);
     setTramo(tramo);
 }
    
  public PermanenciaPorTramo(String dia, Tramo tramo){   
     
     super(dia);
     setTramo(tramo);
 }
    
  public PermanenciaPorTramo( PermanenciaPorTramo permanenciaPorTramo)
        {
	    super();   
            if (permanenciaPorTramo == null) {
		throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
            }
		setDia(permanenciaPorTramo.dia);
		setTramo(permanenciaPorTramo.tramo);
		
        }
    
 public Tramo getTramo()
        {
            return this.tramo;
            
        }            
 private void setTramo(Tramo tramo)
        {
        
                if (tramo == null) {
			throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
		}
		
		this.tramo= tramo;

        }
 
	
 
  
    @Override
    public int getPuntos() {
        return PUNTOS;
    }

    
     // Se Modifica el método hashcode que viene por defecto en java, ya que tenemos que heredar dia de la clase padre.
    @Override
    public int hashCode() {
       return Objects.hash(this.dia,this.tramo);
    }

    
    // Se Modifica el método equals que viene por defecto en java, ya que tenemos que heredar dia de la clase padre.
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
        
        if(this.getDia().equals(other.getDia()) && this.getTramo().equals(other.getTramo()))
			return true;
		return false;
        
        
    }

     // Modifico el  método ToStringque viene por defecto en java, ya que tenemos que heredar dia de la clase padre.
    @Override
    public String toString() {
        return "[dia="+dia.format(FORMATO_DIA)+ "tramo=" + tramo + "]";
    }

       
}
    
    
    

