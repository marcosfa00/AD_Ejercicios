package pavions;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("PAVIONS");
        Methods m = new Methods();
        m.getReservas();
        m.readObjectDB();
        m.close();

    }
}