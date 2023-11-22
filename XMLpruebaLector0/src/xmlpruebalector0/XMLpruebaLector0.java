
package xmlpruebalector0;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

public class XMLpruebaLector0 {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        
        Methods x  = new Methods();
        x.leerXML("./archivo");
    }

}
