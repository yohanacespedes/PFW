/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw.parcial.Estructura;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class elemento implements Serializable{
    private int liena;
    private String componente;
    private String cadena;
    private ArrayList<elemento> lista;
    private boolean habilitado = false;
    private boolean visible = false;
    private boolean editable = false;

    public elemento() {
    }

    public elemento(int liena, String componente, String cadena, ArrayList<elemento> lista) {
        this.liena = liena;
        this.componente = componente;
        this.cadena = cadena;
        this.lista = lista;
       
    }

    public int getLiena() {
        return liena;
    }

    public void setLiena(int liena) {
        this.liena = liena;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public ArrayList<elemento> getLista() {
        return lista;
    }

    public void setLista(ArrayList<elemento> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
       String aa =  (lista != null)?lista.toString():"";
        String s  ="[ linea : "+liena+ ", nombre = "+this.componente+" lista : "+aa+" ] ";
        String es = "H = "+""+habilitado+ " v = "+""+visible+" E = "+""+editable;
        return s+es ; //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    
    
}
