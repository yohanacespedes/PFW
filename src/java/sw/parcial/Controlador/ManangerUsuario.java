/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Controlador;

import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.DAO.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author Rafael
 */
public class ManangerUsuario implements Serializable {

    private Hashtable<String, Usuario> usuarios;

    public ManangerUsuario() {
        usuarios = new Hashtable<String, Usuario>();
    }

    public boolean agregarUsuario(Usuario g) {
        String d = g.getLogin();
        if (!usuarios.containsKey(d)) {
            usuarios.put(d, g);
            return true;
        } else {
            return false;
        }
    }

    public boolean ModificarGrupo(Usuario g) {
        String d = g.getLogin();
        Usuario aux;
        if (usuarios.containsKey(d)) {
            aux = usuarios.get(d);
            aux.setLogin(g.getLogin());
            usuarios.remove(d);
            usuarios.put(d, aux);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarUsuario(String d) {
        if (usuarios.containsKey(d)) {
            usuarios.remove(d);
            return true;
        } else {
            return false;
        }
    }

    public Usuario recuperarUsuario(String d) {
        if (usuarios.containsKey(d)) {
            return usuarios.get(d);
        } else {
            return null;
        }
    }

    public Enumeration total() {
        return usuarios.keys();
    }

    public void mostrar() {
        String d;
        Enumeration e = this.total();
        while (e.hasMoreElements()) {
            // recuperando el DNI
            d = (String) e.nextElement();
            // buscamos el dni
            Usuario per = this.usuarios.get(d);
            System.out.println(per.toString());

        }
    }

    public ArrayList<Usuario> getTodos() {
        return new ArrayList<Usuario>(usuarios.values());

    }

    public static void main(String[] args) {
        ManangerUsuario u = new ManangerUsuario();
        u.agregarUsuario(new Usuario("xx", "yy", "zzz"));
        u.agregarUsuario(new Usuario("xx1", "yy1", "zzz1"));
        u.agregarUsuario(new Usuario("xx2", "yy2", "zzz2"));
        u.mostrar();
        System.out.println(u.recuperarUsuario("xx1").toString());

    }

}
