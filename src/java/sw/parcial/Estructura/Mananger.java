/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Estructura;

import sw.parcial.Controlador.ManangerGrupo;
import sw.parcial.Controlador.ManangerPerfil;
import sw.parcial.Controlador.ManangerUsuario;
import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.DAO.Grupo;
import sw.parcial.DAO.Usuario;
import sw.parcial.datos.Archivos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author Rafael
 */
public class Mananger implements Serializable {

    public ArrayList<objecto> listaModificar = new ArrayList<objecto>();
    public ManangerPerfil mperfil;
    public ManangerGrupo mgrupo;
    public ManangerUsuario musuario;
    public String path;
    private int TIPO_ARCHIVO = -1;

    public Mananger() {

    }

    public void init() {
        mperfil = new ManangerPerfil();
        mgrupo = new ManangerGrupo();
        musuario = new ManangerUsuario();
    }

    public void Cargar() {
        CargarGrupos();
        CargarUsuarios();
    }
    public void CargarUsuarios(){
       musuario.agregarUsuario(new Usuario("ADMIN", "ADMIN", "AMINISTRACION"));
        musuario.agregarUsuario(new Usuario("SYS", "SYS", "DESARROLLO"));
    }

    public void CargarGrupos() {
        mgrupo.agregarGrupo(new Grupo("AMINISTRACION"));
        mgrupo.agregarGrupo(new Grupo("DESARROLLO"));
        mgrupo.agregarGrupo(new Grupo("SISTEMAS"));

    }

    public ManangerPerfil getMperfil() {
        return mperfil;
    }

    public void setMperfil(ManangerPerfil mperfil) {
        this.mperfil = mperfil;
    }

    public ManangerGrupo getMgrupo() {
        return mgrupo;
    }

    public void setMgrupo(ManangerGrupo mgrupo) {
        this.mgrupo = mgrupo;
    }

    public ManangerUsuario getMusuario() {
        return musuario;
    }

    public void setMusuario(ManangerUsuario musuario) {
        this.musuario = musuario;
    }

    public void setTIPO_ARCHIVO(int TIPO_ARCHIVO) {
        this.TIPO_ARCHIVO = TIPO_ARCHIVO;
    }

    public int getTIPO_ARCHIVO() {
        return TIPO_ARCHIVO;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
    

}
