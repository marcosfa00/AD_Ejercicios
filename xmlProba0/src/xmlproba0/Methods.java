

package xmlproba0;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Methods{
   
    
    
    public void escrituraXML(String path) throws IOException, XMLStreamException{
         XMLOutputFactory xmlOutput = XMLOutputFactory.newInstance(); //parece un objeto singleton
         XMLStreamWriter xmlStreamWriter = xmlOutput.createXMLStreamWriter(new FileWriter("./archivo"));
          // Escribir el encabezado del documento XML con la versión especificada
            xmlStreamWriter.writeStartDocument("1.0");

            // Comenzar a escribir los elementos del documento XML
            xmlStreamWriter.writeStartElement("autores");

            // Escribir el primer autor y sus libros
            xmlStreamWriter.writeStartElement("autor");
            xmlStreamWriter.writeAttribute("codigo", "a1");
            xmlStreamWriter.writeStartElement("nombre");
            xmlStreamWriter.writeCharacters("Alexandre Dumas");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("titulo");
            xmlStreamWriter.writeCharacters("El conde de Montecristo");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("titulo");
            xmlStreamWriter.writeCharacters("Los miserables");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement(); // Cerrar el elemento autor

            // Escribir el segundo autor y sus libros
            xmlStreamWriter.writeStartElement("autor");
            xmlStreamWriter.writeAttribute("codigo", "a2");
            xmlStreamWriter.writeStartElement("nombre");
            xmlStreamWriter.writeCharacters("Fiodor Dostoyevski");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("titulo");
            xmlStreamWriter.writeCharacters("El idiota");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("titulo");
            xmlStreamWriter.writeCharacters("Noches blancas");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement(); // Cerrar el elemento autor

            // Cerrar el elemento raíz "autores"
            xmlStreamWriter.writeEndElement();

            // Finalizar la escritura y cerrar el XMLStreamWriter
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

            System.out.println("Documento XML creado exitosamente: " + path);

    }
    
}


