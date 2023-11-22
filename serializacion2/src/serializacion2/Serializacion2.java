
package serializacion2;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author marcosfa
 */
public class Serializacion2 {
private static String path = "./serializacion2";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        double[] prezo = {3, 4, 5};
        Product product1 = new Product( cod[0],desc[0],prezo[0]);
        Product product2 = new Product( cod[1],desc[1],prezo[1]);
        Product product3 = new Product( cod[2],desc[2],prezo[2]);
        System.out.println("p1.tostrcing: "+ product1.toString());
        
        Methods.writeProducts(path, product1,product2,product3);
       
        
        Methods.leerProductosSerial(path);
      
        
        
        
    }

}
