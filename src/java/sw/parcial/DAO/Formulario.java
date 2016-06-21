package sw.parcial.DAO;

public class Formulario {

    private String Nombre;
    private String path;
    private Integer formularioID;
    private Integer grupoID;

    public Formulario( ) { 
      }
    public Formulario(String Nombre,String path,Integer formularioID,Integer grupoID){
        this.Nombre = Nombre;
        this.path = path;
        this.formularioID = formularioID;
        this.grupoID = grupoID;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFormularioID() {
        return formularioID;
    }

    public void setFormularioID(Integer formularioID) {
        this.formularioID = formularioID;
    }

    public Integer getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Integer grupoID) {
        this.grupoID = grupoID;
    }


}