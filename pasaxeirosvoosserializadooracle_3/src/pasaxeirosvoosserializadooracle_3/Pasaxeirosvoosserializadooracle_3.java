
package pasaxeirosvoosserializadooracle_3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Pasaxeirosvoosserializadooracle_3 {
    public static final String path ="./Reservas";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
       Methods x = new Methods();
       //x.leerSerializado("./reservas");
      x.insertReserva(path);
   
        
        System.out.println("");
    }

}
