package servidor.services;

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

    public boolean iniciarSesion(String uvus, String password){
      boolean usuarioValido;
      for (Usuario usuario : this.usuarios) {
        if (usuario.getUvus().equals(uvus) && usuario.getPassword().equals(password))
          usuarioValido = true;
      }
      return usuarioValido;
    }
    public void darAltaUsuario(String uvus, String password){
      Usuario usuario_temp = new Usuario(uvus, password);
      usuarios.add(usuario_temp);
    }
    public void darBajaUsuario(String uvus){
      for (Usuario usuario : this.usuarios) {
        if(usuario.getUvus().equals(uvus)){
          this.usuarios.remove(usuario);
        }
      }
    }
    public void modificarUsuario(String uvus, String new_password){
      for (Usuario usuario : this.usuarios) {
        if(usuario.getUvus().equals(uvus)){
          this.usuarios.remove(usuario);
          this.darAltaUsuario(uvus, new_password);
        }
      }
    }
    public void modificarUsuario(String uvus, String new_uvus, String password){
      for (Usuario usuario : this.usuarios) {
        if(usuario.getUvus().equals(uvus)){
          this.usuarios.remove(usuario);
          this.darAltaUsuario(new_uvus, password);
        }
      }
    }
  }
