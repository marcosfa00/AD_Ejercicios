

package pasaxeirosvoosserializadooracle_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.sql.*;

public class Methods {
   
    
     /**
     * Declaramos los atributos para conectarnos a la bae SQL
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
    
    
    public void insertReserva(String path) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException{
        ArrayList<Reserva> lista = new ArrayList<>();
        lista = leerSerializado(path);
    
        String insertQuery = "INSERT INTO reservasfeitas (codr, dni, nome, prezoreserva) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = Conexion().prepareStatement(insertQuery);
        for (int i = 0; i < lista.size(); i++) {
            //Codigo de la reserva
            statement.setInt(1, lista.get(i).getCodr());
            
            //DNI
            statement.setString(2,lista.get(i).getDni() );
            
            //Nombre
            statement.setString(3, getNombre(lista, i));
            
            //Prezo Reservas
            int Precioida = getPrecioVuelos(lista.get(i).getIdvooida());
            int PrecioVuelta =getPrecioVuelos(lista.get(i).getIdvoovolta());
            int total = Precioida + PrecioVuelta;
            statement.setInt(4,total );
            
             //Ejecutamos la consulta:
            
            statement.executeUpdate();
       
        }
        
        for (int i = 0; i < lista.size(); i++) {
            //Actualizamos el número de reservas
            insertPaxaseiros(lista.get(i).getDni());
            
        }
        statement.close();
       
        
    }
    
    public void insertPaxaseiros(String dni) throws SQLException{
        int nreservas = getTotalReservationsForDNI(dni);
        String updateQuery = "UPDATE pasaxeiros SET nreservas = ? WHERE dni = ?";
        PreparedStatement statement = Conexion().prepareStatement(updateQuery);
        statement.setInt(1, nreservas);
        statement.setString(2, dni);
        statement.executeUpdate();
        
    }
    
    public ArrayList<Reserva> leerSerializado(String path) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
         /**
       Reserva{codr=1, dni=361a, idvooida=1, idvoovolta=2}
       Reserva{codr=2, dni=362b, idvooida=3, idvoovolta=4}
       Reserva{codr=3, dni=361a, idvooida=5, idvoovolta=6}
     */
        ObjectInputStream read = new ObjectInputStream(new FileInputStream(path));
        ArrayList<Reserva> lista = new ArrayList<>();
        Reserva x;
        //Mosramos lo guardado en el fichero reserva
        while((x =(Reserva) read.readObject())!=null){
            System.out.println(x);
            lista.add(x);
        }
        read.close();
        
        return lista;
        
     }
    public String getNombre(ArrayList<Reserva> lista,int id) throws SQLException{
        String nombre = "";
        String selectQuery = "SELECT * FROM pasaxeiros where dni =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1, lista.get(id).getDni());
         ResultSet result = statement.executeQuery();
          while(result.next()){
           nombre =result.getString("nome");
                System.out.println("Nombre pasagero: "+nombre);
          }
          statement.close();
          return nombre;
          
    }
   
    
 public int getTotalReservationsForDNI(String dni) throws SQLException {
    int contador = 0;
    String selectQuery = "SELECT COUNT(*) FROM reservasfeitas WHERE dni = ?";
    PreparedStatement statement = Conexion().prepareStatement(selectQuery);
    statement.setString(1, dni);

    ResultSet result = statement.executeQuery();
        while(result.next()){
            contador = result.getInt(1);
        System.out.println("Total de reservas para el DNI " + dni + ": " + contador);
    
        }
        

    statement.close();
    return contador;
}

    
    
    
   
    
    public int getPrecioVuelos(int id) throws SQLException{
        int precioReserva =0;
        String vuelosQuery = "SELECT * FROM voos WHERE voo=?" ;
                PreparedStatement statement = Conexion().prepareStatement(vuelosQuery);
               
                    
                   statement.setInt(1, id);
                   ResultSet result = statement.executeQuery();
           
                        
                    while(result.next()){
                        precioReserva += result.getInt("prezo");
                        System.out.println(precioReserva);
                       
                    }
                    statement.close();
            return precioReserva;
                
                 
        
         
        
    }
}
