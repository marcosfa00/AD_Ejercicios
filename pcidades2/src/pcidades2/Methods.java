

package pcidades2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

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
    
    /**
     * Mostramos los datos del texto Delimitado
     * @param path Ruta del archivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public  void readDelimitado(String path) throws FileNotFoundException, IOException, SQLException{
        BufferedReader read = new BufferedReader(new FileReader(path));
        String result="";
        int nPropiedades =0;
        while((result = read.readLine())!=null){
          
           String[] parts = result.split("_");
            System.out.print(parts[0] + "    ");
            System.out.print(parts[1]+ "    ");
            System.out.print(parts[2]+ "    \n");
            
                   nPropiedades = getNPropiedades(parts[0],Integer.parseInt(parts[2]));
        }
        System.out.println("El nuemro de propiedades es: "+ nPropiedades);
                
    }
    
    
    public int getNPropiedades(String codZ, int precioPiso) throws SQLException{
        String selectQuery = "SELECT * FROM propiedades WHERE codz =?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1, codZ);
        ResultSet result = statement.executeQuery();
        int nPropiedades =0;
        while (result.next()) {
            String codP = result.getString("codp");
            System.out.print("   "+codP + "\n");
            nPropiedades ++;
            getInfPiso(codP,precioPiso);
            
        }
        return nPropiedades;
        
    }
    
    
    public void  getInfPiso(String codP, int precioM2) throws SQLException{
        String selectQuery = "SELECT * FROM pisos WHERE codp = ?";
        PreparedStatement statement = Conexion().prepareStatement(selectQuery);
        statement.setString(1, codP);
        ResultSet result = statement.executeQuery();
        int m2 =0;
        int precio =0;
        while(result.next()){
            m2 = result.getInt("m2");
            int anhoConstruccion = result.getInt("ano");
            System.out.print("Los M^2 de este piso son: " + m2 + " \n");
            
           Date hoy = new Date();
            
            precio =getPrecioPropiedad(m2, precioM2,hoy,anhoConstruccion);
            
            
        }
       
    }
    
    
  public int getPrecioPropiedad(int nM2, int precioM2, Date hoy, int anhoConstruccion) {
        int anhoActual = hoy.getYear() + 1900; // Obtener el año actual sumando 1900
        int antiguedad = anhoActual - anhoConstruccion;

        int descuento = 0;

        if (antiguedad > 30) {
            descuento = 20000;
        } else if (antiguedad > 20) {
            descuento = 10000;
        } else if (antiguedad > 10) {
            descuento = 5000;
        }

        int precioTotal = nM2 * precioM2;
        int precioFinal = precioTotal - descuento;

        System.out.println("El precio total es: " + precioFinal);
        
        return precioFinal;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
