public class Turno {

  public static final String [6] horasTurnos = {"8:00-10:00", "10:00-12:00", "12:00-14:00", "16:00-18:00", "18:00-20:00"};

  private boolean reservado;
  private String uvusReservante;

  public Turno() {
    reservado = false;
  }

  public void setReservado(String uvus) {
    // Marcamos el turno como reservado, indicando el usuario
    reservado = !reservado;
    uvusReservante = uvus;
  }

  public boolean getReservado() {
    return reservado;
  }

  public boolean comprobarReserva(String uvus) {
    return this.uvus == uvus;
  }
}
