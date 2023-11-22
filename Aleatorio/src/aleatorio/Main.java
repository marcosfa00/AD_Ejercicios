
package aleatorio;

import java.io.IOException;

/**
 *
 * @author marcosfa
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[] codes={"p1","p2","p3"};
        String[] descricion ={"parafusos","cravos ","tachas"};
        int[] prices ={3,4,5};
        int longitudTotal = 30;
        
        Methods.readRandomFile("./random.txt", codes, descricion, prices);
    }

}
