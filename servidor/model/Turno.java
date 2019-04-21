package servidor.model;

import java.io.Serializable;

public class Turno implements Serializable {

  private boolean reservado;
  private String uvusReservante;

  public Turno() {
    reservado = false;
    uvusReservante = "";
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
    if(this.uvusReservante.equals(uvus)){
      result = true;
    }
    return result;
  }
}
