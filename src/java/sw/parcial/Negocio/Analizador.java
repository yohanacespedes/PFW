/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Negocio;

import sw.parcial.Estructura.elemento;
import java.util.StringTokenizer;
import sw.parcial.util.Opciones;

/**
 *
 * @author Rafael
 */
public class Analizador {

    public Analizador() {
    }

    private String getComponente(String linea) {
        //  linea.su   
        return "";
    }

    public static boolean esTag(String linea) {
        if (linea.length() > 2) {
            String inicio = linea.substring(0, 1);
            String fin = linea.substring(linea.length() - 1);
            return inicio.equals("<") && (fin.equals(">"));
        } else {
            return false;
        }

    }

    public static int inicioFinTag(String linea) {
        return linea.substring(1, 2).equals("/") ? Constantes.FIN : Constantes.INICIO;
    }

    //---- importante
    public static String getPrimero(String linea) {
        StringTokenizer st = new StringTokenizer(linea);
        return st.nextToken();
    }

    public static String getTag(String substring) {
        String cadena = substring.substring(1, substring.length());
        if (cadena.charAt(cadena.length() - 1) == '>') {
            return cadena.substring(0, cadena.length() - 1);
        } else {
            return cadena;
        }
    }

    public static boolean esFinMenu(String linea) {
        return linea.equals(Etiquetas.FIN_MENU);
    }

    public static boolean esLetras(String cadena) {
        boolean sw = true;
        for (int i = 0; (i < Constantes.caracateres.length) && sw; i++) {
            int res = cadena.indexOf(Constantes.caracateres[i]);
            if (res != -1) {
                sw = !sw;
            }
        }
        return sw;
    }

    public static String getCadena(String nocadena) {
        String sw = "";
        boolean b = false;
        for (int i = 0; (i < nocadena.length()) && !b; i++) {
            if (nocadena.charAt(i) != '>') {
                sw = sw + nocadena.charAt(i);
            } else {
                b = !b;
            }
        }
        return sw;
    }

    public static String modifcarTexto(elemento e) {

        return "";
    }

    public static String getType(String linea) {
        String salida = "";
        StringTokenizer tokens = new StringTokenizer(linea);
        boolean sw = true;
        while (tokens.hasMoreTokens() && sw) {
            salida = tokens.nextToken();
            if (salida.charAt(0) == 't' || salida.charAt(0) == 'T') {
                sw = !sw;
            }

        }
        salida = getSubType(salida);
        return salida;
    }

    public static String getSubType(String sublinea) {
        String salida = "";
        StringTokenizer tokens = new StringTokenizer(sublinea, "=");
        while (tokens.hasMoreTokens()) {
            String valor = tokens.nextToken().trim();
            if (valor.charAt(0) == '"') {
                salida = valor.trim();
            }
        }
        return salida.substring(1, salida.length() - 1);

    }

    public static String modificarLineaJSP(elemento ee) {
        String cadena = ee.getCadena();
        if (ee.getComponente().equalsIgnoreCase("ul") || ee.getComponente().equalsIgnoreCase("li")) {
            String estado = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
            int indice = cadena.indexOf(">");
            String p = cadena.substring(0, indice);
            String u = cadena.substring(indice, cadena.length());

            cadena = p + " " + estado + " " + u;

        } else {
            if (Etiquetas.getEtiquetaJSP(ee.getComponente()) != -1) {
                String visible = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
                String readonly = ee.isEditable() ? Opciones.SOLOLECTURA : "";
                String habilitado = !ee.isHabilitado() ? Opciones.DESABILITADO : "";
                String estado = visible + " " + readonly + " " + habilitado;
                cadena = cadena.substring(0, cadena.length() - 1) + " " + estado + "" + cadena.charAt(cadena.length() - 1);
            } else {
                if (ee.getComponente().equalsIgnoreCase("select") || ee.getComponente().equalsIgnoreCase("option")) {
                    String visible = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
                    String habilitado = !ee.isHabilitado() ? Opciones.DESABILITADO : "";
                    String estado = visible + " " + habilitado;
                    int indice = cadena.indexOf(">");
                    String p = cadena.substring(0, indice);
                    String u = cadena.substring(indice, cadena.length());
                    cadena = p + " " + estado + " " + u;
                }

            }

        }
        return cadena;
    }

    public static String modificarLineaJSF(elemento ee) {
        String cadena = ee.getCadena();
        if (ee.getComponente().equalsIgnoreCase("menu")
                || ee.getComponente().equalsIgnoreCase("submenu")) {
            String estado = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
            int indice = cadena.indexOf(" ");
            if (indice != -1) {
                String p = cadena.substring(0, indice);
                String u = cadena.substring(indice, cadena.length());
                cadena = p + " " + estado + " " + u;
            } else {
                 indice = cadena.indexOf(">");
                String p = cadena.substring(0, indice);
                String u = cadena.substring(indice, cadena.length());

                cadena = p + " " + estado + " " + u;
            }

        } else {
            if (Etiquetas.esEtiqueta_JSF(ee.getComponente())) {
                String visible = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
                String readonly = !ee.isEditable() ? Opciones.SOLOLECTURA : "";
                String habilitado = !ee.isHabilitado() ? Opciones.DESABILITADO : "";
                String estado = visible + " " + readonly + " " + habilitado;
                int indice = cadena.indexOf(" ");
                String p = cadena.substring(0, indice);
                String u = cadena.substring(indice, cadena.length());
                //cadena = cadena.substring(0, cadena.length() - 1) + " " + estado + "" + cadena.charAt(cadena.length() - 1);
                cadena = p + " " + estado + " " + u;
            } else {
                if (ee.getComponente().equalsIgnoreCase("selectOneMenu")) {
                    String visible = !ee.isVisible() ? Opciones.NO_VISIBLE_COMPLETO : "";
                    String habilitado = !ee.isHabilitado() ? Opciones.DESABILITADO : "";
                    String estado = visible + " " + habilitado;
                    int indice = cadena.indexOf(" ");
                    String p = cadena.substring(0, indice);
                    String u = cadena.substring(indice, cadena.length());
                    cadena = p + " " + estado + " " + u;
                }

            }

        }
        return cadena;
    }

    public static void main(String[] args) {
        String prueba = "p:submenu";
        Analizador a = new Analizador();
        System.out.println(prueba.substring(2, prueba.length()));
        System.out.println(a.esTag(prueba));
        System.out.println(Analizador.inicioFinTag(prueba));
        System.out.println(Analizador.getTag(Analizador.getPrimero(prueba)));
        System.out.println(Etiquetas.getEtiquetaJSP("select"));
        String inicio = prueba.substring(0, 1);
        String fin = prueba.substring(prueba.length() - 1);
        System.out.println(Etiquetas.esEtiqueta_JSF("inputText"));

    }

}
