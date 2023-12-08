

package copycaracteres2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Methods {
    private static BufferedReader bfreader = null;
    private static PrintWriter pWriter = null;
    

    public static void copyPasteBuffer(String path1,String path2) throws FileNotFoundException, IOException{
        
        bfreader = new BufferedReader(new FileReader(path1));
        pWriter = new PrintWriter(new FileWriter(path2));
        String x ="" ;
        while ((x =bfreader.readLine())!= null) {
            pWriter.println(x);
        }
        pWriter.close();
        
        
        
    }
}
