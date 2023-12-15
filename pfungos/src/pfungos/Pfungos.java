
package pfungos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Pfungos {
    public static final String path ="./detectados";
    public static final String pathFinal = "./final";
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
      /*  Methods obx = new Methods();
        Connection con = obx.conexion();
      
        obx.leerDetectadosSerializado(con, path, pathFinal);*/
      
      
      Methods2 methods = new Methods2();
      ArrayList<Detectados>list =   methods.readSerial();
      int numeroFungos =0;
      int hummedia =0;
      int humlimite = 0;
      double superficieAfectada = 0;
      double porcentaxedanos =0;
        System.out.println("************************************");
        for (int i = 0; i < list.size(); i++) {
           
            int coda = list.get(i).getCodarea();
            System.out.println("detectado " + (i+1));
            numeroFungos = methods.getNFungos(coda);
            hummedia = methods.getHummedia(coda);
            methods.showAreas(coda);
            methods.showFungos(list.get(i).getCodfungo());
            //methods.updateNfungos(coda);
            humlimite = methods.getHumlimite(coda);
           superficieAfectada = list.get(i).getSuperficie();
           porcentaxedanos = methods.porcentaxeDanos(coda,hummedia, humlimite, superficieAfectada);
            System.out.println("porcentaxeDaños: " + porcentaxedanos);
            methods.writeDelimitado(coda, "noma", "nomf", superficieAfectada, porcentaxedanos);
            
        }
        
        methods.readDelimitado();
        
    }

}
