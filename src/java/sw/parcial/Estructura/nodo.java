/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw.parcial.Estructura;

/**
 *
 * @author Rafael
 */
public class nodo extends sw.parcial.Estructura.Document{
    private Object elemento;
    public nodo(String name, String size, String type) {
        super(name, size, type);
    }
    public nodo(String name, String size, String type, Object ele) {
        super(name, size, type);
        this.elemento = ele;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElemento() {
        return elemento;
    }
    
    
}
