
package exa15brevep;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marcosfa
 */
public class Exa15brevep {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        // TODO code application logic here
        ArrayList<Platos> lista = new ArrayList<>();
        Conection x = new Conection();
        lista =x.readFihero("Platoss");
        x.compruebaDB(lista);
        x.getNombre("p2", "c2");
    }

}
