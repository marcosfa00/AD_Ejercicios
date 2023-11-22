

package copybytesimaxe2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methods {
  private static  BufferedInputStream bfinput;
  private static  BufferedOutputStream bfoutput;
    
    public static void copyBufferImaxe(String ruta1, String ruta2) throws FileNotFoundException, IOException{
        bfinput = new BufferedInputStream(new FileInputStream(ruta1));
        bfoutput = new BufferedOutputStream(new FileOutputStream(ruta2));
        int bfread ;
        while((bfread =bfinput.read())!= -1){
           bfoutput.write(bfread);
        }
        
        
    }

}
