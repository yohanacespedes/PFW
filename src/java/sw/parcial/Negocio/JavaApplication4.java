/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Negocio;

import sw.parcial.Estructura.objecto;
import java.io.File;
import java.util.StringTokenizer;

/**
 *
 * @author Rafael
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      String valor = "<li> <a href=\"\">Submenu1</a></li>";
        StringTokenizer x = new StringTokenizer(valor);
        System.out.println(valor.indexOf(">"));
        String p = valor.substring(0, valor.indexOf(">"));
        String u = valor.substring(valor.indexOf(">"), valor.length());
        System.out.println(p+" adfasf " +u);
        
    }

}
