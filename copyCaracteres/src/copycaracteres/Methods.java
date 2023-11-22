

package copycaracteres;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Methods {

    private static FileWriter writer = null;
    private static FileReader reader = null;
    
    public static void writer(String path1,String path2) throws IOException{
        reader = new FileReader(new File(path1));
        writer = new FileWriter(new File(path2));
         int x ;
         System.out.print("Leyendo ");
        while((x = reader.read())!= -1){
            
            System.out.print(".");
            writer.write(x);
        }
        writer.close();
       
    }
}
