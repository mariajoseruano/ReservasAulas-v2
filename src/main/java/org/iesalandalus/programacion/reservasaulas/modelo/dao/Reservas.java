
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

/**
 *
 * @author usuario
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;



public class Reservas {
    
    //Declaración de varibales.
    
    
         private List<Reserva> coleccionReservas;
         private static final float MAX_PUNTOS_PROFESOR_MES=200;
         
	
        
       
        // Se crea Constructor.
        
        public Reservas (){
            
            coleccionReservas= new ArrayList<>();
            
            
        }
        
        //Se crea el constructor copia
        
        public Reservas (Reservas reservas) {
		setReservas(reservas);
	}
	
	private void setReservas(Reservas reservas) {
            
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		
	}
        
	
        // Se crea copiaprofunda
        
	private List<Reserva> copiaProfundaReservas(List<Reserva>reservas) {
		List<Reserva> otrasReservas = new ArrayList<>();
		for (Reserva reserva:reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
	}
	
	
        	
	//GETTER
	
        public List<Reserva>getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}
        
	public int getNumReservas() {
		return coleccionReservas.size();
	}
	
        // Método Insertar
        //Modifico con respecto al ReservasAulasv1
        
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
           
              
            if (reserva == null) {
			
                throw new IllegalArgumentException("No se puede realizar una reserva nula.");
            }
            if (this.coleccionReservas.contains(reserva)) {
			
                throw new OperationNotSupportedException("La reserva ya existe.");
            }
            if (!esMesSiguienteOPosterior(reserva)) {
		
                throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		}
            if (getPuntosGastadosReserva(reserva) > MAX_PUNTOS_PROFESOR_MES) {
		
                throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
            }
         
            	Reserva reservaRealizada = getReservaDia(reserva.getPermanencia().getDia());
		
            if (reservaRealizada != null) {
			
                if (reservaRealizada.getPermanencia() instanceof PermanenciaPorTramo && reserva.getPermanencia() instanceof PermanenciaPorHora) {
		
                    throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
			}
			if (reservaRealizada.getPermanencia() instanceof PermanenciaPorHora && reserva.getPermanencia() instanceof PermanenciaPorTramo) {
				throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
			}
		}
		
                coleccionReservas.add(new Reserva(reserva));
	}



        
        /**
         * esMesSiguienteOPosterior: sirve para comprobar si es correcta la fecha de la reserva en cuanto no se pueden realizar para el mes en curso.       
         * @see https://www.tutorialspoint.com/javatime/javatime_localdate.htm
         * @param reserva
         * @return 
         */
     
       
        private boolean esMesSiguienteOPosterior ( Reserva reserva){
            
        	boolean correcto = false;
		int anoReserva = reserva.getPermanencia().getDia().getYear();
		int anoActual = LocalDate.now().getYear();
		int mesReserva = reserva.getPermanencia().getDia().getMonthValue();
		int mesActual = LocalDate.now().getMonthValue();
		
                if (anoReserva > anoActual) {
			correcto = true;
		} else {
			if (mesActual < 12 && mesReserva > mesActual) {
				correcto = true;
			}
		}
		return correcto;
      
	}

        //Este método suma todos los puntos gastados hasta el momento.

	private float getPuntosGastadosReserva(Reserva reserva) {
		
            float puntosGastados = 0;
            
            List<Reserva> listaReservasProfesorMes = this.getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
			
                for (Reserva r : listaReservasProfesorMes) {
			
                    puntosGastados = puntosGastados + r.getPuntos();
		}
                    return puntosGastados + reserva.getPuntos();
		}
	

	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia) {
			
           List<Reserva> reservasProfesor = new ArrayList<>();
            
           for (Reserva reserva : coleccionReservas) {
				
                if (reserva.getProfesor().equals(profesor)&& reserva.getPermanencia().getDia().getMonthValue() == dia.getMonthValue()
		&& reserva.getPermanencia().getDia().getYear() == dia.getYear()) {
					
                    reservasProfesor.add(new Reserva(reserva));
		}
            }
		return reservasProfesor;
	}
       

	private Reserva getReservaDia(LocalDate dia) {
			
            for(Reserva reserva : coleccionReservas) {
		
                if(reserva.getPermanencia().getDia().equals(dia))
                    return new Reserva(reserva);
                }
		return null;
	}
	
        
        
        public Reserva buscar(Reservas reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		if (indice != -1) {
			return new Reserva(coleccionReservas.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if ( coleccionReservas.contains(reserva)){
                        coleccionReservas.remove(reserva);
		}
		else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	
        
	
	public List <String> representar() {
            
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(reserva.toString());
		}
		return representacion;
	}
        
       
       /*   getReservasProfesor: Tiene que devolver de todas las reservas realizadas, 
        aquellas que pertenecen al profesor pasado como parámetro.*/
        
        
        public List<Reserva> getReservasProfesor(Profesor profesor) {
		
                if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		
                List<Reserva>reservasProfesor = new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas ){
			if( reserva.getProfesor().equals(profesor))
                        {
                            reservasProfesor.add(new Reserva(reserva));
                        }
		}
		return reservasProfesor;
	}

                       
        /* getReservasAula(Aula aula)Tiene que devolver de todas las reservas realizadas,
        aquellas que pertenecen al aula pasada como parámetro.*/
        
        public List<Reserva> getReservasAula( Aula aula){
            
                if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List <Reserva>reservasAula= new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas) {
                    
			if(reserva.getAula().equals(aula) ) 
                        {
				reservasAula.add(new Reserva(reserva));                                
                        }
		}
		return reservasAula;
        }
        
        /*getReservasPermanencia(Permanencia permanencia)Tiene que devolver de todas las reservas realizadas, 
        aquellas cuya permanencia sea igual a la pasada como parámetros.*/
        
        public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
                if(permanencia==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de una permanencia nula.");
		
                List<Reserva> reservasPermanencia= new ArrayList<>();
                
		
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getPermanencia().equals(permanencia)) {
                            reservasPermanencia.add(new Reserva(reserva));
				
                       }
		}
		return reservasPermanencia;
        
        }
                       
        public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getAula().equals(aula)&& reserva.getPermanencia().equals(permanencia)){
                         
                            return false;
                            
                        }
		
                 }
		return true;
	}

    
        
            
          
}

        
        




    


    

    

