

package pinmobiliaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;

/**
 * 
 * @author marcosfa
 */
public class Methods {

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
        
    
    
    public   void readSerialiced(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        //Debemos leer el texto Serializado que esta en el archivo listap
        ObjectInputStream readSerial = new ObjectInputStream(new FileInputStream(path));
        //Creamos el objeto de tipo Listap
        Listap x ;
        
        //Leemos el fichero
        while((x=(Listap) readSerial.readObject()) != null){
            System.out.println(x.toString());
        }
        readSerial.close();
    }
    
    
    public  void getNumPisos(String nif) throws SQLException{
        String selectQuery = "SELECT * FROM pisos where nif = ? ";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1,nif);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Fin clase
}
