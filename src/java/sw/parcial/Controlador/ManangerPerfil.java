/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Controlador;

import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.DAO.Grupo;
import sw.parcial.DAO.Usuario;
import sw.parcial.Estructura.objecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Rafael
 */
public class ManangerPerfil implements Serializable{

    private Hashtable<String, ConfiguracionGrupo> perfiles;

    public ManangerPerfil() {
        perfiles = new Hashtable<String, ConfiguracionGrupo>();
    }

    public boolean agregarPerfil(ConfiguracionGrupo g) {
        String d = g.getNombre();
        if (!perfiles.containsKey(d)) {
            perfiles.put(d, g);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarPerfil(String d) {
        if (perfiles.containsKey(d)) {
            perfiles.remove(d);
            return true;
        } else {
            return false;
        }
    }
     public boolean ModificarPerfil(ConfiguracionGrupo g) {
        String d = g.getNombre();
        ConfiguracionGrupo aux;
        if (perfiles.containsKey(d)) {
            aux = perfiles.get(d);
            aux.setComponentes(g.getComponentes());
          /*  perfiles.remove(d);
            perfiles.put(d, aux);*/
            return true;
        } else {
            return false;
        }
    }

    public ConfiguracionGrupo recuperarPerfil(String d) {
        if (perfiles.containsKey(d)) {
            return perfiles.get(d);
        } else {
            return null;
        }
    }

    public Enumeration total() {
        return perfiles.keys();
    }

    public void mostrar() {
        String d;
        Enumeration e = this.total();
        while (e.hasMoreElements()) {
            // recuperando el DNI
            d = (String) e.nextElement();
            // buscamos el dni
            ConfiguracionGrupo per = this.perfiles.get(d);
            System.out.println(per.toString());

        }
    }
    public ArrayList<ConfiguracionGrupo> getTodos(){
      return  new ArrayList<ConfiguracionGrupo>(perfiles.values());
        
    }
    public static void main(String[] args) {
        ManangerPerfil mp = new ManangerPerfil();
        ConfiguracionGrupo g = new ConfiguracionGrupo("sdfds1", null);
        mp.agregarPerfil(g);
        mp.agregarPerfil(new ConfiguracionGrupo("sdfds", null));
        
        mp.agregarPerfil(new ConfiguracionGrupo("sdfds2", null));
        
        System.out.println(mp.getTodos().toString());
        g.setComponentes(new ArrayList<objecto>());
        mp.ModificarPerfil(g);
         System.out.println("-----");
         System.out.println(mp.getTodos().toString());
    }

}
