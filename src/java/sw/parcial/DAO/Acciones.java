package sw.parcial.DAO;

public class Acciones {

    private String nombre;
    private Integer accionesID;

    public Acciones( ) { 
      }
    public Acciones(String nombre,Integer accionesID){
        this.nombre = nombre;
        this.accionesID = accionesID;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAccionesID() {
        return accionesID;
    }

    public void setAccionesID(Integer accionesID) {
        this.accionesID = accionesID;
    }


}