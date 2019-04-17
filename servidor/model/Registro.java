package servidor.model;

import java.util.Date;
import java.io.Serializable;

public class Registro implements Serializable {

  private String uvus;
  private String accion;
  private String sala;
  private String turno;
  private Date fecha;

  public Registro (String uvus, String accion, String sala, String turno) {
    this.uvus = uvus;
    this.accion = accion;
    this.sala = sala;
    this.turno = turno;
    this.fecha = new Date();
  }

  public String toString(){
    String s="Fecha: "+fecha.toString()+" Usuario: "+uvus+" Accion: "+accion+
    " Sala: "+sala+" Turno: "+turno;
    return s;
  }
}
