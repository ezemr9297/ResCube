package servidor.model;

public class Turno {

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
    boolean result = false;
    if(this.uvusReservante == uvus){
      result = true;
    }
    return result;
  }
}
