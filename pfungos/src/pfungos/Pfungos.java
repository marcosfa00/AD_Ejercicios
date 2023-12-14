
package pfungos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;


public class Pfungos {
    public static final String path ="./detectados";
    public static final String pathFinal = "./final";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        Methods obx = new Methods();
        Connection con = obx.conexion();
      
        obx.leerDetectadosSerializado(con, path, pathFinal);
        
    }

}
