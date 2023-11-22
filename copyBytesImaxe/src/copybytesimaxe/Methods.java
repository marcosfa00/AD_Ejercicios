

package copybytesimaxe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methods {
     private static FileInputStream input;
    private static FileOutputStream output;
    
    
    public static void copyBytexto(String path1, String path2) throws FileNotFoundException, IOException{
        //Ojo no se puede leer y escribir a la vez en un mismo fichero, pero leer en uno y escribir en otro a la vez si
        input = new FileInputStream(path1);
        output = new FileOutputStream(path2,true);
        int byteLeido; //Aquí almacenaremos el byteLeido
        while((byteLeido =input.read())!= -1){//almacenados en la variable el byte leido y comprobamos que este no sea = a -1
            
            output.write(byteLeido);//Escribimos en el otro fichero el byte leido
            
        }
        
    }
}
