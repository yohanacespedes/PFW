/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author Administrador
 */
public class Constantes {

    public static String DIRECTORIO = "model";
    public static String TAB_ATRIBUTO = "    ";
    public static String DATE = "import java.util.Date;";

    public static String nombreClase(String nombre) {

        String cabecera = "public class "
                + Constantes.convertirPrimero(nombre) + " {";

        return cabecera;
    }

    public static String getAtributo(String tipo, String nombre) {
        if (tipo.equalsIgnoreCase("TINYINT"))
                tipo = "BOOL";
        return Constantes.TAB_ATRIBUTO + "private " + Constantes.getTipo(tipo) + " " + nombre + ";";

    }

    public static String convertirPrimero(String nombre) {
        String aux = nombre.substring(0, 1).toUpperCase();
        return aux + nombre.substring(1, nombre.length());
    }

    public static String getTipo(String t) {
        if (t.equalsIgnoreCase("INT")) {
            return "Integer";
        } else if (t.equalsIgnoreCase("DATE")||t.equalsIgnoreCase("DATETIME")) {
            return "Date";
        } else if (t.equalsIgnoreCase("VARCHAR")) {
            return "String";
        } else if (t.equalsIgnoreCase("TIME")) {
            return "Date";
        } else if (t.equalsIgnoreCase("CHAR")) {
            return "char";

        } else if (t.equalsIgnoreCase("DOUBLE")) {
            return "Double";
        } else if (t.equalsIgnoreCase("BOOL")) {
            return "Boolean";
        } else {
            return "";
        }

    }

    public static String obtenerGet(String atributo) {
      
        String[] datos = atributo.split(" ");
        String nom = datos[6].substring(0, datos[6].length() - 1);
        String tipo = datos[5];
        String aux = Constantes.TAB_ATRIBUTO + "public "+tipo+" get"+Constantes.convertirPrimero(nom)+"() {"+"\n";
        aux+= Constantes.TAB_ATRIBUTO + Constantes.TAB_ATRIBUTO +"return "+nom+";"+"\n";
        aux+=Constantes.TAB_ATRIBUTO+"}";
        return aux;
    }
    public static String obtenerSet(String atributo) {
      /*
        public void setAlmacenId(Integer almacenId) {
        this.almacenId = almacenId;
    }
        */
        String[] datos = atributo.split(" ");
        String nom = datos[6].substring(0, datos[6].length() - 1);
        String tipo = datos[5];
        String aux = Constantes.TAB_ATRIBUTO + "public void set"+Constantes.convertirPrimero(nom)+"("+tipo+" "+nom+") {"+"\n";
        aux+= Constantes.TAB_ATRIBUTO + Constantes.TAB_ATRIBUTO +"this."+nom+" = "+nom+";\n";
        aux+=Constantes.TAB_ATRIBUTO+"}";
        return aux;
    }

}
