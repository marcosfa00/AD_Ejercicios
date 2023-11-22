

package textodelimitado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Methods {
    
    private static PrintWriter pwriter = null;
    private static BufferedReader bfreader = null;
    
    public static void writer(String[]codigos,String [] descripcion,double[] precio,String path) throws IOException{
        pwriter = new PrintWriter(new FileWriter(path));
        
        for (int i = 0; i < codigos.length; i++) {
            //Si i vale 0 escribimos en una linea el codigo de la posicion 0 la descripcion y el precio
            //empleamos println para hacer un saltod e linea al final y utilizamos tabulación para separar cada uno de los elementos 
            pwriter.println(codigos[i] + "\t"+ descripcion[i] +"\t" +precio[i]);
            
        }
        pwriter.close();
        
    }
    
    public static void reader(String path) throws FileNotFoundException, IOException{
        bfreader = new BufferedReader(new FileReader(path));
        String read;
        while((read =bfreader.readLine())!= null){
           String[] valores = read.split("\t");
            System.out.println("Codigo: " + valores[0]);
            System.out.println("Descripción "+valores[1]);
            System.out.println("Precio " +valores[2] + " €");
            System.out.println("");
           
        }
        bfreader.close();
        
    }

}
