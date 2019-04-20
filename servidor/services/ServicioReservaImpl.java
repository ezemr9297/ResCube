package servidor.services;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

import servidor.services.interfaces.*;
import servidor.services.*;
import servidor.model.*;

public class ServicioReservaImpl extends UnicastRemoteObject implements ServicioReserva{

  private static final String NO_RESERVA = "NO RESERVA";
  public static final String [] horasTurnos = {"8:00-10:00", "10:00-12:00", "12:00-14:00", "16:00-18:00", "18:00-20:00"};
  public static final String [] nomSalas = {"SALA 1","SALA 2","SALA 3","SALA 4","SALA 5","SALA 6","SALA 7","SALA 8"};
  public static final String [] dias = {"HOY", "MAÑANA"};

  private ServicioLogIn login;
  private ServicioRegistro log;
  private String fichero = "Acciones_realizadas";
  private Calendario [] calendario;


  public ServicioReservaImpl() throws RemoteException{
    calendario = new Calendario[2];
    calendario[0]= new Calendario();
    calendario[1]= new Calendario();

    login = new ServicioLogInImpl();
    log = new ServicioRegistroImpl(this.fichero);
  }


  /*
  **  Retorna el calendario del dia indicado por el cliente
  */
  public Calendario verSalas(int dia, String uvus) throws RemoteException{

    log.log(uvus, "VER ESTADO SALAS", dias[dia], NO_RESERVA, NO_RESERVA, false );
    // devolvemos el calendario correspondiente al dia indicado
    return calendario[dia];
  }

  /*
  ** Realiza, si se puede, la reserva de un turno de una sala para un día.
  ** La reserva se realiza si la sala no está reservada y el usuario no ha
  ** reservado para ese día.
  */
  public boolean reservarSala (int dia, int nSala, int nTurno, String uvus) throws RemoteException{

    boolean permitido = false;

    Sala sala = calendario[dia].getSala(nSala);
    Turno turno = sala.getTurno(nTurno);

    if (calendario[dia].comprobarReserva(uvus)) {
      // Ya tiene reserva ese dia
      log.log(uvus, "INTENTO DE RESERVA MULTIPLE EN UN DÍA", dias[dia], NO_RESERVA, NO_RESERVA, false );
    }
    else{
      if(turno.getReservado() == false ){
        calendario[dia].reservar(nSala, nTurno, uvus);
        permitido=true;

        log.log(uvus, "RESERVA REALIZADA", dias[dia], nomSalas[nSala], horasTurnos[nTurno], true);
      }
      else{
        // Ese turno de esa sala esta ya reservado
        log.log(uvus, "SALA YA RESERVADA", dias[dia], NO_RESERVA, NO_RESERVA, false );
      }
    }
    return permitido;
  }

  /*
  ** Elimina una reserva del calendario
  */
  public boolean borrarReserva(int dia, int nSala, int nTurno, String uvus) throws RemoteException{

    boolean permitido = false;

    Sala sala = calendario[dia].getSala(nSala);
    Turno turno = sala.getTurno(nTurno);

    if(!turno.comprobarReserva(uvus)) {
      //No tiene una reserva que borrar
      log.log(uvus, "INTENTO DE BORRADO DE RESERVA NO EXISTENTE", dias[dia], NO_RESERVA, NO_RESERVA, false );
    }
    else{
      calendario[dia].anular(nSala, nTurno, uvus);
      permitido = true;

      log.log(uvus, "RESERVA CANCELADA", dias[dia], nomSalas[nSala], horasTurnos[nTurno], false );
    }
    return permitido;
  }

  public ServicioLogIn obtenerLogIn() throws RemoteException {
    return this.login;
  }

  public ServicioRegistro obtenerServRegistro() throws RemoteException {
    return this.log;
  }
}
