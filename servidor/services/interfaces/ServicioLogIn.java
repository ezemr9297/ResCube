package servidor.services.interfaces;

import java.rmi.*;
import java.util.*;
import servidor.model.*;

public interface ServicioLogIn extends Remote {

  boolean iniciarSesion(String uvus, String password) throws RemoteException;
  void darAltaUsuario(String uvus, String password) throws RemoteException;
  void darBajaUsuario(String uvus) throws RemoteException;
  void modificarUsuario(String uvus, String new_password) throws RemoteException;
  void modificarUsuario(String uvus, String new_uvus, String new_password) throws RemoteException;

}
