

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
    
    public void readSerial(String path) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
        Detectados x ;
        while((x =(Detectados) reader.readObject()) != null ){
            System.out.println("numero: "+x.numero+" codarea: "+x.codarea+" codfungo: "+x.codfungo+" superficieAfectada: "+x.superficie);
           readArea(x.codarea);
        }
        
        
    }
    
    public void readArea(int codArea) throws SQLException{
        String selectQuery = "SELECT * FROM areas WHERE coda = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1,codArea);
        ResultSet result = statement.executeQuery();
        float superficie=0;
        float numeroFungos =0;
        float humedia=0;
        String nameArea ="";
        while(result.next()){
           superficie = result.getFloat("superficie");
           humedia = result.getFloat("hummedia");
           numeroFungos = result.getFloat("numerofungos");
           nameArea = result.getString("noma");
           
            System.out.println(superficie + "  "+humedia+"  "+numeroFungos+"  "+nameArea);
            
            
            
          
        }
        
        
        
    }
    
}
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

