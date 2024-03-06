package pinvasoras;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.persistence.*;
import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.where;

public class Methods {



    /**
     * Conexión MongoDB
     */
    private MongoClient mongoClient;
    private MongoDatabase database;

    public void conectionMongoDB(String database){
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase(database);
    }
    public void closeClientMongo(){
        mongoClient.close();
    }
    /**
     * Postgres
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
    public Connection ConexionPost() throws SQLException{
        Connection conn = DriverManager.getConnection(url,usuario,password);
        return conn;

    }





    /**
     * Conexión ObjectDB
     */

    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    //Creamos la conexión con ObjectDB
    public void conectionODB(){
        entityManagerFactory = Persistence.createEntityManagerFactory("$objectdb/db/encontradasezonas.odb");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Encontradas> getEncontradas() throws SQLException {
        System.out.println("********ENCONTRADAS************** \n");
        TypedQuery<Encontradas> selectQuery = entityManager.createQuery("SELECT e FROM Encontradas e",Encontradas.class);
        // metemos las zonas en un alista
        List<Encontradas> listEncontradas = selectQuery.getResultList();
        //Mostramos la lista de Encontradas
        int tempMedia =0;
        int tempBarrera =0;
        for( Encontradas e :listEncontradas){
            System.out.println(e);
            //Obtenemos la zona por el id
            Zonas zona = getZonabyId(e.getCodzona());
          tempMedia =  zona.getTempmedia();
            tempBarrera = getTempBArrera(e.getCodespecie());
            String nomei = getNomInvasora(e.getCodespecie());
            if (tempMedia> tempBarrera){
                crearResumo(zona.getCodz(),zona.getNomz(),nomei,e.getExtension(),zona.getSuperficie());
            }
            //updateZonas(e);

        }
        return listEncontradas;


    }

    public void updateZonas(Encontradas encontradas){
        int codzona = encontradas.getCodzona();
        entityManager.getTransaction().begin();
        int update = entityManager.createQuery("UPDATE Zonas z SET z.numeroinvasoras = z.numeroinvasoras + 1 WHERE z.codz = :codzona")
                .setParameter("codzona",codzona)
                .executeUpdate();
        entityManager.getTransaction().commit();

        if (update >0){
            System.out.println(" se ha actualizado correctamente");

        }else {
            System.out.println("no se ha actualizado");
        }


    }


    public List<Zonas>  getZonas(){
        System.out.println("\n********ZONAS************** \n");
        TypedQuery<Zonas> selectQuery = entityManager.createQuery("SELECT z FROM Zonas z",Zonas.class);
        //Obtenemos una Lista de Zonas
        List<Zonas> listaZonas = selectQuery.getResultList();
        for (Zonas z:listaZonas){
            System.out.println(z);

        }
        return listaZonas;
    }

    public Zonas getZonabyId(int id){
        Zonas zona = entityManager.find(Zonas.class,id);
        return zona;
    }



    public int getTempBArrera(int id){
        MongoCollection<Document> collection = this.database.getCollection("especiesinvasoras");
        Bson campos = eq("_id",id);
        FindIterable<Document> iterable = collection.find(campos);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc = cursor.next();
        int tempBarrera = doc.getInteger("tempbarrera");


       return tempBarrera;


    }

    public String getNomInvasora(int id){
        MongoCollection<Document> collection = this.database.getCollection("especiesinvasoras");
        Bson campos = eq("_id",id);
        FindIterable<Document> iterable = collection.find(campos);
        MongoCursor<Document> cursor = iterable.iterator();
        Document doc = cursor.next();
        String nomei = doc.getString("nomei");


        return nomei;


    }








    public void crearResumo(int codz, String nomz, String nomeinvasora, double extendionEncontradas, double superficieZona) throws SQLException {
        //Conexión con postgres
        String insert = "insert into resumo(codz,nomez,nomei,danos.extensiondanada,danos.porcentaxedanos) VALUES(?,?,?,?,?)";
        PreparedStatement statement = ConexionPost().prepareStatement(insert);
        statement.setInt(1,codz);
        statement.setString(2,nomz);
        statement.setString(3,nomeinvasora);
        statement.setDouble(4,extendionEncontradas);
        statement.setDouble(5,superficieZona);
        statement.executeUpdate();
        statement.close();



    }





    public void closeConnection(){
        entityManager.close();
        entityManagerFactory.close();
    }


}
