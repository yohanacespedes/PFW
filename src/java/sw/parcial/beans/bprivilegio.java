/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.beans;

import sw.parcial.DAO.ConfiguracionGrupo;
import sw.parcial.Estructura.Mananger;
import sw.parcial.Estructura.elemento;
import sw.parcial.Estructura.nodo;
import sw.parcial.Estructura.objecto;
import sw.parcial.Negocio.Constantes;
import sw.parcial.Negocio.Escaner;

import sw.parcial.datos.ManejadorDB;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.swing.JFileChooser;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import sw.parcial.datos.Archivos;
import java.lang.reflect.Type;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class bprivilegio implements Serializable {

    private TreeNode root;
    private TreeNode raiz;
    private TreeNode selectedNode;
    private TreeNode selectedNodeRaiz;
    private Escaner escaner;
    private String path = "E:\\PROYECTOS\\SW\\ExamenI\\Proyecto\\DemoJSP\\web\\login.jsp";
    private ArrayList<elemento> lista = null;
    private String seleccion;
    private String nombrePerfil;
    private Mananger mananger;
    private String Tipo;
   
    ArrayList<objecto> objetos;

    

    /**
     * Creates a new instance of bprivilegio
     */
    public bprivilegio() {
        root = new DefaultTreeNode("root", null);
        raiz = new DefaultTreeNode("raiz", null);
        escaner = new Escaner();
        objetos = new ArrayList<>();
       mananger = (Mananger) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("m");
        //lista = escaner.getComponentes(path);
        //   listarTemas(lista, root);

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
        // event.getSource().toString();

        //   nodo d = (nodo) event.getTreeNode().getData();
        objecto e = (objecto) event.getTreeNode().getData();
        root = new DefaultTreeNode("root", null);
        listarTemas(e.getLista(), root);
      //  String eee= ""+ e.getLiena();
        //       Tema t = ct.getIDTemaUuario(selecionTema);
        //    Cargar(t.getId());

        //   pathUp = ct.path(t);
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
    public void ArchivosEnCarpeta() {
      
        File dir = new File(path);

        File[] fs = dir.listFiles();
        Escaner escaner = new Escaner();
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isFile()) {
              
                ArrayList<elemento> l = escaner.getComponentes(fs[i].getAbsolutePath(),mananger.getTIPO_ARCHIVO());
                String nombre = fs[i].getName();
                String pathh = fs[i].getAbsolutePath();
                objecto oo = new objecto(nombre, pathh, l);
                objetos.add(oo);

                escaner.init();

            }
        }
        CargarLista();

    }

    public void CargarLista() {
        for (int i = 0; i < objetos.size(); i++) {
            objecto tt = ((objecto) objetos.get(i));
            TreeNode documents = new DefaultTreeNode(tt, raiz);

            // listarTemas(tt.getLista(), documents);
        }
    }

    public void dummyAction() {

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String filename = file.getName();
        //System.out.println("You have selected: " + filename);  testing to see if file seleected was right
        //String path = file.getAbsolutePath();
        String pa = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length());
        this.path = pa;
        mananger.setPath(path);
        AsignarTipo();
        //  String path = IOUtils.toString(file.getInputStream(), "UTF-8");
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful" + path + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
             ArchivosEnCarpeta();
             
        }

    }
    private void AsignarTipo(){
        if(this.Tipo.equalsIgnoreCase("JSP"))
            mananger.setTIPO_ARCHIVO(Constantes.JSP);
       else
            mananger.setTIPO_ARCHIVO(Constantes.JSF);
    }

    public void onGuardarPrivilegio() {
        /// Subir a la Seccion;
       
        ConfiguracionGrupo g = new ConfiguracionGrupo(nombrePerfil, objetos);
        mananger.mperfil.agregarPerfil(g);
        Archivos.guardarBD(mananger);
        Archivos.crearDirectorio(path, nombrePerfil);
     /*   mananger = null;
        mananger = Archivos.recuperarBD();
        mananger.mostrar();*/
        toList();
        GenerarPagina();

    }
    public void GenerarPagina(){
       
        for (int i = 0; i < mananger.listaModificar.size(); i++) {
             objecto nuevo =mananger.listaModificar.get(i);
             Escaner.modificarArchivoLinea(nuevo.getPath()+"\\"+nuevo.getNombre(), path+"\\"+nombrePerfil+"\\"
                     +nuevo.getNombre(), nuevo.getLista(),mananger.getTIPO_ARCHIVO());
        }
        
    }

    public void toList() {
        for (int i = 0; i < objetos.size(); i++) {
            ArrayList<elemento> nuevo = new ArrayList<>();
            escaner.listar(objetos.get(i).getLista(), nuevo);
            objecto oo = new objecto(objetos.get(i).getNombre(), path, nuevo);
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
