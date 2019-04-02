import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ServicioLogInImpl extends UnicastRemoteObject implements ServicioLogIn, Serializable {
    List <usuario> usuarios;

    ServicioLogImpl() throws RemoteException {
      usuarios= new LinkedList<usuario>();
    }

    LinkedList <usuario> inicio_sesion(String uvus, String password){
      return usuarios;
    }
    void alta_usuario(String uvus, String password){
      usuario_temp = new usuario(uvus, password);
      usuarios.add(usuario_temp);
    }
    void baja_usuario(String uvus){
      ListIterator<usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
        }
      }
    }
    void mod_usuario(String uvus, String new_password){
      ListIterator<usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
          this.alta_usuario(uvus, new_password);
        }
      }
    }
    void mod_usuario(String uvus, String new_uvus, String password){
      ListIterator<usuario> iterador = usuarios.descendingIterator();
      while(iterador.hasNext()){
        usuario_temp = iterador.next();
        if(usuario_temp.getUvus().equals(uvus)){
          iterador.remove();
          this.alta_usuario(new_uvus, new_password);
        }
      }
    }
  }
