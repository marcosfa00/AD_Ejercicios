
package productsstream;

import java.io.IOException;

/**
 *
 * @author marcosfa
 */
public class ProductsStream {
    private static final String path = "./productos.txt";

    public static void main(String[] args) throws IOException {
        /* 
        "cod1", "parafusos", 3.0
        "cod2","cravos",4.0
        "cod3","tachas",6.0
        "cod4","grapas",2.0
        */
        Product x = new Product("cod1","parafusos",3.0);
        Product x1 = new Product("cod2","cravos",4.0);
        Product x2 = new Product("cod3","tachas",6.0);
        Product x3 = new Product(  "cod4","grapas",2.0);
           
        
        Methods.escribir(path, x);
        Methods.escribir(path, x1);
        Methods.escribir(path, x2);
        Methods.escribir(path, x3);
        
        //Tras grabar los products anterores en el documento Productos.txt
        
        Product po3 = new Product();
        Methods.leerYGuardar(path, po3);
        
    }

}
