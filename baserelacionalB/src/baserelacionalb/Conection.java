

package baserelacionalb;
import java.sql.*;


public class Conection {
     /**
     * Declaramos los atributos
     */
    private String driver ="jdbc:postgresql:";
    private String host = "//localhost:";
    private String puerto = "5434";//Recordar que pro defecto en clase será el 5432
    private String sid = "postgres";
    private String usuario="postgres";
    private String password = "postgres";
    private String url = driver + host + puerto + "/" + sid;
    
    
    /**
     * Este Metodo devuelve la conexión con la base de datos
     * @return Objecto de Tipo Connection
     */
    public  Connection Conexion() throws SQLException{
        Connection conn = DriverManager.getConnection(url,usuario,password);
        return conn;
        
    }
    
    public void listarConResult() throws SQLException{
        String executeQuery = "Select * from produtos";
        //Creamos un objeto con ResultSet de tipo scrolaable
        //Esto no se puede hacer con preparedStatement
        Statement statement = Conexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet result =statement.executeQuery(executeQuery);
        result.moveToCurrentRow();
        while (result.next()) {
            String codigo = result.getString("codigo");
            String descripcion = result.getString("descricion");
            double precio = result.getDouble("prezo");
            Date fecha = result.getDate("datac");

            System.out.println("Código: " + codigo + ", Descripción: " + descripcion + ", Precio: " + precio + ", Fecha: " + fecha);
        }
             result.close();
        statement.close();
        Conexion().close();
        
    }
    
    public void actualizarConResult(String codigo, double prezo) throws SQLException{
        String updateQuery = "SELECT * FROM PRODUTOS ";
        Statement statement = Conexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet result = statement.executeQuery(updateQuery);
        //Ahora sewrá donde realmente emplearemos esta forma nueva de busqueda con ResultSet
        result.beforeFirst();//te pone antes del primero, proque si no el while salta el primero
        while(result.next()){
            if (result.getString("codigo").equals(codigo)) {
                result.updateInt("prezo", (int)prezo);
            result.updateRow();
            }
            
            
        }
         result.close();
        statement.close();
        Conexion().close();
        listarConResult();
        
    }
    
    
    
    
    

}
