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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class busuario {

    private String login;
    private String pass;
    private String grupo;
    private Map<String, Grupo> grupos = new HashMap<String, Grupo>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Mananger mananger;

    /**
     * Creates a new instance of busuario
     */
    public busuario() {
        mananger = (Mananger) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("m");
        CargarGrupos();
        CargarUsuarios();
    }

    public String getLogin() {
        return login;
    }

    public void CargarUsuarios() {
        usuarios = mananger.musuario.getTodos();
    }

    public void CargarGrupos() {
        List datos = mananger.mgrupo.getTodos();
        for (int i = 0; i < datos.size(); i++) {
            Grupo element = (Grupo) datos.get(i);
            grupos.put(element.getNombre(), element);
        }

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    public Map<String, Grupo> getGrupos() {
        return grupos;
    }

}
