
package pfungos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Pfungos {
    public static final String path = "./detectados";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        
        Methods methods = new Methods();
        methods.readSerial(path);
        
    }

}
