import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioReservaImpl extends UnicastRemoteObject implements ServicioLogImpl{

  private static final int NO_RESERVA = 10;

  private ServicioRegistroImpl log;
  private String fichero = "Acciones_realizadas";
  private Calendario [2] calendario;


  ServicioReservaImpl() throws RemoteException{
    calendario = new Calendario[2]();
    log = new ServicioRegistroImpl(fichero);
  }


  /*
  **  Retorna el calendario del dia indicado por el cliente
  */
  Calendario verSalas(int dia, String uvus) throws RemoteException{

    log.log(uvus, "VER ESTADO SALAS", Calendario.dias[dia], NO_RESERVA, NO_RESERVA, false );
    // devolvemos el calendario correspondiente al dia indicado
    return calendario[dia];
  }

  /*
  ** Realiza, si se puede, la reserva de un turno de una sala para un día.
  ** La reserva se realiza si la sala no está reservada y el usuario no ha
  ** reservado para ese día.
  */
  boolean reservarSala (int dia, int nSala, int nTurno, String uvus) throws RemoteException{

    boolean permitido = false;

    Sala sala = calendario[dia].getSala(nSala);
    Turno turno = sala.getTurno(nTurno);

    if (calendario[dia].comprobarReserva(uvus)) {
      // Ya tiene reserva ese dia
      log.log(uvus, "INTENTO DE RESERVA MULTIPLE EN UN DÍA", Calendario.dias[dia], NO_RESERVA, NO_RESERVA, false );
    }
    else{
      if(turno.getReservado() == false ){
        calendario[dia].reservar(nSala, nTurno, uvus);
        permitido=true;

        log.log(uvus, "RESERVA REALIZADA", Calendario.dias[dia], Sala.nomSalas[nSala], Turno.horasTurnos[nTurno], true);
      }
      else{
        // Ese turno de esa sala esta ya reservado
        log.log(uvus, "SALA YA RESERVADA", Calendario.dias[dia], NO_RESERVA, NO_RESERVA, false );
      }
    }
    return permitido;
  }

  /*
  ** Elimina una reserva del calendario
  */
  boolean borrarReserva(int dia, int nSala, int nTurno, String uvus) throws RemoteException{

    boolean permitido = false;

    Sala sala = calendario[dia].getSala(nSala);
    Turno turno = sala.getTurno(nTurno);

    if(!turno.comprobarReserva(uvus)) {
      //No tiene una reserva que borrar
      log.log(uvus, "INTENTO DE BORRADO DE RESERVA NO EXISTENTE", Calendario.dias[dia], NO_RESERVA, NO_RESERVA, false );
    }
    else{
      calendario[dia].anular(nSala, nTurno);
      permitido = true;

      log.log(uvus, "RESERVA CANCELADA", Calendario.dias[dia], Sala.nomSalas[nSala], Turno.horasTurnos[nTurno], false );
    }
    return permitido;
  }

  /*ServicioLogIn creaLogIn(String uvus) throws RemoteExcpetion{
    return new ServicioLogInImpl(uvus);
  }*/
}
