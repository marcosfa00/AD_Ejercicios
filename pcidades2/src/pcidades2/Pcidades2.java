
package pcidades2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Pcidades2 {
    private static final String path ="./zonas.txt";
    public static void main(String[] args) throws IOException, FileNotFoundException, SQLException {
        Methods methods = new Methods();
        methods.readDelimitado(path);
        
    }
}
