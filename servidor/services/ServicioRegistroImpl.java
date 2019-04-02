import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioRegistroImpl extends UnicastRemoteObject implements ServicioRegistro {

  PrintWriter fd;
  List<Registro> historial;

  ServicioLogImpl(String f) throws RemoteException {

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

    if(tipo=true){
      String actividad = año+"-"+mes+"-"+dia+":"+hora+":"+minutos+":"+segundos+" --> USUARIO: "+nombre+" ACCION: "+accion+
      " SALA: "+sala+" TURNO: "+turno+" DIA: "+dia;
    }
    else{
      String actividad = año+"-"+mes+"-"+dia+":"+hora+":"+minutos+":"+segundos+" --> USUARIO: "+nombre+" ACCION: "+accion;
    }

    fd.println(actividad);
    //System.out.println(actividad);
    fd.flush();
  }

  public List<Registro> getHistorial(String uvus) {
    // TO-DO
  }
}
