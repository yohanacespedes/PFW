/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Controlador;

import sw.parcial.DAO.Grupo;
import sw.parcial.DAO.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Rafael
 */
public class ManangerGrupo implements Serializable {

    private Hashtable<String, Grupo> grupos;

    public ManangerGrupo() {
        grupos = new Hashtable<String, Grupo>();
    }

    public boolean agregarGrupo(Grupo g) {
        String d = g.getNombre();
        if (!grupos.containsKey(d)) {
            grupos.put(d, g);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarGrupo(String d) {
        if (grupos.containsKey(d)) {
            grupos.remove(d);
            return true;
        } else {
            return false;
        }
    }

    public Grupo recuperarGrupo(String d) {
        if (grupos.containsKey(d)) {
            return grupos.get(d);
        } else {
            return null;
        }
    }

    public Enumeration total() {
        return grupos.keys();
    }

    public ArrayList<Grupo> getTodos() {
        return new ArrayList<Grupo>(grupos.values());

    }

    

    public void mostrar() {
        String d;
        Enumeration e = this.total();
        while (e.hasMoreElements()) {
            // recuperando el DNI
            d = (String) e.nextElement();
            // buscamos el dni
            Grupo per = this.grupos.get(d);
            System.out.println(per.toString());

        }
    }

}
