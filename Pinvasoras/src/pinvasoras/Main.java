package pinvasoras;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("PINVASORAS");
        Methods m = new Methods();
        m.conectionODB();
        m.conectionMongoDB("test");
        List<Encontradas> listaEncontradas = m.getEncontradas();
        List<Zonas> listazonas= m.getZonas();

        m.closeClientMongo();
        m.closeConnection();
    }
}