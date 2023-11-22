

package serializacion2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Methods {
    
    
    public static void writeProducts(String path, Product obj,Product obj1,Product obj3) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOut = new ObjectOutputStream( new FileOutputStream(path));
        objectOut.writeObject(obj);
        objectOut.writeObject(obj1);
        objectOut.writeObject(obj3);
        objectOut.writeObject(null);
        objectOut.close();
        
    }
    public static void leerProductosSerial(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream objectinput = new ObjectInputStream(new FileInputStream(path));
        Product x ;
        while((x =(Product) objectinput.readObject()) != null ){
            System.out.println(x.toString());
        }
        
        objectinput.close();
    }

}
