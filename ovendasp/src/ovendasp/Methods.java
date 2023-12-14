

package ovendasp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.*;
import java.time.temporal.TemporalQueries;

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
    
    public void mainEvent() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException{
        ArrayList<Vendas> lista = readSerial(Ovendasp.path);
        for (int i = 0; i < lista.size(); i++) {
            String codp = lista.get(i).getCodp();
            int cantv = lista.get(i).getCantv();
            int nv = lista.get(i).getNv();
            String des = lista.get(i).getDes();
            int finalPrice = getPrice(codp,cantv,des);
            
            updateStock(cantv, codp);
            String nomp = getNproducto(codp);
            
            writeDelimitado(nv, nomp, cantv, finalPrice);
        }
        reader(Ovendasp.pathD);
        
        
    }
    
    
    public  ArrayList<Vendas> readSerial(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
          ArrayList<Vendas> lista = new ArrayList<>();
          ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
        Vendas x ;
        while((x =(Vendas) reader.readObject()) != null ){
            System.out.println("nv: "+x.nv+" codp: "+x.codp+" cantv: "+x.cantv+" des: "+x.des);
            lista.add(x);
            
        }
        reader.close();
       return lista;
    }
    
    
    public int getPrice(String codp,int cantv, String tieneDes) throws SQLException{
        String selectQuery = "SELECT * FROM prezos WHERE codp = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1,codp);
        ResultSet result =statement.executeQuery();
        int prezoFinal =0;
        while(result.next()){
            int  prezo = result.getInt("prezo");
            int discount = result.getInt("de");
            System.out.println("el precio de : " +codp + " es: "+ prezo + " de: " + discount);
            if (tieneDes.equals("s")) {
                prezoFinal = (prezo - discount)*cantv;
            }else{
                prezoFinal = prezo*cantv;
            }
           
 
        }
        statement.close();
        result.close();
        
        
        return prezoFinal;
    }
    
    public void updateStock(int cantv,String codp) throws SQLException{
        String updateQuery = "UPDATE stock SET cants = (cants - ?) WHERE codp = ?";
        PreparedStatement statement = Conexion().prepareStatement(updateQuery);
        statement.setInt(1, cantv);
        statement.setString(2,codp);
        statement.executeUpdate();
        statement.close();
    }
    
    public String getNproducto(String codp) throws SQLException{
        String selectQuery = "SELECT * FROM stock WHERE codp = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1,codp);
        ResultSet result = statement.executeQuery();
        String name ="";
        while(result.next()){
            name =result.getString("nomp");
        }
        
       statement.close();
       result.close();
       return name;
        
    }
    
   
   
    public void writeDelimitado(int nv,String nomp,int totalproducto,int precio) throws IOException{
        
        PrintWriter  pwriter = new PrintWriter(new FileWriter(Ovendasp.pathD,true));
        pwriter.println(nv+"\t"+nomp+"\t"+totalproducto+"\t"+precio);
        pwriter.close();
    }
    
    public static void reader(String path) throws FileNotFoundException, IOException{
       BufferedReader bfreader = new BufferedReader(new FileReader(path));
        String read;
        while((read =bfreader.readLine())!= null){
           String[] valores = read.split("\t");
            System.out.println("nv: " + valores[0] +" \nnombre "+valores[1]+ " \nnumero Ventas " +valores[2] +"\nprecioTotal " +valores[3] + " €");
            System.out.println();
           
        }
        bfreader.close();
        
    }
    
    
    
    
    
    
    
    
    
    

}
