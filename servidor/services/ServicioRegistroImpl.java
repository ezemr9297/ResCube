package servidor.services;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import servidor.services.interfaces.*;
import servidor.model.*;

public class ServicioRegistroImpl extends UnicastRemoteObject implements ServicioRegistro {

  PrintWriter fd;
  List<Registro> historial;

  public ServicioRegistroImpl(String f) throws RemoteException {

    historial = new LinkedList<Registro>();

    try {
         fd = new PrintWriter(new FileWriter(f, true));
    }
    catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }
    catch( IOException e){

    }
  }

  //public synchronized void log(String m) throws RemoteException {
  public synchronized void log(String nombre, String accion, String dia, String sala, String turno, boolean tipo) throws RemoteException {
    try {
         Thread.sleep(1);
    }
    catch( InterruptedException e) {
      // TO-DO
    }

    historial.add(new Registro(nombre, accion, sala, turno));
    String actividad;

    Calendar c = Calendar.getInstance();
    String dia_fecha = Integer.toString(c.get(Calendar.DATE));
    String mes = Integer.toString(c.get(Calendar.MONTH));
    String annio = Integer.toString(c.get(Calendar.YEAR));
    String hora =Integer.toString(c.get(Calendar.HOUR_OF_DAY));
    String minutos = Integer.toString(c.get(Calendar.MINUTE));
    String segundos = Integer.toString(c.get(Calendar.SECOND));

    if(tipo=true){
      actividad = annio+"-"+mes+"-"+dia_fecha+":"+hora+":"+minutos+":"+segundos+" --> USUARIO: "+nombre+" ACCION: "+accion+
      " SALA: "+sala+" TURNO: "+turno+" DIA: "+dia;
    }
    else{
      actividad = annio+"-"+mes+"-"+dia_fecha+":"+hora+":"+minutos+":"+segundos+" --> USUARIO: "+nombre+" ACCION: "+accion;
    }

    fd.println(actividad);
    //System.out.println(actividad);
    fd.flush();
  }

  public List<Registro> getHistorial(String uvus) {
    List<Registro> h = new LinkedList<Registro>();
    for(Registro temp : historial){
      if(temp.getUvus().equals(uvus)){
        h.add(temp);
      }
    }
    return h;
  }
  
}
