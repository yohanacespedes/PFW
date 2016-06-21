package sw.parcial.DAO;

import java.io.Serializable;

public class Usuario implements Serializable {


    private String login = "";
    private String pass = "";
    private String nombreGrupo = "";
   

    public Usuario( ) { 
      }

    public Usuario(String login, String pass, String nombreGrupo) {
        this.login = login;
        this.pass = pass;
        this.nombreGrupo = nombreGrupo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @Override
    public String toString() {
        return "Login = "+this.login+ " PASS = "+this.pass;
    }
   
    
}