
package baserelacionala;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author marcosfa
 */
public class BaserelacionalA {

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Conection x = new Conection();
        Date fecha = new Date();
        
       // x.insertProduct("03", "tachas", 6,new java.sql.Date(fecha.getTime()));
       x.listaProductos("produtos");
       //x.listaProductosPorCodigo("01");
       x.updateProduct("01", 33);
       x.listaProductos("produtos");
       x.eliminarProducto("03");
       x.listaProductos("produtos");
    }

}
