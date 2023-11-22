
package textodelimitado;

import java.io.IOException;

/**
 *
 * @author marcosfa
 */
public class Main {
    private static final String path ="./delimitado.txt"; 

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[] cod={"p1","p2","p3"};
        String[] desc ={"parafusos","cravos","tachas"};
        double[] prezo ={3,4,5};
        
        Methods.writer(cod, desc, prezo, path);
        Methods.reader(path);
        
    }

}
