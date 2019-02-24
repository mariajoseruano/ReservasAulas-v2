/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author usuario
 */
public interface IControladorReservasAulas {

    void anularReserva(Reserva reserva) throws OperationNotSupportedException;

    void borrarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

    void borrarProfesor(Profesor profesor) throws OperationNotSupportedException;

    Aula buscarAula(Aula aula);

    Profesor buscarProfesor(Profesor profesor);

    Reserva buscarReserva(Reserva reserva);

    void comenzar();

    boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

    List<Reserva> getReservasAula(Aula aula);

    List<Reserva> getReservasPermanencia(Permanencia permanencia);

    List<Reserva> getReservasProfesores(Profesor profesor);

    // Aulas:Ahora se llama al modelo no a aulas para insertar el aula.
    void insertarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

    //Profesores: lo mismo que aulas
    void insertarProfesor(Profesor profesor) throws OperationNotSupportedException;

    // Reservas: los mismo cambios que aulas
    void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

    List<String> representarAulas();

    List<String> representarProfesores();

    List<String> representarReservas();

    void salir();
    
}
