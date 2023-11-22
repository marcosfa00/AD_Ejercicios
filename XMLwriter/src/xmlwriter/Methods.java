

package xmlwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.Product;


public class Methods {
    
    /**
     * Metodi que nos permitirá leer de un archivo Serializado y posteriormente escribir en otro archivo .xml
     * @param path ruta archvo a leer
     * @param path2 ruta archivo a escribir
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void leerSerializacion(String path, String path2) throws FileNotFoundException, IOException, ClassNotFoundException, XMLStreamException{
          ObjectInputStream objectinput = new ObjectInputStream(new FileInputStream(path));
          //creamos ArrayList de objetos Product
          ArrayList<Product> lista = new ArrayList<>();
          Product product = null;

            while ((product = (Product) objectinput.readObject()) != null) {
                lista.add(product);
            }
            
            //Una vez ya tenemos el ArrayList lleno de los productos procedemos a mostrarlo para comprobar que este bien y después lo escribimos
            for (Product product1 : lista) {
                System.out.println(product1);
             }
            
            escribirXML(path2, lista);
    }
    
    
    public static void escribirXML(String path,ArrayList<Product> lista) throws IOException, XMLStreamException{
         XMLOutputFactory xmlOutput = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = xmlOutput.createXMLStreamWriter(new FileWriter(path));

        xmlStreamWriter.writeStartDocument("1.0");
        xmlStreamWriter.writeStartElement("productos");

        for (Product product : lista) {
            xmlStreamWriter.writeStartElement("producto");
            xmlStreamWriter.writeAttribute("codigo", product.getCodigo());

            xmlStreamWriter.writeStartElement("descripcion");
            xmlStreamWriter.writeCharacters(product.getDescripcion());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("prezo");
            xmlStreamWriter.writeCharacters(String.valueOf(product.getPrezo()));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement(); // Cerrar el elemento producto
        }

        xmlStreamWriter.writeEndElement(); // Cerrar el elemento raíz "productos"
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.close();
        
    }
    
    
    
    public static void XMLreader(String path){
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
