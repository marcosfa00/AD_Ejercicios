package envinopmongo;

public class Main {
    public static final String databaseMongo = "test";
    public static void main(String[] args) {
        System.out.println("EJERCICIO: envinopmongo AD");


        Methods monfodb = new Methods();
        monfodb.getConnection("test");
        monfodb.getAnalisisdb();
        monfodb.cerrarCliente();

    }
}

/**
 * test> db.xerado.find()
 * [
 *   {
 *     _id: 'a1',
 *     nomeuva: 'alvarinho',
 *     trataacidez: 'subir acidez',
 *     total: 30
 *   }
 * ]
 */