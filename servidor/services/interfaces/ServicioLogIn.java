package servidor.services.interfaces;

import java.rmi.*;
import java.util.*;
import servidor.model.*;

public interface ServicioLogIn extends Remote {

  LinkedList <Usuario> iniciarSesion(String uvus, String password);
  void darAltaUsuario(String uvus, String password);
  void darBajaUsuario(String uvus);
  void modificarUsuario(String uvus, String new_password);
  void modificarUsuario(String uvus, String new_uvus, String new_password);

}
