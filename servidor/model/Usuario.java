public class Usuario {

  private String uvus;
  private String password;

  public Usuario(String uvus, String password) {
    this.uvus = uvus;
    this.password = password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public void setUvus(String usuario){
    this.uvus = usuario;
  }

  public boolean getPassword(){
    return this.password;
  }

  public String getUvus(){
    return this.uvus;
  }
}
