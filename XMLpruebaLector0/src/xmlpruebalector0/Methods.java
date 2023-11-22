package xmlpruebalector0;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Methods {



   
   
   
   
   public void leerXML(String path) throws FileNotFoundException, XMLStreamException{
    XMLInputFactory factory = XMLInputFactory.newInstance();//Insgtancciamos el objeto input
    XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(path));
    
    //una vez instanciados los objetos toca leer el archivo XML o obtener un entero del tipo de elemento que es
    int eventType;
    while(reader.hasNext()){
        eventType = reader.next();
        //con el tipo de elemento obtenido, podremos hacer un switch case para saber que tipo de elemento es
        switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("Elemento de inicio: " + reader.getLocalName());
                        
                        // Ejemplo: Obtener atributos si existen
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            System.out.println("Atributo " + i + ": " + reader.getAttributeValue(i));
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        System.out.println("Texto: " + reader.getText());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        System.out.println("Elemento de fin: " + reader.getLocalName());
                        break;
                    default:
                        break;
                }
    }
    
    reader.close();
   
       
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
