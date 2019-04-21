package servidor.model;

import java.io.Serializable;
import java.util.*;
import java.util.LinkedHashMap;

public class Calendario implements Serializable {

  public static final String [] nomSalas = {"SALA 1","SALA 2","SALA 3","SALA 4","SALA 5","SALA 6","SALA 7","SALA 8"};
  public static final String [] dias = {"HOY", "MAÑANA"};

  private Map<String,Sala> salas;
  private List<String> uvusReservantes;

  public Calendario(){
    uvusReservantes = new ArrayList<String> ();
    salas = new LinkedHashMap<String,Sala>();
    // Creamos las salas y le asignamos un nombre
    for (String nomSala : Sala.nomSalas) {
      salas.put(nomSala, new Sala());
    }
  }

  public Sala getSala(int sala) {
    return salas.get(Sala.nomSalas[sala]);
  }

  public Map<String,Sala> getSalas() {
    return salas;
  }

  public void reservar(int sala, int turno, String uvus) {
    // añadimos usuario a la lista de usuarios q han reservado en este dia
    uvusReservantes.add(uvus);
    // reservamos la sala especificada, indicando el turno y el usuario
    salas.get(nomSalas[sala]).reservarTurno(turno, uvus);
  }

  public void anular(int sala, int turno, String uvus) {
    // eliminamos usuario de la lista de usuarios q han reservado en este dia
    uvusReservantes.remove(uvusReservantes.indexOf(uvus));
    // reservamos la sala especificada, indicando el turno y el usuario
    salas.get(nomSalas[sala]).anularTurno(turno);
  }

  public boolean comprobarReserva (String uvus) {
    boolean encontrado = true;
    if (uvusReservantes.indexOf(uvus) == -1) {
      encontrado = false;
    }

    return encontrado;
  }
}
