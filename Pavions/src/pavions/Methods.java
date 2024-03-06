package pavions;

import com.mongodb.client.*;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.management.BadStringOperationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.print.Doc;
import java.sql.*;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Methods {

    /**
     * ObjectDB
     */
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    private void objectdbConnection(){
        entityManagerFactory = Persistence.createEntityManagerFactory("$objectdb/db/resultado2.odb");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Mongo
     */

    MongoClient mongoClient;
    MongoDatabase database;

    /**
     * Conexión a base d edatos Mongo
     *
     */
    private void mongoDBConnection(String mongoDB){
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase(mongoDB);
    }

    /**
     * Conexión con Postgres
     */
    private String driver ="jdbc:postgresql:";
    private String host = "//localhost:";
    private String puerto = "5432";//Recordar que pro defecto en clase será el 5432
    private String sid = "postgres";
    private String usuario="postgres";
    private String password = "postgres";
    private String url = driver + host + puerto + "/" + sid;


    /**
     * Este Metodo devuelve la conexión con la base de datos
     * @return Objecto de Tipo Connection
     */
    public Connection ConexionPost() throws SQLException {
        Connection conn = DriverManager.getConnection(url,usuario,password);
        return conn;

    }


    public void getReservas() throws SQLException {
        mongoDBConnection("test");
        String selectQuery = "SELECT *,(voos).* FROM reservas";
        PreparedStatement statement = ConexionPost().prepareStatement(selectQuery);
        ResultSet result = statement.executeQuery();
        int codr,vooIda,vooVolta;
        String dni;
        while (result.next()){
            codr = result.getInt("codr");
            dni = result.getString("dni");
            vooIda = result.getInt("idvooida");
            vooVolta = result.getInt("idvoovolta");
            System.out.println("Reserva: codr - " + codr +  ", dni - " + dni+", vooIda - " + vooIda + ", vooVolta - " + vooVolta );
            //updateNReservas(dni);
            Confirmada confirmada = createConfirmada(codr,dni,vooIda,vooVolta);
            createObjectDB(confirmada);

        }
    }

    public void updateNReservas(String dni){
       // mongoDBConnection("test");
        MongoCollection<Document> collection = database.getCollection("pasaxeiros");
        Bson query = eq("_id",dni);
        Bson change = Updates.combine(
                Updates.inc("nreservas",1)
        );
        collection.updateOne(query,change);

    }

    /**
     * orixe ida/orixe volta prezoreserva
     * @param dni
     * @return
     */
    public Confirmada createConfirmada(int codr,String dni,int vooIda, int vooVolta) throws SQLException {

        Confirmada confirmada = new Confirmada();
        confirmada.setCodr(codr);
        confirmada.setDni(dni);
        MongoCollection<Document> collection = database.getCollection("voos");
        Bson fields = Projections.fields(
                Projections.include(
                        "orixe",
                        "prezo"
                )
        );
        Bson equals = eq("_id", vooIda);
        FindIterable<Document> preResult = collection.find(equals);
        MongoCursor<Document> result = preResult.iterator();

        //Obtenemos datos primera consulta
        Document doc = result.next();
        String orixeida = doc.getString("orixe");
        int prezoIda = doc.getInteger("prezo");

        //Segunda consulta
        Bson equals2 = eq("_id",vooVolta);
        FindIterable<Document> preResult2= collection.find(equals2);
        MongoCursor<Document> result2 = preResult2.iterator();

        Document doc2 = result2.next();
        String orixeVolta = doc2.getString("orixe");
        int prezoVolta = doc2.getInteger("prezo");

        double total = prezoIda + prezoVolta;

        confirmada.setOrixeida(orixeida);
        confirmada.setOrixevolta(orixeVolta);
        confirmada.setPrezoreserva(total);
        return  confirmada;

    }


    /*
    resultado.odb
     */
    private void  createObjectDB(Confirmada confirmada){
        objectdbConnection();
        entityManager.getTransaction().begin();
        entityManager.persist(confirmada);
        entityManager.getTransaction().commit();
    }


    public void readObjectDB(){

        TypedQuery<Confirmada> query = entityManager.createQuery("SELECT c FROM Confirmada c", Confirmada.class);
        List<Confirmada> results = query.getResultList();

        for(Confirmada c: results){
            System.out.println(c.toString());
        }
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
        mongoClient.close();

    }

}
