
package serializacion1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author marcosfa
 */
public class Serializacion1 {
     static private ObjectOutputStream objOutput;
     static private ObjectInputStream objInput;
     public static void WriteObject(String path,MiClase obj) throws FileNotFoundException, IOException{
        objOutput = new ObjectOutputStream(new FileOutputStream(path));
        objOutput.writeObject(obj);
        objOutput.close();
    }
     
     public static MiClase readObject(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
         objInput = new ObjectInputStream(new FileInputStream(path));
         MiClase x = (MiClase)objInput.readObject();
         return x;
     }
       public static void readObjectnoI(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
         objInput = new ObjectInputStream(new FileInputStream(path));
         MiClase x = (MiClase)objInput.readObject();
           System.out.println("object: nombre"+x.getNombre() +"   "+ x.getNumero2());
     }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        MiClase x = new MiClase("hola", -7, 2.7E10);
        
        WriteObject("./serial", x);
        MiClase y = readObject("./serial");
        System.out.println(  y.toString());
        readObjectnoI("./serial");
        
    }

}
