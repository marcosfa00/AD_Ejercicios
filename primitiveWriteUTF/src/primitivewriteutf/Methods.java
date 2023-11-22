

package primitivewriteutf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methods {

    private static DataInputStream datainput;
    private static DataOutputStream dataoutput;
    
    public static void primitiveWriteUTF(String path2) throws FileNotFoundException, IOException{
        dataoutput = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path2)));
        datainput = new DataInputStream(new BufferedInputStream(new FileInputStream(path2)));
        
        String cadena = "el tiempo está gélido";
        for (int i = 0; i < 3; i++) {
           dataoutput.writeUTF(cadena);
            System.out.println("bytes Escritos"+ (dataoutput.size()));
            
        }
         dataoutput.close();
         
       while(datainput.available()!=0){
           
           System.out.println(datainput.readUTF());
       }
        
        
        
        
        
    }
    
}
