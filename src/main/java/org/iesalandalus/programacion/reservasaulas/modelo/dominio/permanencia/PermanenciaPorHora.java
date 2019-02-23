
 
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class PermanenciaPorHora extends Permanencia{
    
    private static final int PUNTOS=3;
    private static final int HORA_INICIO=8;
    private static final int HORA_FINAL=22;
    private static final DateTimeFormatter FORMATO_HORA=DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime hora;


    public PermanenciaPorHora(LocalDate dia, LocalTime hora){   
     
           super(dia);
           setHora(hora);
    }  
    
    public PermanenciaPorHora(String dia, LocalTime hora){   
     
     super(dia);
     setHora(hora);
    }  
    
    public PermanenciaPorHora(String dia, String hora){   
     
     super(dia);
     setHora(hora);
    }    

    public PermanenciaPorHora( PermanenciaPorHora permanenciaPorHora){
        
    	        
        if (permanenciaPorHora == null) {
		throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
        }
            setDia(permanenciaPorHora.dia);
            setHora(permanenciaPorHora.hora);
    }

    PermanenciaPorHora(LocalDate DIA_LD, String HORA_STR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PermanenciaPorHora(LocalDate DIA_LD, String HORA_STR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PermanenciaPorHora(LocalDate DIA_LD, String HORA_STR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PermanenciaPorHora(LocalDate DIA_LD, String hora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PermanenciaPorHora(LocalDate DIA_LD, String hora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PermanenciaPorHora(LocalDate DIA_LD, String hora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
               
        
    public LocalTime getHora(){
        
        return hora;
       
    }
    
    private void setHora( LocalTime hora){
        
        if ( hora==null)
        {
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula."); 
        }
        if (hora.getHour() > HORA_INICIO && hora.getHour() < HORA_FINAL) {
		if (hora.getMinute() == 0) {
                    this.hora = hora;
                } else {
                    throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
                }
        } else {
            throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las 8 y las 22.");
		}
	}

    
    private void setHora(String hora) {
        
        if (hora == null) {
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
	
        }
            try {
		
                this.setHora(LocalTime.parse(hora, FORMATO_HORA));
            } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
            }
	}


    public int getPuntos(){
        return PUNTOS;
        
    }
    // Se Modifica el método hashcode que viene por defecto en java, ya que tenemos que heredar dia de la clase padre.

    @Override
    public int hashCode() {
        return Objects.hash(this.dia,this.hora);
    }

    
    // Se Modifica el método equals que viene por defecto en java, ya que tenemos que heredar dia de la clase padre.
    @Override
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
        final PermanenciaPorHora other = (PermanenciaPorHora) obj;
        
        if(this.getDia().equals(other.getDia()) && this.getHora().equals(other.getHora()))
            return true;
	return false;
    }

    
     // Modifico el  método ToString que viene por defecto en java, ya que tenemos que heredar dia de la clase padre.
    @Override
    public String toString() {
       
        return "[dia=" + dia.format(FORMATO_DIA) + ", hora=" + hora.format(FORMATO_HORA) + "]";
	}

    }
    

    
    
    
    
    
        
        

    
            
            


    
    
    
    
    
    
    
    
    
    
    

    