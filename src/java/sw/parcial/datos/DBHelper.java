/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw.parcial.datos;

/**
 *
 * @author Rafael
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ConstructoraSalgado
 */
public class DBHelper {
    /**
     * Retorna una nueva instancia de acceso a la base de datos
     *
     * @return Connection
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    
    public synchronized static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/bdcomponentes", "root", "h0l4mund0");
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
    }
   

    /**
     * Ejecuta un comando(INSERT,UPDATE,DELETE) en la base de datos
     *
     * @param connection Connection sobre el cual se ejecutara el comando
     * @param query Comando a ejecutar en formato SQL
     * @param parametros Parametros del query si los hay o null si no los hay
     * @return El id asignado en la base de datos si el comando es INSERT o la
     * cantidad de registros afectados en los demas comandos
     * @throws SQLException
     */
    public synchronized static int guardar(Connection connection, String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        return ps.executeUpdate();
    }

    public synchronized static int executeCommand(Connection connection, String query, HashMap<String, Object> parametros) throws SQLException {
        int id = -1;
        if (connection != null && !connection.isClosed()) {
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            if (parametros != null) {
                for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                    ps.setObject(Integer.parseInt(parametro.getKey()), parametro.getValue());
                }
            }
            id = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int _id = rs.getInt(1);
                if (_id > 0) {
                    // ## Es una insercion
                    id = _id;
                }
            }
            rs.close();
        }
        return id;
    }

    /**
     * Ejecuta una consulta a la base de datos
     *
     * @param connection Connection sobre la cual se ejecutara la consulta
     * @param query Consulta a ejecutar
     * @param parametros Parametros de query si los hay o null si no los hay
     * @return ResulSet con el resultado de la consulta
     * @throws SQLException
     */
    @SuppressWarnings("NestedSynchronizedStatement")
    public synchronized static ResultSet executeQuery(Connection connection, String query, HashMap<String, Object> parametros) throws SQLException {
        ResultSet resultSet = null;
       synchronized(connection){
        if (connection != null && !connection.isClosed()) {
            PreparedStatement ps = connection.prepareStatement(query);
            if (parametros != null) {
                for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                    ps.setObject(Integer.parseInt(parametro.getKey()), parametro.getValue());
                }
            }
            resultSet = ps.executeQuery();
        }
       }
        return resultSet;
    }

    /**
     * Ejecuta un procedimiento en la base de datos
     *
     * @param connection Connection sobre la cual se ejecutara el procedimiento
     * @param procedureName Nombre del procedimiento a ejecutar
     * @param parametros Parametros del procedimiento o nul si no los hay
     * @return ResulSet con el resultado del procedimiento
     * @throws SQLException
     */
    public synchronized static ResultSet executeProcedure(Connection connection, String procedureName, HashMap<String, Object> parametros) throws SQLException {
        ResultSet resultSet = null;
        String storedProcedure = "CALL " + procedureName + "(";

        if (connection != null && !connection.isClosed()) {
            if (parametros != null) {
                for (int i = 0; i < parametros.size(); i++) {
                    storedProcedure += ((i + 1) == parametros.size()) ? "?" : "?,";
                }
            }
            CallableStatement cs = connection.prepareCall(storedProcedure + ")");
            if (parametros != null) {
                for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                    cs.setObject(parametro.getKey(), parametro.getValue());
                }
            }
            resultSet = cs.executeQuery();
        }

        return resultSet;
    }

    /**
     * Ejecuta una consulta a la base de datos paginando los resultados
     *
     * @param connection Connection sobre la cual se ejecutara la consulta
     * @param query Consulta a ejecutar
     * @param parametros Parametros de query si los hay o null si no los hay
     * @param offset Indice a partir del cual se obtendran los resultados
     * @param rowCount Cantidad de filas a retornar
     * @return ResultSet con el resultado de la consulta
     * @throws SQLException
     */
    public synchronized static ResultSet executeQuery(Connection connection, String query, HashMap<String, Object> parametros,
            int offset, int rowCount) throws SQLException {

        ResultSet resultSet = null;
        query += " LIMIT :offset , :rowCount";

        if (connection != null && !connection.isClosed()) {
            PreparedStatement ps = connection.prepareStatement(query);
            if (parametros != null) {
                parametros.put("offset", offset);
                parametros.put("rowCount", rowCount);
                for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                    ps.setObject(Integer.parseInt(parametro.getKey()), parametro.getValue());
                }
            }
            resultSet = ps.executeQuery();
        }

        return resultSet;

    }

    public synchronized static List getLista(Object T, Class TT) throws ClassNotFoundException, SQLException {
        String nombre = TT.getSimpleName();
        nombre = nombre.substring(0, nombre.length() - 3);
        Connection connection = DBHelper.getConnection();
        List lista = new ArrayList();
        String consulta = "SELECT * FROM " + nombre;
        ResultSet rs = DBHelper.executeQuery(connection, consulta, null);

        while (rs.next()) {
            getDato(T, rs);
            lista.add(T);
        }
        rs.close();

        return lista;
    }
    
    
    public synchronized static List getListaObject(Object T, Class TT,String pregunta) throws ClassNotFoundException, SQLException {
        String nombre = TT.getSimpleName();
        nombre = nombre.substring(0, nombre.length() - 3);
        Connection connection = DBHelper.getConnection();
        List lista = new ArrayList();
        String consulta = "SELECT * FROM " + nombre+ " Where ( "+ pregunta+" );";
        ResultSet rs = DBHelper.executeQuery(connection, consulta, null);

        while (rs.next()) {
            getDato(T, rs);
            lista.add(T);
        }
        rs.close();

        return lista;
    }
   
    public synchronized static void getDato(Object obj, ResultSet rs) {
        try {
            Class T = obj.getClass();
            Field properties[] = obj.getClass().getDeclaredFields();
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                boolean accesible = field.isAccessible();
                field.setAccessible(true);
                String nombre = field.getName();
                Object tipo = field.getType();
                String tp = field.getType().toString();              
               
                if (tp.equals("int")||tp.equals("class java.lang.Integer")) {
                    Integer value = new Integer(rs.getInt(nombre));
                    field.set(obj, value.intValue());
                } else {
                    if (tp.equals("class java.lang.String")) {
                        @SuppressWarnings("RedundantStringConstructorCall")
                        String value = new String(rs.getString(nombre));
                          field.set(obj, value);
                    } else {
                          Character value = new Character(rs.getString(nombre).charAt(0));
                          field.set(obj, value);
                    }
                    field.setAccessible(accesible);
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }
    
}
