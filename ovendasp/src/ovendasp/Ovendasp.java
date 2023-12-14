
package ovendasp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Ovendasp {
    
    public static final String path = "./vendas";
    public static final String pathD = "./delimitado";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        
        Methods methods = new Methods();
        methods.mainEvent();
    }
    
}   
/*
postgres=# select * from stock;
 codp |  nomp   | cants 
------+---------+-------
 p4   | arroz   |  2000
 p6   | pan     |   200
 p3   | farinha |   998
 p2   | aceite  |   398
 p1   | leite   |   493
 p5   | sal     |   498
(6 rows)



postgres=# select * from prezos;
 codp | prezo | de 
------+-------+----
 p1   |    10 |  1
 p2   |    20 |  2
 p3   |    30 |  3
 p4   |    40 |  5
 p5   |    20 |  5
 p6   |    40 | 10
(6 rows)
*/
