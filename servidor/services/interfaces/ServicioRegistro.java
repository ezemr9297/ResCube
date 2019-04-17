package servidor.services.interfaces;

import java.rmi.*;

public interface ServicioRegistro extends Remote {

	public void log (String nombre, String accion, String dia, String sala, String turno, boolean tipo) throws RemoteException;

	//boolean tipo indica si la accion realizada se permite hacer o no
}
