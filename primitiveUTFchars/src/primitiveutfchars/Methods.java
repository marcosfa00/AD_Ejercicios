

package primitiveutfchars;

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
    private static String cadena = "Está en casa";
    private static int tamanho =0;
    private static int bytesTotales =0;
    
    
    public static void primitiveUTFcharsWrite(String path) throws FileNotFoundException, IOException{
        output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
       // Procedemos a escribir la cadena
        tamanho = cadena.length();
        //Escribimos la cadena 3 veces de maneras diferentes;
        output.writeUTF(cadena);
        output.writeChars(cadena);
        output.writeUTF(cadena);
        
        //Mostramos los bytes Escritos
        //Guardamos en lav ariable tamanho los bytes totales escritos
        bytesTotales = output.size();
        System.out.println("Los bytes totales escritos son: " + bytesTotales );
      output.close();
      
    
        
    }
    
    /**
     * Haremos varios métodos, uno para leer 
     * qiue contendra los metodos
     * leerUTF
     * y leerChar
     */
    
    public static void leerUTF(String path, int num) throws FileNotFoundException, IOException{
        //decklaramos el input
        input = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        //Aquí haremos una modificación, primero obtendremos el total de bytes disponibles a leer
        int byteleido = input.available();
       
       //ahora en este bucle leemos  una sola linea puestoq ue estamos restando al espacio para leer el tamaño total de la cadena a leer 
        while((input.available())>(byteleido - tamanho)){
            System.out.print(input.readUTF());
            
        }
        System.out.println(" "+ num+ " El numero de bytes por leer es: " + input.available());
    }
    
    /**
     * Metodo para leer los chars
     *
     */
    public static void leerChars(String path) throws FileNotFoundException, IOException{
          input = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
          
          int bytesDisponibles = input.available();//obtenemos los bytes disponibles para leer
          
          //mientras lo disponible para leer sea mayor que lo disponible para leer menos el tamaño total de la cadena
          while ((input.available()) > (bytesDisponibles-tamanho)) {
              for (int j = 0; j < tamanho; j++) {
                  System.out.print(input.readChar());
                 
                  
              }
              System.out.println(" Numero de bytes por leer: " + input.available());
            
        }
    }
    
    
    /**
     * hacemos un método para leerlo todo a la vez
     */
    public static void readAll(String path) throws FileNotFoundException, IOException{
         input = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
         //Mostramos los bytes totales a leer
         System.out.println("Bytes totales a leer : " + input.available());
         
         leerUTF(path,1);
         leerChars(path);
         leerUTF(path,2);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
