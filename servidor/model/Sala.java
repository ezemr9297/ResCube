public class Sala {

  public static final String [8] nomSalas = {"SALA 1","SALA 2","SALA 3","SALA 4","SALA 5","SALA 6","SALA 7","SALA 8"};

  private Map<String,Turno> turnos;

  public Sala(){
    // Creamos los turnos de la sala
    for (String hora : Turno.horasTurnos) {
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
    return turnos.get(Turno.horasTurnos[turno]);
  }

  public Map<String,Turno> getTurnos() {
    return turnos;
  }
}
