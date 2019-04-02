import java.rmi.*;

interface ServicioLogIn extends Remote {

  LinkedList <usuario> iniciarSesion(String uvus, String password)
  void darAltaUsuario(String uvus, String password)
  void darBajaUsuario(String uvus)
  void modificarUsuario(String uvus, String new_password)
  void modificarUsuario(String uvus, String new_uvus, String new_password)
  
}
