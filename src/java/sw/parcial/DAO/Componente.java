package sw.parcial.DAO;

public class Componente {

    private Boolean Editable;
    private Boolean habilitado;
    private String nombre;
    private Boolean Visible;
    private Integer componenteID;
    private Integer formularioID;

    public Componente( ) { 
      }
    public Componente(Boolean Editable,Boolean habilitado,String nombre,Boolean Visible,Integer componenteID,Integer formularioID){
        this.Editable = Editable;
        this.habilitado = habilitado;
        this.nombre = nombre;
        this.Visible = Visible;
        this.componenteID = componenteID;
        this.formularioID = formularioID;
    }
    public Boolean getEditable() {
        return Editable;
    }

    public void setEditable(Boolean Editable) {
        this.Editable = Editable;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getVisible() {
        return Visible;
    }

    public void setVisible(Boolean Visible) {
        this.Visible = Visible;
    }

    public Integer getComponenteID() {
        return componenteID;
    }

    public void setComponenteID(Integer componenteID) {
        this.componenteID = componenteID;
    }

    public Integer getFormularioID() {
        return formularioID;
    }

    public void setFormularioID(Integer formularioID) {
        this.formularioID = formularioID;
    }


}