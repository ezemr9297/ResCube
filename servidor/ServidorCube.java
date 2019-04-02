import java.rmi.*;
import java.rmi.server.*;

class ServidorCube {

    static public void main (String args[]) {

       if (args.length !=1 ) {
            System.err.println("Uso: ServidorCube numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            ServicioLogInImpl srv = new ServidorCubeImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Log", srv);
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
