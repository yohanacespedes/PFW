/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.util;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.text.SimpleDateFormat;

/**
 *
 * @author Rafael
 */
public class UtilDB {

    public static SimpleDateFormat FormatoFecha;
    public static SimpleDateFormat FormatoHora = new SimpleDateFormat("HH:mm:ss");

    public static boolean esAuxiliar(String campo) {

        return campo.substring(0, 1).equals("_");
    }

    public static final String getConsultaInsertar(Object obj) {
        Class objeto = obj.getClass();

        String consulta = "INSERT INTO " + objeto.getSimpleName() + " ( ";
        String valores = " VALUES ( ";
        Field[] fields = objeto.getDeclaredFields();

        for (Field f : fields) {
            if (!UtilDB.isPrimaria(f.getName()) && !UtilDB.esAuxiliar(f.getName())) {
                consulta = consulta + f.getName() + ", ";

                if (f.getType().getSimpleName().equals("String") || f.getType().getSimpleName().equals("Date")|| f.getType().getSimpleName().equals("Time")) {
                    if (f.getType().getSimpleName().equals("Date")) {
                        try {
                            FormatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                          
                            Date fecha = new Date();
                            String fs = FormatoFecha.format(fecha);
                          
                            if (f.getName().equalsIgnoreCase("fechaI") || f.getName().equalsIgnoreCase("fecha")) {
                                if (f.get(obj) != null) {
                                    valores = valores + "'" + fs + "'" + ", ";
                                } else {
                                    valores = valores + "'" + "'" + ", ";
                                }
                            } 

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        if (f.getType().getSimpleName().equals("Time")) {
                            try {
                                  valores = valores + "'" + f.get(obj).toString() + "'" + ", ";
                            } catch (IllegalArgumentException ex) {
                                Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            

                        } else {
                            try {

                                if (f.get(obj) != null) {
                                    valores = valores + "'" + f.get(obj).toString() + "'" + ", ";
                                } else {
                                    valores = valores + "'" + "'" + ", ";
                                }
                            } catch (IllegalArgumentException | IllegalAccessException ex) {
                                Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }

                } else {
                    try {
                        if (f.get(obj) != null) {
                            valores = valores + f.get(obj).toString() + ", ";
                        } else {
                            valores = valores + -1 + ", ";
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }

        valores = valores.substring(0, valores.length() - 2);
        valores = valores + ")";
        consulta = consulta.substring(0, consulta.length() - 2);
        consulta = consulta + " )" + valores + ";";

        return consulta;
    }

    public static final String getConsultaInsertarID(Object obj) {
        Class objeto = obj.getClass();

        String consulta = "INSERT INTO " + objeto.getSimpleName() + " ( ";
        String valores = " VALUES ( ";
        Field[] fields = objeto.getDeclaredFields();

        for (Field f : fields) {
            if (!UtilDB.esAuxiliar(f.getName())) {
                consulta = consulta + f.getName() + ", ";

                if (f.getType().getSimpleName().equals("String") || f.getType().getSimpleName().equals("Date")||f.getType().getSimpleName().equals("Time") ) {
                    try {

                        if (f.get(obj) != null) {
                            valores = valores + "'" + f.get(obj).toString() + "'" + ", ";
                        } else {
                            valores = valores + "'" + "'" + ", ";
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (f.get(obj) != null) {
                            valores = valores + f.get(obj).toString() + ", ";
                        } else {
                            valores = valores + -1 + ", ";
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }

        valores = valores.substring(0, valores.length() - 2);
        valores = valores + ")";
        consulta = consulta.substring(0, consulta.length() - 2);
        consulta = consulta + " )" + valores + ";";

        return consulta;
    }

    public static void main(String[] args) {
     

        //  System.out.println(UtilDB.getConsultaTodos(po));
        //   System.out.println(UtilDB.getObjectoID(e));
        //   System.out.println(UtilDB.getConsultaUpdate(e));
        //  System.out.println(UtilDB.getObjectCampo(u, "login",u.getLogin()));
    }

    public static boolean isPrimaria(String campo) {
        if (campo.length() <= 3) {
            return false;
        }
        String valor = campo.substring(campo.length() - 3, campo.length());
        if (valor.substring(valor.length() - 2, valor.length()).equalsIgnoreCase("Id")) {
            if (valor.substring(0, valor.length() - 2).equals("_")) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }

    }

    public static final String getConsultaTodos(Object obj) {
        Class objeto = obj.getClass();
        String consulta = "SELECT * FROM " + objeto.getSimpleName() + ";";
        return consulta;
    }

    public static final String getTodosProductosAlmacen(int idalmacen) {
        String consulta = "SELECT p.productoID,p.descripcion as nombre,c.descripcion,  i.actual FROM producto p,categoria c inner join detaproalma i"
                + " where p.productoID = i.producto_id  and i.almacen_id = " + idalmacen + " and c.categoriaID = p.categoria_id;";
        return consulta;
    }

    public static final String getTodosProductosIngreso(int idalmacen) {
        //String consulta = "SELECT p.productoID,p.categoria_id,p.umedida_id ,p.descripcion,p.observacion FROM producto p inner join detaproing i where p.productoID = i.producto_id;";
        String consulta = "SELECT p.productoID,p.categoria_id,p.umedida_id ,p.descripcion,p.observacion FROM  detaproing i  inner join  producto p ON p.productoID = i.producto_id INNER JOIN detaproalma de ON p.productoID = de.producto_id AND almacen_id = "+idalmacen;
        
        return consulta;
    }

    public static final String getTodosStockDescripcion(String descripcion, int idalmacen) {
        String consulta = "SELECT p.productoID,p.descripcion as nombre,c.descripcion,  i.actual FROM producto p,categoria c inner join detaproalma i"
                + " where p.productoID = i.producto_id  and i.almacen_id = " + idalmacen + " and p.descripcion like '%" + descripcion + "%' and c.categoriaID = p.categoria_id;";
        return consulta;
    }

    public static final String getTodosIngresoDescripcion(String descripcion, int idalmacen) {
        String consulta = "SELECT i.ingresoID, i.fechaI, i.horaI, p.descripcion,u.abreviatura, d.cantidad,pr.nombre, pr.apellidos FROM ingreso i INNER JOIN detaproing d\n"
                + " ON i.ingresoID = d.ingreso_id INNER JOIN producto p ON d.producto_id = p.productoID   INNER JOIN umedida u ON p.umedida_id = u.umedidaID"
                + " INNER JOIN proveedor pr ON pr.proveedorID = i.proveedor_id"
                + " AND p.descripcion like '%" + descripcion + "%' AND i.almacen_id = " + idalmacen + ";";
        return consulta;
    }

    public static final String getTodosEgresoDescripcion(String descripcion, int idalmacen) {
        String consulta = "SELECT i.egresoID, i.fechaI, i.horaI,pr.descripcion,um.abreviatura,o.nombre,o.apellidos ,n.nombre AS nnombre,it.nombre AS inombre, d.cantidad FROM egreso i INNER JOIN nivel n "
                + "ON i.nivel_id =n.nivelID INNER JOIN detaprosa d ON d.egreso_id = i.egresoID INNER JOIN item it "
                + "ON d.item_id = it.itemID INNER JOIN obrero o ON o.obreroID = i.obrero_id INNER JOIN  producto pr "
                + "ON pr.productoID = d.producto_id INNER JOIN umedida um ON  um.umedidaID = pr.umedida_id "
                + "AND pr.descripcion like '%" + descripcion + "%' AND i.almacen_id = " + idalmacen + ";";
        return consulta;
    }

    public static final String getConsultaUltimo(Object obj) {
        Class objeto = obj.getClass();
        String id = obj.getClass().getSimpleName() + "ID";
        //   "SELECT * FROM umedida p order by umedidaID desc limit 1"
        String consulta = "SELECT * FROM " + objeto.getSimpleName() + " ORDER BY " + id + " desc limit 1;";
        return consulta;
    }

    public static final String getObjectoID(Object obj) {
        try {
            String tabla = obj.getClass().getSimpleName();
            Class objeto = obj.getClass();
            String consulta = "SELECT * FROM " + tabla + " WHERE " + tabla + "Id = ";
            Field[] fields = objeto.getDeclaredFields();
            Field f = fields[0];
            String valores = "";

            if (f.get(obj) != null) {
                valores = valores + f.get(obj).toString();
            } else {
                valores = valores + -1;
            }
            consulta = consulta + valores + ";";
            return consulta;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static final String getConsultaUpdate(Object obj) {
        Class objeto = obj.getClass();
        String condicion = " WHERE ";
        String consulta = "UPDATE " + objeto.getSimpleName() + " SET ";
        String valores = "";
        Field[] fields = objeto.getDeclaredFields();

        for (Field f : fields) {
            if (!UtilDB.isPrimaria(f.getName()) && !UtilDB.esAuxiliar(f.getName())) {
                consulta = consulta + f.getName() + " = ";
                valores = "";
                if (f.getType().getSimpleName().equals("String") || f.getType().getSimpleName().equals("Date") ||f.getType().getSimpleName().equals("Time") )  {
                    try {
                        if (f.get(obj) != null) {
                            valores = valores + "'" + f.get(obj).toString() + "'" + ", ";
                        } else {
                            valores = valores + "'" + "'" + ", ";
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (f.get(obj) != null) {
                            valores = valores + f.get(obj).toString() + ", ";
                        } else {
                            valores = valores + -1 + ", ";
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                consulta = consulta + valores;
            } else {
                try {
                    condicion = condicion + f.getName() + " = " + f.get(obj).toString();

                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        consulta = consulta.substring(0, consulta.length() - 2);
        consulta = consulta + condicion + ";";

        return consulta;
    }

    public static final String getObjectCampo(Object obj, String campo, Object data) {
        try {
            String tabla = obj.getClass().getSimpleName();
            Class objeto = obj.getClass();
            String consulta = "SELECT * FROM " + tabla + " WHERE " + campo + " = ";

            Field[] fields = objeto.getDeclaredFields();
            Field f = fields[0];
            String valores = "";

            if (data.getClass().getSimpleName().equals("String") || data.getClass().getSimpleName().equals("Date")||f.getType().getSimpleName().equals("Time") ) {
                consulta = consulta + "'" + data + "'";
            } else {
                if (data.getClass().getSimpleName().equals("Integer")) {
                    consulta = consulta + data;
                }

            }

            consulta = consulta + valores + ";";
            return consulta;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
