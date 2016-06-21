/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.beans;

import sw.parcial.DAO.Grupo;
import sw.parcial.DAO.Usuario;
import sw.parcial.Estructura.Mananger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class bgrupo {

    private String nombre;
    private List<Grupo> grupos = new ArrayList<Grupo>();
    private Mananger mananger;

    /**
     * Creates a new instance of bgrupo
     */
    public bgrupo() {
        mananger = (Mananger) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("m");
        CargarGurpos();

    }
    public void CargarGurpos() {
        grupos = mananger.mgrupo.getTodos();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

}
