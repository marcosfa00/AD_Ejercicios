

package com.marcosfa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Methods {
    private static DataInputStream input;
    private static DataOutputStream output;
    
    public static void primitiveWriteChars(String path1) throws FileNotFoundException, IOException{
    
      
        output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path1)));
        input = new DataInputStream(new BufferedInputStream(new FileInputStream(path1)));
        
        String cadena ="el tiempo está gelido";
        
        int bytEscritos =0;
        output.writeChars(cadena);
        bytEscritos = output.size();
        System.out.println("se han escrito "+bytEscritos+" bytes");
        bytEscritos =0;
        output.writeChars("\n");
        bytEscritos = output.size();
        output.writeChars(cadena);
        System.out.println("se han escrito "+(bytEscritos)+" bytes");
        bytEscritos =0;
        output.close();
        
        while(input.available()!= 0){
           
            System.out.print(input.readChar());
            
        }
        
    }
        
}
