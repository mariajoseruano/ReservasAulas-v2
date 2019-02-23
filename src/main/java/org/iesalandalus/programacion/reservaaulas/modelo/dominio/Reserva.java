/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservaaulas.modelo.dominio;

import java.util.Objects;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

/**
 *
 * @author usuario
 */
public class Reserva {
    
     /*Se Declara los atributos.*/
    
    
    private Profesor profesor;
    private Aula aula;
    private Permanencia permanencia;
    
    
    /* Se crea el constructor*/
        
        public Reserva (Profesor profesor, Aula aula, Permanencia permanencia){
            
            setProfesor(profesor);
            setAula(aula);
            setPermanencia(permanencia);
                       
        }
        
        /*Se crea el constructor copia */
        
        public Reserva (Reserva reserva){
            
            if (reserva==null)
                    
                    throw new IllegalArgumentException("No se puede copiar una reserva nula.");
           
            setProfesor(reserva.getProfesor());
            setAula(reserva.getAula());
            setPermanencia(reserva.getPermanencia());
                       
        }


   
   
        private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		}
		this.profesor = new Profesor(profesor);
	}
	
	private void setAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		}
		this.aula = new Aula(aula);
	}
        
        private void setPermanencia (Permanencia permanencia){
		if (permanencia == null) {
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		}
		if (permanencia instanceof PermanenciaPorHora) {
			this.permanencia = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
		} else {
			this.permanencia = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
		}
	}

     
	
	public Profesor getProfesor() {
		return new Profesor(profesor);
	}
	
	public Aula getAula() {
		return new Aula(aula); 
	}

        
        public Permanencia getPermanencia() {
            
        
            if (permanencia instanceof PermanenciaPorHora) {
		return new PermanenciaPorHora((PermanenciaPorHora) permanencia);
            } else {
		return new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
            }
	}


	public float getPuntos() {
		return this.aula.getPuntos() + this.permanencia.getPuntos();
        
        }
         
                
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.profesor);
        hash = 53 * hash + Objects.hashCode(this.aula);
        hash = 53 * hash + Objects.hashCode(this.permanencia);
        return hash;
    }

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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.permanencia, other.permanencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia + ", puntos=" + getPuntos()+ "]";
        
    }
    
      
    
}




    


    

    
