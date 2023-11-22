

package productsstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methods {

  private static DataOutputStream output = null;
  private static DataInputStream input =null;

    
    public static void escribir(String path,Product x) throws FileNotFoundException, IOException{
        output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path,true)));
        //Escribimos con UTF codigo y descripción y con Double el precio
        output.writeUTF(x.getCodigo());
        output.writeUTF(x.getDescripcion());
       
        output.writeDouble(x.getPrezo());
       // BytesTotales = BytesTotales - output.size();
        output.close();
        System.out.println("Se ha escrito todo correctamente");
    }
    
    public static void leerYGuardar(String path,Product x) throws FileNotFoundException, IOException{
        input = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        //Leemos linea a linea
        
        while(input.available() !=0){
            x.setCodigo(input.readUTF());
            x.setDescripcion(input.readUTF());
            x.setPrezo(input.readDouble());
            System.out.println(x.toString());
        }
        
    }
}
