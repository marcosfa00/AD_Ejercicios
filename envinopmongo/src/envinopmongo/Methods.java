package envinopmongo;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class Methods {
    /**
     * Se declarano los atributos necesarios
     */
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    /**
     * Creamos un método conexión
     */
    public void getConnection(String database){
        mongoClient = MongoClients.create();
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public void cerrarCliente(){
        mongoClient.close();
    }

    /**
     * {
     *     _id: 'a1',
     *     acidez: 6,
     *     grao: 9,
     *     taninos: 0,
     *     tipodeuva: 'c',
     *     cantidade: 2,
     *     dni: '36898976z'
     *   },
     *
     */
    public void getAnalisisdb(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("analisis");
        Bson fields = Projections.fields();
        //Tras obtener los campos con los que queremos trabajar vamos a usarlos
        //Se crea un cursor
        FindIterable<Document> preResult = collection.find().projection(fields).sort(Sorts.ascending("_id"));
        //Ahora debemos iterar este preResultado
        MongoCursor<Document> result = preResult.iterator();
        //Obyenemos los campos en un documento
        Document doc;
        //Declaramos las variables de los datos que queremos obtener
        Analisis analisis = new Analisis();
        while (result.hasNext()){
            //Obtenemos la fila
            doc = result.next();
            //Ahora obtenemos todos los campso del documento uno a uno
            analisis.setIdAnalisis(doc.getString("_id"));
            analisis.setAcidez(doc.getInteger("acidez"));
            analisis.setTipodeuva(doc.getString("tipodeuva"));
            analisis.setCantidade(doc.getInteger("cantidade"));
            analisis.setDni(doc.getString("dni"));
            //Llamamos a getUvas
            System.out.println("Llamamos a get uvas y le pasamos" + analisis);
            getUvas(analisis);
            updateCliente(analisis);

            System.out.println(analisis);
        }
    }

    private void getUvas(Analisis analisis){
        MongoCollection<Document> collection = mongoDatabase.getCollection("uvas");
        Bson equals =  eq("_id", analisis.getTipodeuva());
        FindIterable<Document> preResult = collection.find(equals);
        MongoCursor<Document> cursor = preResult.iterator();
        Document doc = cursor.next();
            Uvas uva = new Uvas(
                    doc.getString("_id"),
                    doc.getString("nomeu"),
                    doc.getInteger("acidezmin"),
                    doc.getInteger("acidezmax")
            );
           //llamamos a Xerado
            String trataAcidez = getTrataAcidez(uva.getAcidezmin(), uva.getAcidezMax(), analisis.getAcidez());
            int total = 15*analisis.getCantidade();
            createXerado(analisis.getIdAnalisis(), uva.getNome(), trataAcidez,total);

            System.out.println(uva);

        }








    public void createXerado(String idAnalisis,String nomeUva, String trataAcidez, int total){
        MongoCollection<Document> collection = mongoDatabase.getCollection("xerado");
        //para crear la base de datos debemos hace un insert
        Document insert = new Document("_id",idAnalisis)
                .append("nomeuva" ,nomeUva)
                .append("trataacidez", trataAcidez)
                .append("total",total);
        collection.insertOne(insert);
        System.out.println("Creado XERADO");


    }

    private String getTrataAcidez(int aMinima, int aMaxima, int acidez){
        String trataAcidez ="";
        if(acidez > aMaxima){
            trataAcidez = "baixar acidez";
        } else if (acidez < aMinima){
            trataAcidez = "subir acidez";
        } else{
            trataAcidez = "acidez correcta";
        }
        return trataAcidez;
    }


    private void updateCliente(Analisis analisis){
        MongoCollection<Document> collection = mongoDatabase.getCollection("clientes");
        //Debemso hacer otra query
        Bson query = eq("_id", analisis.getDni());
        Bson resultUpdate = Updates.combine(
            Updates.inc("numerodeanalisis", 1)
        );
        collection.updateOne(query,resultUpdate);
        System.out.printf("Cliente Actualizado");
    }




}
