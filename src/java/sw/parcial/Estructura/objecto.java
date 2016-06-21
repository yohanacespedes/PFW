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
public class objecto implements Serializable {

    private String nombre;
    private String path;
    private ArrayList<elemento> lista;

    public objecto() {
    }

    public objecto(String nombre, String path, ArrayList<elemento> lista) {
        this.nombre = nombre;
        this.path = path;
        this.lista = lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLista(ArrayList<elemento> lista) {
        this.lista = lista;
    }

    public ArrayList<elemento> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return super.toString()+getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    

}
