package sw.parcial.DAO;

import java.io.Serializable;

public class Grupo  implements Serializable{

    private String nombre;
  

    public Grupo( ) { 
      }

    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

   
    
    

}