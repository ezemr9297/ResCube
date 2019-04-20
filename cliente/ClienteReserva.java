package cliente;

import java.rmi.*;
import java.rmi.server.*;
import servidor.services.interfaces.*;
import servidor.model.*;

class ClienteReserva {
    static public void main (String args[]) {
        if (args.length!=2) {
            System.err.println("Uso: ClienteReserva hostregistro numPuertoRegistro");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {

            ServicioReserva srv = (ServicioReserva) Naming.lookup("//" + args[0] + ":" + args[1] + "/ServicioReserva");
            System.out.println("Funciona");
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteReserva:");
            e.printStackTrace();
        }
    }
}
