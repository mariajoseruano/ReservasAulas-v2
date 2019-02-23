
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public abstract class Permanencia {
    
    protected  LocalDate dia;
    protected static final DateTimeFormatter FORMATO_DIA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    
    /* Crear el constructor Permanencia sin parametros*/
    
        protected Permanencia(){
            
        }
        
    /* Crear constructor Permanencia con parametro LocalDate */
        
        protected Permanencia(LocalDate dia) {
		
                setDia(dia);
		
        }
        
        /* Crear el constructor Permanencia con parametro String*/
        
        protected Permanencia(String dia) {
		
                setDia(dia);
		
        }
        
        //Crear los métodos
        
         public LocalDate getDia()
        {
            return dia;
        }
        
        protected void setDia(LocalDate dia){
            
            if (dia == null) {
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		}
		
		this.dia= dia;
        }      
        
        protected void setDia(String dia) {
        	
            if (dia == null) {
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
            }
	
            try {
		
                this.dia = LocalDate.parse(dia,FORMATO_DIA);
		
            } catch (DateTimeParseException e) {
		
                throw new IllegalArgumentException("El formato del día de la permanencia no es correcto.");
            }
	}
        
        public abstract int getPuntos();

    @Override
    
    public abstract String toString();
    
        
                  
    @Override
    public abstract int hashCode() ;
       

    @Override
    public abstract boolean equals(Object obj);
    
}        
        
        

         
        
            
            

	

            
        
        
         
        
         
        
        
        
    
