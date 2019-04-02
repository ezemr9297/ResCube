import java.util.Date;

public class Registro extends Serializable {

  private String uvus;
  private String accion;
  private String sala;
  private String turno;
  private Date fecha;

  Usuario (String uvus, String accion, String sala, String turno) {
    this.uvus = uvus;
    this.accion = accion;
    this.sala = sala;
    this.turno = turno;
    this.fecha = new Date;
  }

  public void toString() {
    // TO-DO
  }
}
