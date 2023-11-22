
package xmlwriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import serializacion2.Product;

public class XMLwriter {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, XMLStreamException {
      //Primero importamos la clase, ahora debemos leer el fichero y 
      
      Methods.leerSerializacion("./serializacion2", "./serializacion2.xml");
      
    }

}
