
package baserelacionalb;

import java.sql.SQLException;

/**
 *
 * @author marcosfa
 */
public class BaserelacionalB {

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        Conection x = new Conection();
        x.listarConResult();
        x.actualizarConResult("02", 33.0);
        
    }

}
