
package pfungos;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pfungos {
    public static final String path = "./detectados";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        Methods methods = new Methods();
        methods.readSerial(path);
    }

}
