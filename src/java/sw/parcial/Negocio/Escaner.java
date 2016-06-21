/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Negocio;

import sw.parcial.Estructura.Pila;
import sw.parcial.Estructura.elemento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import sw.parcial.util.Opciones;

/**
 *
 * @author Rafael
 */
public class Escaner implements Serializable {

    //  private String path = C:\\Users\\Rafael\\Documents\\NetBeansProjects\\WebApplication3\\web\\index.jsp ;
    private ArrayList<elemento> lista;
    private Pila pila;
    private boolean menu;

    public Escaner() {
        lista = new ArrayList<>();
        pila = new Pila();
        menu = false;
    }

    public void init() {
        lista = new ArrayList<>();
        pila = new Pila();
        menu = false;
    }

    private static elemento buscarElmento(ArrayList<elemento> lista, int linea) {
        elemento ee = null;
        for (int i = 0; i < lista.size(); i++) {
            if (linea == lista.get(i).getLiena()) {
                ee = lista.get(i);
                lista.remove(i);
                return ee;
            }

        }
        return ee;
    }

    public static void modificarArchivoLinea(String orign, String dest, ArrayList<elemento> lista, int tipo) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        //----
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(orign);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            fichero = new FileWriter(dest);
            pw = new PrintWriter(fichero);

            String linea;
            int cant = 1;
            while ((linea = br.readLine()) != null) {
                elemento ee = Escaner.buscarElmento(lista, cant);
                if (ee != null) {
                     String nuevo = "";
                    if(tipo == Constantes.JSP)
                       nuevo = Analizador.modificarLineaJSP(ee);
                    else 
                        nuevo = Analizador.modificarLineaJSF(ee);
                    pw.println(nuevo);
                } else {
                    pw.println(linea);
                }
                cant++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
                if (null != fr) {
                    fr.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        //   cambiarNombreArchivo(orign, dest);

    }

    public static void cambiarNombreArchivo(String actual, String nuevo) {
        File f1 = new File(actual);
        File f2 = new File(nuevo);
        f2.renameTo(f1);
    }

    public static void leerArchivo(String path) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int cant = 1;
            while ((linea = br.readLine()) != null) {
                System.out.print(cant + "  ");
                System.out.print("'" + linea.trim() + "'");
                System.out.println("");
                cant++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public ArrayList<elemento> getComponentes(String path, int tipo) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int cant = 1;
            while ((linea = br.readLine()) != null) {
                //    System.out.print(cant + "  ");
                //   System.out.print("'" + linea.trim() + "'");
                if (tipo == Constantes.JSP) {
                    CargarComponenteJSP(linea.trim(), cant);
                } else {
                    CargarComponenteJSF(linea.trim(), cant);
                }
                cant++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lista;
    }

    private void CargarComponenteJSP(String Cadena, int linea) {
        if (Analizador.esTag(Cadena)) {
            if (Constantes.INICIO == Analizador.inicioFinTag(Cadena)) {
                String c = Analizador.getTag(Analizador.getPrimero(Cadena));
                if (Analizador.esLetras(c)) {
                    //        int id = Etiquetas.getEtiquetaJSP(c);
                    if (!Etiquetas.esMenuJSP(c)) {
                        if (Etiquetas.esMenuItems(c)) {
                            agregarElemento(Cadena, linea, c);
                        } else {
                            if (Etiquetas.esEtiquetaJSP(c)) {
                                elemento ee = new elemento(linea, c, Cadena, null);
                                ee.setComponente(Analizador.getType(Cadena));
                                lista.add(ee);
                            }

                        }

                    } else {
                        agregarLista(Cadena, linea, c);
                    }

                }

            } else {
                if (Etiquetas.FIN_MENU.equalsIgnoreCase(Cadena) || Etiquetas.FIN_OPTION.equalsIgnoreCase(Cadena)) {
                    pila.getObjeto();
                }

            }
        }

    }

    private void CargarComponenteJSF(String Cadena, int linea) {
        if (Analizador.esTag(Cadena)) {
            if (Constantes.INICIO == Analizador.inicioFinTag(Cadena)) {
                String c = Analizador.getTag(Analizador.getPrimero(Cadena));
                c = c.substring(2, c.length());
                if (Analizador.esLetras(c)) {
                    //        int id = Etiquetas.getEtiquetaJSP(c);
                    if (!Etiquetas.esMenu_JSF(c)) {
                        if (Etiquetas.esMenuItems_JSF(c)) {
                            agregarElemento(Cadena, linea, c);
                        } else {
                            if (Etiquetas.esEtiqueta_JSF(c)) {
                                elemento ee = new elemento(linea, c, Cadena, null);
                                //ee.setComponente(Analizador.getType(Cadena));
                                lista.add(ee);
                            }

                        }

                    } else {
                        agregarLista(Cadena, linea, c);
                    }

                }

            } else {

                if (Etiquetas.FIN_MENU_JSF.equalsIgnoreCase(Cadena) 
                        || Etiquetas.FIN_OPTION_JSF.equalsIgnoreCase(Cadena)
                        || Etiquetas.FIN_SUBMENU_JSF.equalsIgnoreCase(Cadena)) {
                    pila.getObjeto();
                }

            }
        }

    }

    private void agregarLista(String Cadena, int linea, String e) {
        ArrayList<elemento> elista = new ArrayList<>();
        elemento ee = new elemento(linea, e, Cadena, elista);
        if (pila.esVacia()) {
            pila.adicionar(elista);
            lista.add(ee);
        } else {
            ArrayList<elemento> le = (ArrayList<elemento>) pila.consultaTope();
            pila.adicionar(elista);
            le.add(ee);

        }
    }

    private void agregarElemento(String Cadena, int linea, String e) {
        elemento ee = new elemento(linea, e, Cadena, null);
        if (pila.esVacia()) {

            lista.add(ee);
        } else {
            ArrayList<elemento> le = (ArrayList<elemento>) pila.consultaTope();
            le.add(ee);

        }
    }

    public void setLista(ArrayList<elemento> lista) {
        this.lista = lista;
    }

    public ArrayList<elemento> getLista() {
        return lista;
    }

    public void listar(ArrayList<elemento> l, ArrayList<elemento> nuevo) {

        if (l != null) {
            for (int i = 0; i < l.size(); i++) {
                elemento tt = ((elemento) l.get(i));
                //
                nuevo.add(tt);

                listar(tt.getLista(), nuevo);
            }
        }
    }

    public static void main(String[] arg) {
        Escaner.leerArchivo("E:\\PROYECTOS\\SW\\ExamenI\\Proyecto\\DemoJSP\\web\\login.jsp");
        String actual = "E:\\PROYECTOS\\SW\\ExamenI\\Proyecto\\DemoJSP\\web\\index.jsp";
        String nuevo = "E:\\PROYECTOS\\SW\\ExamenI\\Proyecto\\DemoJSP\\web\\index.jsp";
        //   String dis = "style="+'"'+"display: none"+'"';
        //  Escaner.modificarArchivoLinea(actual, nuevo, 36, "<li><a href=" + '"' + '"' + " >Submenu1</a></li>");
        Escaner e = new Escaner();
        //    ArrayList<elemento> ee = e.getComponentes("E:\\PROYECTOS\\SW\\ExamenI\\Proyecto\\DemoJSP\\web\\login.jsp");
        // System.out.println(ee);
    }

}
