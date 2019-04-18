package servidor.services.interfaces;

import java.util.*;
import java.rmi.*;
import servidor.model.*;

public interface ServicioRegistro extends Remote {

	public void log (String nombre, String accion, String dia, String sala, String turno, boolean tipo) throws RemoteException;
  //El boolean indica si la operación es permitida o no
	public List<Registro> getHistorial(String uvus);
}
