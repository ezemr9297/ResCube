import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import servidor.model.*;
import servidor.services.interfaces.*;

class ServicioLogInImpl extends UnicastRemoteObject implements Serializable, ServicioLogIn {
    LinkedList <Usuario> usuarios;

    public ServicioLogInImpl() throws RemoteException {
      usuarios= new LinkedList<Usuario>();
    }

    public LinkedList <Usuario> iniciarSesion(String uvus, String password){
      return usuarios;
    }
    public void darAltaUsuario(String uvus, String password){
      Usuario usuario_temp = new Usuario(uvus, password);
      usuarios.add(usuario_temp);
    }
    public void darBajaUsuario(String uvus){
      Iterator<Usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        Usuario usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
        }
      }
    }
    public void modificarUsuario(String uvus, String new_password){
      Iterator<Usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        Usuario usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
          this.darAltaUsuario(uvus, new_password);
        }
      }
    }
    public void modificarUsuario(String uvus, String new_uvus, String password){
      Iterator<Usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        Usuario usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
          this.darAltaUsuario(new_uvus, password);
        }
      }
    }
  }
