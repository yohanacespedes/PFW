/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw.parcial.Negocio;

/**
 *
 * @author Rafael
 */
public class Linea {
    private int numero;
    private String texto;
    public Linea() {
    }

    public Linea(int numero, String texto) {
        this.numero = numero;
        this.texto = texto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return super.toString() + "["+numero+" , "+texto+" ]"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
