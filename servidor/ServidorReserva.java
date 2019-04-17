import java.rmi.*;
import java.rmi.server.*;

import servidor.services.*;

public class ServidorReserva {

    static public void main (String args[]) {

       if (args.length !=1 ) {
            System.err.println("Uso: ServidorCube numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            ServicioReservaImpl srv = new ServicioReservaImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Reserva", srv);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorCube:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
