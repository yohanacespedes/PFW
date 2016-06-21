/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.DAO;

import sw.parcial.Estructura.objecto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
    public class ConfiguracionGrupo implements Serializable{

    public String nombre;
    public ArrayList<objecto> componentes;

    public ConfiguracionGrupo() {
    }

    public ConfiguracionGrupo(String nombre, ArrayList<objecto> componentes) {
        this.nombre = nombre;
        this.componentes = componentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<objecto> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<objecto> componentes) {
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        String aaa = componentes!=null?componentes.toString():"";
        return "nombre :"+this.nombre+" Lista :"+aaa; //To change body of generated methods, choose Tools | Templates.
    }
    

}
