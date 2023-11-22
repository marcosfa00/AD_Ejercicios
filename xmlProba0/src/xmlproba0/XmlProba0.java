
package xmlproba0;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

public class XmlProba0 {
    public static void main(String[] args) throws IOException, XMLStreamException {
        
        Methods x = new Methods();
        x.escrituraXML("./xml.xml");
    }

}
