package sw.parcial.DAO;

import java.util.Date;
import java.util.Date;

public class Bitacora {

    private Date fecha;
    private Date hora;
    private Integer bitacoraID;
    private Integer accionesID;
    private Integer usuarioID;

    public Bitacora( ) { 
      }
    public Bitacora(Date fecha,Date hora,Integer bitacoraID,Integer accionesID,Integer usuarioID){
        this.fecha = fecha;
        this.hora = hora;
        this.bitacoraID = bitacoraID;
        this.accionesID = accionesID;
        this.usuarioID = usuarioID;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getBitacoraID() {
        return bitacoraID;
    }

    public void setBitacoraID(Integer bitacoraID) {
        this.bitacoraID = bitacoraID;
    }

    public Integer getAccionesID() {
        return accionesID;
    }

    public void setAccionesID(Integer accionesID) {
        this.accionesID = accionesID;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }


}