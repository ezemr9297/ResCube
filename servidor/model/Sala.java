package servidor.model;

import java.util.Map;
public class Sala {

  public static final String [] nomSalas = {"SALA 1","SALA 2","SALA 3","SALA 4","SALA 5","SALA 6","SALA 7","SALA 8"};
  public static final String [] horasTurnos = {"8:00-10:00", "10:00-12:00", "12:00-14:00", "16:00-18:00", "18:00-20:00"};
  private Map<String,Turno> turnos;

  public Sala(){
    // Creamos los turnos de la sala
    for (String hora : horasTurnos) {
      turnos.put(hora, new Turno());
    }
  }

  public void reservarTurno(int turno, String uvus) {
    turnos.get(horasTurnos[turno]).setReservado(uvus);
  }

  public void anularTurno(int turno) {
    turnos.get(horasTurnos[turno]).setReservado("");
  }

  public Turno getTurno(int turno) {
    return turnos.get(horasTurnos[turno]);
  }

  public Map<String,Turno> getTurnos() {
    return turnos;
  }
}
