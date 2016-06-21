/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.beans;

import sw.parcial.DAO.Usuario;
import sw.parcial.Estructura.Mananger;
import sw.parcial.datos.Archivos;
import sw.parcial.datos.ManejadorDB;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
public class blogin implements Serializable {

    Usuario usuario;

    /**
     * Creates a new instance of busuario
     */
    public blogin() {
        this.usuario = new Usuario();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void autentificar() {
        try {
            Mananger m = VerificarManager();
            Usuario uu = m.musuario.recuperarUsuario(usuario.getLogin());
                if (uu.getPass().equals(usuario.getPass())) {
                    
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", uu);
                     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("m", m);
                    // FacesContext.getCurrentInstance().getExternalContext().redirect("faces/views/main2.xhtml");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
                } else { 
                    FacesContext.getCurrentInstance().getExternalContext().redirect("");
                }
         

        } catch (IOException ex) {
            Logger.getLogger(blogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void autentificar1() {
        try {
            Mananger m = VerificarManager();
            Usuario uu = m.musuario.recuperarUsuario(usuario.getLogin());
                if (uu.getPass().equals(usuario.getPass())) {
                    if (uu.getPass().equals("SYS")) {
                      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", uu);
                     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("m", m);
                    // FacesContext.getCurrentInstance().getExternalContext().redirect("faces/views/main2.xhtml");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/Principal.xhtml");  
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", uu);
                     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("m", m);
                    // FacesContext.getCurrentInstance().getExternalContext().redirect("faces/views/main2.xhtml");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
                    }
                    
                } else { 
                    FacesContext.getCurrentInstance().getExternalContext().redirect("");
                }
         

        } catch (IOException ex) {
            Logger.getLogger(blogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Mananger VerificarManager() {
        Mananger m = Archivos.recuperarBD();
        if (m != null) {
            return m;
        } else {
            m = new Mananger();
            m.init();
            m.Cargar();
            Archivos.guardarBD(m);
            return m;
        }

    }

}
