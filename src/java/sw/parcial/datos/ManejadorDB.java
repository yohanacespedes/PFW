/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.datos;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import java.util.logging.Level;
import java.util.logging.Logger;
import sw.parcial.DAO.Grupo;
import sw.parcial.util.UtilDB;

/**
 *
 * @author Rafael
 */
public class ManejadorDB {

    static private ManejadorDB manejador = null;

    public ManejadorDB() {
    }

    static public ManejadorDB getManejador() {

        if (manejador == null) {
            manejador = new ManejadorDB();
        }
        return manejador;
    }

    public static int guardar(Object obj) {
        int res = -1;
        try {
            Connection connection = DBHelper.getConnection();
            res = DBHelper.guardar(connection, UtilDB.getConsultaInsertar(obj));
            connection.close();
            return res;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static int guardarID(Object obj) {
        int res = -1;
        try {
            Connection connection = DBHelper.getConnection();
            res = DBHelper.guardar(connection, UtilDB.getConsultaInsertarID(obj));
            connection.close();
            return res;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public void modificar() {

    }

    public ArrayList<Object> getLista(Object obj) {
        ArrayList<Object> lista = new ArrayList<>();
        try {
            Connection connection = DBHelper.getConnection();
            String consulta = UtilDB.getConsultaTodos(obj);
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            Object pr = obj;
            while (rs.next()) {
                pr = getData(rs, pr);

                lista.add(pr);

            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public ArrayList<Object> getListaProductosJoin(Object obj, int idalmacen) {
        ArrayList<Object> lista = new ArrayList<>();
        try {
            Connection connection = DBHelper.getConnection();
            String consulta = UtilDB.getTodosProductosIngreso(idalmacen);
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            Object pr = obj;
            while (rs.next()) {
                pr = getData(rs, pr);

                lista.add(pr);

            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public ArrayList<Object> getListaProductosStock(Object obj, String descripcion, int idalmacen) {
        ArrayList<Object> lista = new ArrayList<>();
        try {
            Connection connection = DBHelper.getConnection();
            String consulta;
            if (!descripcion.equals("")) {
                consulta = UtilDB.getTodosStockDescripcion(descripcion, idalmacen);
            } else {
                consulta = UtilDB.getTodosProductosAlmacen(idalmacen);
            }
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            Object pr = obj;
            while (rs.next()) {
                pr = getData(rs, pr);

                lista.add(pr);

            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public ArrayList<Object> getListaProductosIngreso(Object obj, String descripcion, int idalmacen, int tipo) {
        ArrayList<Object> lista = new ArrayList<>();
        try {
            Connection connection = DBHelper.getConnection();
            String consulta;
            if (tipo == 0) /// ingreso
            {
                consulta = UtilDB.getTodosIngresoDescripcion(descripcion, idalmacen);
            } else {
                consulta = UtilDB.getTodosEgresoDescripcion(descripcion, idalmacen);
            }
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            Object pr = obj;
            while (rs.next()) {
                pr = getData(rs, pr);

                lista.add(pr);

            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public void ejecutarProcedimientoDetalle(String nombre, HashMap<String, Object> parametros) {
        try {
            Connection connection = DBHelper.getConnection();
            DBHelper.executeProcedure(connection, nombre, parametros);
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object getObjectId(Object obj) {
        try {
            String consulta = UtilDB.getObjectoID(obj);
            Connection connection = DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            while (rs.next()) {
                obj = getData(rs, obj);

            }
            rs.close();
            connection.close();
            return obj;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object getUltimoObject(Object obj) {
        try {
            String consulta = UtilDB.getConsultaUltimo(obj);
            Connection connection = DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            while (rs.next()) {
                obj = getData(rs, obj);

            }
            rs.close();
            connection.close();
            return obj;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object getObjectCampo(Object obj, String campo, Object data) {
        try {
            String consulta = UtilDB.getObjectCampo(obj, campo, data);
            Connection connection = DBHelper.getConnection();
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            while (rs.next()) {
                obj = getData(rs, obj);
            }
            rs.close();
            connection.close();
            return obj;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Object> getListaCampo(Object obj, String campo, Object data) {
        ArrayList<Object> lista = new ArrayList<>();
        try {
            Connection connection = DBHelper.getConnection();
            String consulta = UtilDB.getObjectCampo(obj, campo, data);
            ResultSet rs = DBHelper.executeQuery(connection, consulta, null);
            Object pr = obj;
            while (rs.next()) {
                pr = getData(rs, pr);

                lista.add(pr);

            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    private Object getData(ResultSet rs, Object obj) {
        try {
            Class clase = obj.getClass();
            Object o = clase.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field f : fields) {
                f.setAccessible(true);
                if (!UtilDB.esAuxiliar(f.getName())) {
                    if (f.getType().getSimpleName().equals("String")) {
                        try {

                            try {
                                f.set(o, rs.getString(f.getName()));
                            } catch (IllegalArgumentException ex) {
                                Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if (f.getType().getSimpleName().equals("Date")) {
                            try {

                                try {
                                    String nombre = f.getName();

                                    Date valor = rs.getDate(nombre);
                                    f.set(o, valor);
                                } catch (IllegalArgumentException ex) {
                                    Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            if (f.getType().getSimpleName().equals("Integer")) {
                                try {
                                    Integer tipo = new Integer(0);
                                    try {
                                        String nombre = f.getName();
                                        int valor = rs.getInt(f.getName());
                                        f.set(o, valor);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {

                                if (f.getType().getSimpleName().equals("Time")) {
                                    try {
                                        Integer tipo = new Integer(0);
                                        try {
                                            String nombre = f.getName();
                                            Time valor = rs.getTime(nombre);
                                            f.set(o, valor);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }

                        }

                    }

                }
                f.setAccessible(false);

            }

            return o;
        } catch (InstantiationException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ManejadorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ManejadorDB m = new ManejadorDB();
   
//        EIngreso ee =  (EIngreso) ei.get(0);
        Grupo p = new Grupo();
        System.out.println(m.getLista(p).toString());


     //   System.out.println(m.esNumero(" 232"));
        //  System.out.println(m.esNumero("232323"));
        //    System.out.println(m.getObjectCampo(u, "login", u.getLogin()));
    }
}
