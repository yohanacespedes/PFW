/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.beans;

import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.DAO.Grupo;
import sw.parcial.Estructura.Mananger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class bmperfil {

    private List<ConfiguracionGrupo> perfiles = new ArrayList<ConfiguracionGrupo>();
    private Map<String, Grupo> grupos = new HashMap<String, Grupo>();
    private Mananger mananger;
    private String grupo;
    private ConfiguracionGrupo cgrupo;

    /**
     * Creates a new instance of bmperfil
     */
    public bmperfil() {
        mananger = (Mananger) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("m");
        perfiles = mananger.mperfil.getTodos();
        CargarGrupos();
    }

    public void CargarGrupos() {
        List datos = mananger.mgrupo.getTodos();
        for (int i = 0; i < datos.size(); i++) {
            Grupo element = (Grupo) datos.get(i);
            grupos.put(element.getNombre(), element);
        }

    }

    public void displaySelectedSingle() {
        if (cgrupo != null) {
            //    Object e = selectedNode.getData();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", cgrupo.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void onRowSelect(SelectEvent event) {
        try {
            ConfiguracionGrupo g = (ConfiguracionGrupo) event.getObject();
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mp", g);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/view/VMPrifilegios.xhtml");
            //  FacesMessage msg = new FacesMessage("Car Selected", g.getNombre());
            //  FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            Logger.getLogger(bmperfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPerfiles(List<ConfiguracionGrupo> perfiles) {
        this.perfiles = perfiles;
    }

    public List<ConfiguracionGrupo> getPerfiles() {
        return perfiles;
    }

    public void setCgrupo(ConfiguracionGrupo cgrupo) {
        this.cgrupo = cgrupo;
    }

    public ConfiguracionGrupo getCgrupo() {
        return cgrupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    public Map<String, Grupo> getGrupos() {
        return grupos;
    }

}
