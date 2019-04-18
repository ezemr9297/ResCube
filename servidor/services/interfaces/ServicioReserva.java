package servidor.services.interfaces;

import java.rmi.*;
import servidor.model.*;

public interface ServicioReserva extends Remote {

  public Calendario verSalas(int dia, String uvus) throws RemoteException;
	public boolean reservarSala (int dia, int sala, int turno, String uvus) throws RemoteException;
  public boolean borrarReserva(int dia,int nSala, int nTurno, String uvus) throws RemoteException;
  public ServicioLogIn creaLogIn() throws RemoteException;
}
