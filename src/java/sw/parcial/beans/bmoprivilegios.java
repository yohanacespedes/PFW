/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.beans;

import sw.parcial.Controlador.ManangerPerfil;
import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.Estructura.Mananger;
import sw.parcial.Estructura.elemento;
import sw.parcial.Estructura.objecto;
import sw.parcial.Negocio.Constantes;
import sw.parcial.Negocio.Escaner;
import sw.parcial.datos.Archivos;
import java.io.File;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JFileChooser;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class bmoprivilegios {

    private TreeNode root;
    private TreeNode raiz;
    private TreeNode selectedNode;
    private TreeNode selectedNodeRaiz;
    private Escaner escaner;
    private String seleccion;
    private String nombrePerfil;
    private ConfiguracionGrupo mprivilegio;
    private Mananger mananger;
    private String Tipo;

    //ArrayList<objecto> objetos;
    /**
     * Creates a new instance of bprivilegio
     */
    public bmoprivilegios() {
        root = new DefaultTreeNode("root", null);
        raiz = new DefaultTreeNode("raiz", null);
        escaner = new Escaner();
        //   objetos = new ArrayList<>();
        mprivilegio = (ConfiguracionGrupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mp");
        mananger = (Mananger) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("m");

        // lista = mananger.get
        //lista = escaner.getComponentes(path);
//          listarTemas(manangerz, root);
        CargarLista();

    }

    public void listarTemas(ArrayList<elemento> l, TreeNode nodo) {
        // String consulta = "FROM Tema WHERE Tema_id  = " + t.getId() + " AND usuario_id = " + usuario.getId();
        //     List lista = listartodo(consulta);
        //  ArrayList<elemento> li = l.getLista();
        if (l != null) {
            for (int i = 0; i < l.size(); i++) {
                elemento tt = ((elemento) l.get(i));
                TreeNode documents = new DefaultTreeNode(tt, nodo);

                listarTemas(tt.getLista(), documents);
            }
        }
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        seleccion = event.getTreeNode().toString();

        objecto e = (objecto) event.getTreeNode().getData();
        root = new DefaultTreeNode("root", null);
        listarTemas(e.getLista(), root);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", e.toString());
        FacesContext.getCurrentInstance().addMessage(null, message);

        System.out.println(seleccion);

    }

    public void displaySelectedSingle() {
        if (selectedNode != null) {
            Object e = selectedNode.getData();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    ////------------------ tipoo de archivo ----------------

    public void CargarLista() {
        this.nombrePerfil = mprivilegio.getNombre();
        for (int i = 0; i < mprivilegio.componentes.size(); i++) {
            objecto tt = ((objecto) mprivilegio.componentes.get(i));
            TreeNode documents = new DefaultTreeNode(tt, raiz);

            // listarTemas(tt.getLista(), documents);
        }
    }

    public void onGuardarPrivilegio() {

        mananger.mperfil.ModificarPerfil(this.mprivilegio);
        Archivos.guardarBD(mananger);
        Archivos.crearDirectorio(mananger.getPath(), nombrePerfil);

        toList();
        GenerarPagina();

    }

    public void GenerarPagina() {

        for (int i = 0; i < mananger.listaModificar.size(); i++) {
            objecto nuevo = mananger.listaModificar.get(i);
            Escaner.modificarArchivoLinea(nuevo.getPath() + "\\" + nuevo.getNombre(), mananger.getPath() + "\\" + nombrePerfil + "\\"
                    + nuevo.getNombre(), nuevo.getLista(), mananger.getTIPO_ARCHIVO());
        }

    }

    public void toList() {
        mananger.listaModificar.clear();
        for (int i = 0; i < mprivilegio.componentes.size(); i++) {
            ArrayList<elemento> nuevo = new ArrayList<>();
            escaner.listar(mprivilegio.componentes.get(i).getLista(), nuevo);
            objecto oo = new objecto(mprivilegio.componentes.get(i).getNombre(), mananger.getPath(), nuevo);
            mananger.listaModificar.add(oo);
        }

    }

    public void setRaiz(TreeNode raiz) {
        this.raiz = raiz;
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public void setSelectedNodeRaiz(TreeNode selectedNodeRaiz) {
        this.selectedNodeRaiz = selectedNodeRaiz;
    }

    public TreeNode getSelectedNodeRaiz() {
        return selectedNodeRaiz;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
    }

}
