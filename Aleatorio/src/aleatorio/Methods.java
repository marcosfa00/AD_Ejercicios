

package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Methods {
    //guardar los contenidos en un fichero aleatorio
    /**
     *   String[] codes={"p1","p2","p3"};
        String[] descricion ={"parafusos","cravos ","tachas"};
        int[] prices ={3,4,5};
        int l
     */
    private static int longitudTotal = 30;
    private static RandomAccessFile rafile = null;
    
    public static void readRandomFile(String path,String[]codigo,String []descripcion,int[]precio) throws FileNotFoundException, IOException{
        rafile = new RandomAccessFile(new File(path),"rw");
        for (int i = 0; i < 3; i++) {
            rafile.writeChars(codigo[i]);
            rafile.writeChars(descripcion[i]);
            rafile.writeInt(precio[i]);
            
        }
        
    }
    
    public static void readRandomFile(String path){
        
    }
    
    

}
