package cliente;

import java.io.Console;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;
import java.util.Map;
import java.util.List;

import servidor.services.interfaces.*;
import servidor.services.*;
import servidor.model.*;

class ClienteReserva {

    private static ServicioReserva srv;

    public static void main (String args[]) {

        if (args.length!=2) {
            System.err.println("Uso: ClienteReserva hostregistro numPuertoRegistro");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            srv = (ServicioReserva) Naming.lookup("//" + args[0] + ":" + args[1] + "/ServicioReserva");

            // Se comienza a mostrar la interfaz del cliente
            System.out.println("********* Bienvenido a ResCube, el sistema de reserva de salas de la incubadora ***********\n");
            System.out.println("Inicie sesión o cree un usuario para continuar\n");
            ServicioLogIn login = srv.obtenerLogIn();
            // Se crea el lector de entrada del usuario
            Scanner scanner = new Scanner(System.in);
            String option = null; // número de opción introducido por el usuario

            // Se solicita una opción hasta que coincida con las ofrecidas
            do {
              if (option != null)
                System.out.println("Lo siento, el carácter introducido no coincide con ninguna de las opciones (1, 2, 3).\n Pruebe otra vez. \n");

              System.out.println("1.- Iniciar sesión\n");
              System.out.println("2.- Crear usuario\n");
              System.out.println("3.- Salir\n");
              System.out.println("Introduce la opción deseada: ");
              option = scanner.nextLine();
            } while(!(option.equals("1") || option.equals("2") || option.equals("3")));

            // Se sale de la aplicación
            if (option.equals("3")){
              System.out.println("\nGracias por utilizar ResCube!\n");
              System.exit(0);
            }
            else {
              // Solicitamos datos para el inicio de sesión o la creación del usuario
              Console terminal = System.console();
              String uvus = new String (terminal.readLine("Introduce uvus: "));
              String password = new String (terminal.readPassword("Intruduce contraseña: "));
              if (option.equals("2")) {
                // Se crea el usuario
                login.darAltaUsuario(uvus, password);
              }
              else {
                // Se comprueba la información de usuario introducida
                boolean correcto;
                do {
                  correcto = login.iniciarSesion(uvus,password);
                  if (!correcto) {
                    System.out.println("Lo siento, el usuario o la contraseña introducidos no es válido, prueba otra vez:\n");
                    uvus = new String (terminal.readLine("Introduce uvus: "));
                    password = new String (terminal.readPassword("Intruduce contraseña: "));
                  }
                } while(!correcto);

              }
              // Se inicia el menú con la información del usuario
              Usuario usuario = new Usuario(uvus, password);
              startMenu(usuario);
            }
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteReserva:");
            e.printStackTrace();
        }
    }

    private static void startMenu(Usuario usuario) throws RemoteException {
      // Se crea el lector de entrada del usuario
      Scanner scanner = new Scanner(System.in);
      String option = null; // número de opción introducido por el usuario

      System.out.println("Hola, " + usuario.getUvus() + ". ¿Qué deseas hacer?  \n");
      // Se solicita una opción hasta que coincida con las ofrecidas
      do {
        System.out.println("1.- Ver disponibilidad de salas\n");
        System.out.println("2.- Obtener historial\n");
        System.out.println("3.- Modificar usuario\n");
        System.out.println("4.- Salir\n");
        System.out.print("Introduce una opción: ");
        option = scanner.nextLine();

        switch (option) {
          case "1":
            menuSalas(usuario);
            break;
          case "2":
            mostrarHistorial(usuario);
            break;
          case "3":
            menuModificarUsuario(usuario);
            break;
          case "4":
            System.out.println("\nGracias por utilizar ResCube!\n");
            return;
          default:
            System.out.println("Lo siento, el carácter introducido no coincide con ninguna de las opciones (1, 2, 3, 4).\n Pruebe otra vez. \n");
        }
      } while(true);

    }

    private static void menuSalas(Usuario usuario) throws RemoteException {
      // Se crea el lector de entrada del usuario
      Scanner scanner = new Scanner(System.in);
      String option = null; // número de opción introducido por el usuario

      // Se pregunta por el día a mostrar
      Calendario calendario;
      System.out.println("Selecciona el día\n");
      do {
        System.out.println("0.- Hoy\n");
        System.out.println("1.- Mañana\n");
        System.out.println("2.- Volver al menú principal\n");
        System.out.print("Introduce una opción: ");
        option = scanner.nextLine();

        switch (option) {
          case "0":
          case "1":
            int dia = Integer.parseInt(option);
            // Se obtiene el calendario de hoy o mañana
            calendario = srv.verSalas(dia, usuario.getUvus());
            // Se imprime la disponibilidad de salas para el día solicitado
            System.out.println("\t8-10:00 (1) \t10-12:00 (2)\t12-14:00 (3)\t14-16:00 (4)\t16-18:00 (5)\t18-20:00 (6)\n");
            System.out.println("\n");
            String res;
            for (Map.Entry<String, Sala> sala : calendario.getSalas().entrySet()) {
              System.out.print(sala.getKey() + "\t");
              for (Map.Entry<String, Turno> turno : sala.getValue().getTurnos().entrySet()) {
                if(turno.getValue().getReservado())
                  res="Ocupada\t";
                else
                  res="Libre\t\t";
                System.out.print("  "+res);
              }
              System.out.println("\n");
            }
            // Se pregunta si desea hacer o borrar una reserva
            do {
              System.out.print("Indica si quieres reservar (0) ó borrar una reserva (1): ");
              option = scanner.nextLine();
            } while (!(option.equals("0") || option.equals("1")));
            // Se solicitan los datos de la sala y turno a reservar
            int numSala;
            int numTurno;
            do {
              System.out.print("Indica la sala: ");
              numSala = Integer.parseInt(scanner.nextLine());
              System.out.print("Indica el turno: ");
              numTurno = Integer.parseInt(scanner.nextLine());
            } while (!(numSala > 0 && numSala < 9) || !(numTurno > 0 && numTurno < 7));
            // Se procede a reservar o borrar
            boolean permitido;
            if (option.equals("0")) // reservar
              permitido = srv.reservarSala(dia, numSala-1, numTurno-1, usuario.getUvus());
            else // borrar reserva
              permitido = srv.borrarReserva(dia, numSala-1, numTurno-1, usuario.getUvus());

            if (permitido)
              System.out.println("Operación realizada con éxito!\n");
            else
              System.out.println("Operación fallida! Consulta historial para más detalles\n");

          case "2":
            return;
          default:
            System.out.println("Lo siento, el carácter introducido no coincide con ninguna de las opciones (0, 1, 2).\n Pruebe otra vez. \n");
        }
      } while(true);
    }

    private static void mostrarHistorial(Usuario usuario) throws RemoteException {
      List<Registro> historial = srv.obtenerServRegistro().getHistorial(usuario.getUvus());
      for(Registro temp: historial) {
        System.out.println(temp);
      }
    }

    private static void menuModificarUsuario(Usuario usuario) throws RemoteException{
      Scanner scanner = new Scanner(System.in);
      String option = null;
      do {
        if(option != null){
          System.out.println("Lo siento, el carácter introducido no coincide con ninguna de las opciones (1, 2, 3).\n Pruebe otra vez. \n");
        }
        System.out.println("1.- Modificar contraseña\n");
        System.out.println("2.- Modificar uvus\n");
        System.out.println("3.- Volver al menú principal\n");
        System.out.print("Introduce una opción: ");
        option = scanner.nextLine();
      } while(!(option.equals("1") || option.equals("2") || option.equals("3")));

      if (option.equals("3")) {
        //Volvemos al menu principal
        return;
      }
      else {
        ServicioLogIn login = srv.obtenerLogIn();
        //Primero de todos para modificar un usuario comprobamos que sus credenciales son correctas
        Console terminal = System.console();
        //String mod_uvus = new String (terminal.readLine("Introduzca de nuevo el uvus: "));
        String mod_password;
        boolean correcto;
        do {
          mod_password = new String (terminal.readPassword("Intruduzca la contraseña actual: "));
          correcto = usuario.getPassword().equals(mod_password);
          if (!correcto)
            System.out.println("Lo siento, la contraseña introducida no es válida, pruebe otra vez.\n");
        } while(!correcto);
        //Una vez comprobadas las credenciales procedemos a modificar
        if(option.equals("1")) { //Si queremos cambiar la contraseña
          String new_password = new String (terminal.readPassword("Intruduzca la nueva contraseña: "));
          login.modificarUsuario(usuario.getUvus(), new_password);
        }
        else { //Si queremos cambiar el uvus
          String new_uvus = new String (terminal.readLine("Intruduce el nuevo uvus: "));
          login.modificarUsuario(usuario.getUvus(), new_uvus, mod_password);
        }
        System.out.println("Modificación realizada con éxito\n");
        return;
      }
    }
}
