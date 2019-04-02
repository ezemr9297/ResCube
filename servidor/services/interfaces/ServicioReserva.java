import java.rmi.*;

interface ServicioReserva extends Remote {
  
  Calendario verSalas(int dia, String uvus) throws RemoteException;
	boolean reservarSala (int dia, int sala, int turno, String uvus) throws RemoteException;
  boolean borrarReserva(int dia, String uvus) throws RemoteException;
  //ServicioLogIn creaLogIn(String uvus) throws RemoteExcpetion;
}
