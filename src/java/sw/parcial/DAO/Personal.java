package sw.parcial.DAO;

public class Personal {

    private String Apellidos;
    private String CI;
    private String Nombre;
    private String Tel;
    private Integer personalID;

    public Personal( ) { 
      }
    public Personal(String Apellidos,String CI,String Nombre,String Tel,Integer personalID){
        this.Apellidos = Apellidos;
        this.CI = CI;
        this.Nombre = Nombre;
        this.Tel = Tel;
        this.personalID = personalID;
    }
    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public Integer getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Integer personalID) {
        this.personalID = personalID;
    }


}