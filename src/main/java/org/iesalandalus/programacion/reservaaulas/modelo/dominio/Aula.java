
package org.iesalandalus.programacion.reservaaulas.modelo.dominio;

import java.util.Objects;

/**
 *
 * @author usuario
 */
public class Aula {
    
     private String nombre="Salon de Actos";
     private static final float PUNTOS_POR_PUESTO=(float) 0.5;
     private static final int MIN_PUESTOS=5;
     private static final int MAX_PUESTOS=50;
     private int puestos;
        
        /* Se crea el constructor*/
        
        public Aula(String nombre, int puestos){
            
            setNombre(nombre);
            setPuestos(puestos);
                       
        }
        
        /*Se crea el constructor copia */
        
        public Aula (Aula aula){
            
            if (aula==null)
                    
                    throw new IllegalArgumentException("No se puede copiar un aula nula.");
           
            setNombre(aula.nombre);
            setPuestos(aula.puestos);
        }
        
        /* Se crea los métodos Setter y Getter*/
        
        private void setNombre( String nombre){
            
            if  (nombre == null) {
                    throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
            }
            if (nombre.trim().equals("")) {
                    throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
            }
		this.nombre = nombre;
                
        }
         public String getNombre(){
            
            return nombre;
        }
        
            
        public int getPuestos() {
		return puestos;
	}

	private void setPuestos(int puestos) {
            
		if (puestos < MIN_PUESTOS || puestos > MAX_PUESTOS) {
			throw new IllegalArgumentException("El número de puestos no es correcto.");
		}
		this.puestos = puestos;
	}

	public float getPuntos() {
		return this.puestos * PUNTOS_POR_PUESTO;
        
        }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + this.puestos;
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
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" +"nombre=" + nombre + ", puestos=" + puestos + ']';
        
    }

    
      
    
}

    
