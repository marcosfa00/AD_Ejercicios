

package pfungos;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;


public class Methods2 {
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
    
    public ArrayList<Detectados> readSerial() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Detectados>list = new ArrayList<>();
         ObjectInputStream reader = new ObjectInputStream(new FileInputStream(Pfungos.path));
        Detectados x ;
        System.out.println("FICHERO SERIALIZADO CON LA INFORMACION DE LOS DETECTADOS");
        while((x =(Detectados) reader.readObject()) != null ){
            list.add(x);
            System.out.println("num: " + x.getNumero() + " coda " + x.getCodarea()+ " id " + x.getCodfungo() + " superficieAfectada" + x.getSuperficie());
            
        }
        reader.close();
        
        return list;

    }
    
    public void showAreas(int coda) throws SQLException{
          String selectQuery ="SELECT * FROM areas WHERE coda =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, coda);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            System.out.print(" "+result.getInt("coda"));
            System.out.print(" "+result.getString("noma"));
            System.out.print(" "+result.getInt("hummedia"));
            System.out.print(" "+result.getInt("superficie"));
            System.out.println(" "+result.getInt("numerofungos"));
            
        }
        
    }
    
    public void showFungos(int id) throws SQLException{
        String selectQuery ="SELECT * FROM fungos WHERE id =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            System.out.print(" "+result.getInt("id"));
            System.out.print(" "+result.getString("nomf"));
            System.out.println(" "+result.getInt("humlimite"));
        
            
        }
    }
    
    public int getHummedia(int coda) throws SQLException{
        String selectQuery ="SELECT * FROM areas WHERE coda =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, coda);
        ResultSet result = statement.executeQuery();
        int humMedia =0;
        while(result.next()){
            humMedia = result.getInt("hummedia");
        }
     return humMedia;
    }
    
    public int getSuperficie(int coda) throws SQLException{
        String selectQuery ="SELECT * FROM areas WHERE coda =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, coda);
        ResultSet result = statement.executeQuery();
        int superficie =0;
        while(result.next()){
            superficie = result.getInt("superficie");
        }
     return superficie;
    }
    
    
     public int getHumlimite(int id) throws SQLException{
        String selectQuery ="SELECT * FROM fungos WHERE id =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        int humMedia =0;
        while(result.next()){
            humMedia = result.getInt("humlimite");
        }
     return humMedia;
    }
    
    
    
    public int getNFungos(int coda) throws SQLException{
        String selectQuery ="SELECT * FROM areas WHERE coda =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setInt(1, coda);
        ResultSet result = statement.executeQuery();
        int numeroFungos =0;
        while(result.next()){
            numeroFungos = result.getInt("numerofungos");
            
        }
        statement.close();
       
        return numeroFungos;
        
    }
    
    public void updateNfungos(int coda) throws SQLException{
        String  updateQuery = "UPDATE areas SET numerofungos=numerofungos+1 WHERE coda = ?";
        PreparedStatement statement = Conexion().prepareStatement(updateQuery);
        statement.setInt(1, coda);
        statement.executeUpdate();
        statement.close();
    }
    
    
    public double porcentaxeDanos(int coda,int hummedia, int humlimite, double superficieAfecatda) throws SQLException{
        //superficieafectada*100/superficie
         double operacion =0;
        if (hummedia > humlimite) {
              operacion = superficieAfecatda * 100 / this.getSuperficie(coda);
        }else{
            System.out.println("la hummedia esta correctamente");
        }
          
        return operacion;
        
    }
    
    
  public void writeDelimitado(int coda, String noma, String nomf, double superficieDanada, double porcentaxeDanos) throws IOException {
    PrintWriter pwriter = new PrintWriter(new FileWriter(Pfungos.pathFinal, true));
    pwriter.println(coda + "\t" + noma + "\t" + nomf + "\t" + superficieDanada + "\t" + porcentaxeDanos);
    pwriter.close();
}

    
    
    public void readDelimitado() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Pfungos.pathFinal));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\t");
            for (String value : data) {
                System.out.print(value + "\t");
            }
            System.out.println(); // Salto de línea después de mostrar una línea completa
        }

        reader.close();
    }
    
}
