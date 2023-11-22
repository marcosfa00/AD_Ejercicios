

package exa15brevep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;


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
    
    /**
     * Metodo que lee del fichero serializado Platoss
     * @param path ruta del fichero platoss
     * @return devuelve un arraylist de tipo Platos con todos los platos leidos
     * @throws FileNotFoundException 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public  ArrayList<Platos> readFihero(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(path));
        System.out.println("Los platos leidos son");
        ArrayList<Platos> platos = new ArrayList<>();
        Object obj;
        while((obj =objInput.readObject())!= null){
            if (obj instanceof Platos) {
                platos.add((Platos) obj);
            }
           
        }
        for (Platos plato : platos) {
            System.out.println(plato);
        }
        objInput.close();
        return platos;
    }
    
    public Boolean compruebaDB(ArrayList<Platos> lista) throws SQLException{
        String selectQuery = "SELECT * FROM composicion where codp = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        ResultSet result;   
        String codigo = "";
         String codc = "";
         int peso=0;
         int graxa =0;
         int graxa2 = 0;
         double grasa_parcial_p1=0;
         int suma1 =0;
         double grasa_parcial_p2 =0;
         int suma2 =0;
       
        System.out.println(" peso | codc |  nomec  | graxa | ");
        for (int i = 0; i < lista.size(); i++) {
           statement.setString(1, lista.get(i).getCodigop());
            result = statement.executeQuery();
            
            while(result.next()){
                codigo =result.getString("codp");
                codc =result.getString("codc");
                peso =(int) result.getInt("peso");
                System.out.print(peso+"   " );
                
                    
                  
                     /*
                      NOTA importante : 
                       a graxa correspondente a cada componente do plato calculase asi:
                        graxa_parcial= peso/100*graxa
 
                        a grasa total do plato e a suma das grasas parciais
 
                        graxa_total=graxa_total+graxa parcial
                     */
                     
                  if (codigo.contains("p1")) {
                      graxa += getNombre(codigo, codc);
                    suma1 +=  peso;
                    
                    
                      
                }else{
                     suma2 += peso;
                     graxa2 += getNombre(codigo, codc);
                      
                  }
                
            }
           
        }
        
        
        grasa_parcial_p1 = suma1/100*graxa;
        grasa_parcial_p2 = suma2/100*graxa2; 
        
        double totalg = grasa_parcial_p1;
        System.out.println("La grasa total del plato 1 es : " + totalg);
        
        double totalg2 = grasa_parcial_p2;
        System.out.println("La grasa total del plato 2 es : " + totalg2);
        
        
        
        
        
        
        
        return true;
    }
    
    
    
    public int getNombre(String codigo,String codc) throws SQLException{
        String nameQuery = ("SELECT * from componentes where codc = ?");
        PreparedStatement statement = Conexion().prepareStatement(nameQuery);
        int graxa =0;
        statement.setString(1,codc);
        ResultSet result = statement.executeQuery();
       
        while(result.next()){
            System.out.print(result.getString("codc")+ "  |  " );
            System.out.print(result.getString("nomec")+ "  |  ");
            graxa = result.getInt("graxa");
            System.out.print(graxa+ "  |  ");
            System.out.println("");
        } 
        return graxa;
        
}
    
    

}



