
package pyMultas_SQLite3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author saaimons
 */
public class SQL_Conn {

    private Connection conexion = null;
    private Statement query = null;
    private String db = "sample.db";
    public SQL_Conn() {
    }

    private Connection conn() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:"+this.db);
        return conexion;
    }

    private Statement query(Connection conexion) throws SQLException {
        query = conexion.createStatement();
        query.setQueryTimeout(30);  // set timeout to 30 sec.
        return query;
    }

    
    /**
     * 
     * Este método es utilizado cuando se precisa realizar una operación 
     * que no tenga un resultado como devolución
     * 
     * UPDATE
     * CREATE
     * INSERT
     * DELETE
     * 
     * Ejemplos de query:
     * "drop table if exists person"
     * "create table person (id integer, name string)"
     * "insert into person values(1, 'leo')"
     * "insert into person values(2, 'yui')"
     * 
     * @param query
     * 
     * @return 
     */
    public boolean enviar_1(String query) {
        try {
            query(conn()).executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al enviar: "+query+" -> "+ex.toString());
            return false;
        }
    }
    
    /**
     * 
     * Método utilizado cuando se espera un resultado.
     * 
     * @param query
     * @return ResultSet
     */
    public ResultSet enviar_2(String query){
        ResultSet rs = null;
        try{
           rs =  query(conn()).executeQuery(query);
        }catch(SQLException ex){
            System.out.println("Error al eviar: "+query+" -> "+ex.toString());      
        }
        return rs;
    }
    
    public void cerrarConexion(){
        try
          {
            if(conn() != null) {
                conn().close();
            }
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
    }
}
