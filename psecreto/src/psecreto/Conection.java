

package psecreto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;



public class Conection {
    
     /**
     * Declaramos los atributos para conectarnos a la bae SQL
     */
   static private String driver ="jdbc:postgresql:";
    static private String host = "//localhost:";
    static private String puerto = "5434";//Recordar que pro defecto en clase será el 5432
    static private String sid = "postgres";
    static private String usuario="postgres";
    static private String password = "postgres";
    static private String url = driver + host + puerto + "/" + sid;
    
    
    /**
     * Este Metodo devuelve la conexión con la base de datos
     * @return Objecto de Tipo Connection
     */
    public static Connection Conexion() throws SQLException{
        Connection conn = DriverManager.getConnection(url,usuario,password);
        return conn;
        
    } 
    
    public static void getReferencia( String path) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Clave1> list = Methods.readSerializado(path);//lee clave 1
        String selectQuery = "SELECT * FROM referencia";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        ResultSet result = statement.executeQuery();
       int num1 = 0;
       int num2 = 0;
       int sumaTotal =0;
      
       while(result.next()){
              num2 = getReferenciaC2(result.getString("c2"));
             // System.out.println("num Clave2: " + num2);
              
               String letra =result.getString("c1");
               for (int j = 0; j < list.size(); j++) {
                    if (letra.equals( list.get(j).getCla1())){
                   
                   num1 =list.get(j).getNum1();
                   //System.out.println("num clave1: " + num1);
                   
                //metodo que lea suma de los numeros y devuelva la letra, tras pasarle el numeor 1 y numero 2
                Methods.readDelimitado("./clavesuma.txt", num1, num2);
               }
           }
       }
        statement.close();
    }
    
    
    public static int getReferenciaC2(String id) throws SQLException{
        String selectQuery = "SELECT * FROM clave2 where cla2 = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
         int num2 =0;
        while(result.next()){
           num2 = result.getInt("num2");
        }
       
       result.close();
       statement.close();
       
       return num2;
        
    }
    
    
    
    
    
    
    
    

}
