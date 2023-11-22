

package baserelacionala;
import java.sql.*; //importamos manualmente Java SQL

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
    public void insertProduct(String codigo,String desc,int precio,Date fecha) throws SQLException{
        String insertQuery = "INSERT INTO produtos (codigo, descricion, prezo, datac) VALUES (?, ?, ?, ?)";
        PreparedStatement statemens = Conexion().prepareStatement(insertQuery);
        //Aquí indicamos la posición y el valor a insertar empieza en 0
        statemens.setString(1, codigo);
        statemens.setString(2, desc);
        statemens.setInt(3, precio);
        statemens.setDate(4, fecha);
        
        if (statemens.executeUpdate()>0) {
            System.out.println("Insert exitoso");
        }else{
            System.out.println("Something went wrong");
        }
        Conexion().close();
    }
    
    public void listaProductos(String table) throws SQLException{
        String listQuery = "select * from " + table;
        PreparedStatement statements = Conexion().prepareStatement(listQuery);//recogemos en el statements la consulta SQL
        //Para obtener el resultado debemos declarar un ResultSet
        ResultSet result = statements.executeQuery();
        // Imprimimos los resultados por consola
        System.out.println("Productos:");
        System.out.println(" Código | Descripción | Precio | Fecha");
        //debemos hacer un bucle puesto que no sabemos el fin del archivo
        while(result.next()){
            String codigo = result.getString("codigo");
            String desc= result.getString("descricion");
            double prezo = result.getDouble("prezo");
            Date fecha = result.getDate("datac");
            //mostramos por consola la linea que hemos obtenido
            System.out.println(" " + codigo + " | " + desc + " | " + prezo + " | " + fecha);
        }
        
        //ahora al igual que haciemos con los archivos debemos cerrar la lectura
        result.close();
        statements.close();
        Conexion().close();
        
    }
    
    public void listaProductosPorCodigo(String codigo) throws SQLException{
        
        String listQuery = "select * from produtos where codigo = ?";
        PreparedStatement statement = Conexion().prepareStatement(listQuery);
        statement.setString(1, codigo);
        //declaramos el resultSEt
        ResultSet result = statement.executeQuery();
        System.out.println("Producto: ");
        System.out.println(" Código | Descripción | Precio | Fecha");
        
           while(result.next()){
            String desc= result.getString("descricion");
            double prezo = result.getDouble("prezo");
            Date fecha = result.getDate("datac");
            //mostramos por consola la linea que hemos obtenido
            System.out.println(" " + codigo + " | " + desc + " | " + prezo + " | " + fecha);
           }
             result.close();
        statement.close();
        Conexion().close();
    }
    
    
    /**
     * Metodo que actualiza el porecio
     * @param codigo
     * @param prezo
     * @throws SQLException 
     */
    public void updateProduct(String codigo, double prezo) throws SQLException{
      String updateQuery = "UPDATE produtos SET prezo = ? WHERE codigo = ?";
      PreparedStatement statement = Conexion().prepareStatement(updateQuery);
      //en este caso el index 1 indica el primer interrogante y el index 2 el segundo interrogante
      statement.setDouble(1, prezo);
      statement.setString(2, codigo);
      
      
        if (statement.executeUpdate() >0) {
            System.out.println("Los datos se han actualizado correctamente");
        }else{
            System.out.println("Parece que hubo un error al actyualizar los datos");
        }
        statement.close();
        Conexion().close();
      
    }
    
    public void eliminarProducto(String codigo) throws SQLException{
        String deleteQuery = "DELETE from produtos where codigo = ?";
        PreparedStatement statement = Conexion().prepareStatement(deleteQuery);
        statement.setString(1,"03");
        if (statement.executeUpdate()>0) {
            System.out.println("Se ha eliminado correctamente");
        }else{
            System.out.println("Hubo un error al eliminar la fila");
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
