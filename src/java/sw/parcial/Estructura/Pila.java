/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Estructura;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Pila implements Serializable{

    public int indice = -1;
    public ArrayList<Object> elementos;

    public Pila() {
        elementos = new ArrayList<>();
    }

    public void adicionar(Object o) {
        indice++;
        elementos.add(o);
    }

    public Object getObjeto() {
        Object o = null;
        if (elementos.size() > 0) {
            o = elementos.get(indice);
            elementos.remove(indice);
            indice--;
        }
        return o;
    }
    public Object consultaTope(){
        Object o = null;
        if (elementos.size() > 0) {
            o = elementos.get(indice);
         //   elementos.remove(indice);
          //  indice--;
        }
        return o;
    }
    public boolean esVacia(){
        return elementos.size()<=0;
    }

}
