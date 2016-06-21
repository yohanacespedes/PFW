/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sw.parcial.datos;


import sw.parcial.Estructura.Mananger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Florentino Yampasi
 */
public class Archivos {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
        private static final String path = "D:\\Admin.rsf";

	/**
	 *
	 */
	public Archivos() {
		// TODO Auto-generated constructor stub
	}

	public static void guardarBD(Mananger baseDato){
	   try
	    {
	    	//crea un (flujo) archivo en memoria y se conecta
	        //lo que esta entre comillas es la direccion de donde se creara el archivo
	        //ojo la carpeta debe estar creada D:\\
	       FileOutputStream FlujoArchivo = new  FileOutputStream(path);
	       //*.bin  o  *.txt
	       //se crea un administrador de objetos
	       //para trabajar con el archivo
	       ObjectOutputStream FlujoObjeto = new ObjectOutputStream(FlujoArchivo);
	       //escribir el objeto B a disco
	       //B es nuestra Base de Datos
	       FlujoObjeto.writeObject(baseDato);
	       //cierro el flujo  de objeto
	       FlujoObjeto.close();
	       //cierro el flujo de archivo
	       FlujoArchivo.close();
	    }
	    catch( Exception e){}
	}

	public static Mananger recuperarBD(){
		Mananger baseDato= null;
	    try
	    {
	    	//crea un archivo y baja la informacion de la direccion enviada
	       FileInputStream FlujoArchivo = new FileInputStream(path);
	       //crear el administrador de objetos
	       //osea nuestro archivo va a ser de tipo Object
	       ObjectInputStream FlujoObjeto = new ObjectInputStream(FlujoArchivo);
	       //lea un objeto y usted transforme el tipo
	       //en este caso nuestro tipo es BaseDatos
	       baseDato = (Mananger)FlujoObjeto.readObject();
	       //cerrar administrador de Objeto
	       FlujoObjeto.close();
	       //cerrar administrador de Archivo
	       FlujoArchivo.close();
	    }
	    catch(Exception e){ }
	    return baseDato;
	}

	public boolean existeArchivo() {
		// TODO Auto-generated method stub
		return (new File(path).exists());
	}

	public static void crearDirectorio(String path,String nombreDir) {
		// TODO Auto-generated method stub
                String p = path+"\\"+nombreDir;
		if(!(new File(p)).exists())
			(new File(p)).mkdirs();
	}


}
