

package pfungos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;


public class Methods {
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
    
    public void readSerial(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
        Detectados x ;
        while((x =(Detectados) reader.readObject()) != null ){
            System.out.println("numero: "+x.numero+" codarea: "+x.codarea+" codfungo: "+x.codfungo+" superficieAfectada: "+x.superficie);
          
        }
        
        
    }
    
    public void readFungos(int codFungo) throws SQLException{
        String selectQuery = "SELECT * FROM fungos WHERE id = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1,codFungo);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            
        }
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
