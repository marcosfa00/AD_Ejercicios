

package psecreto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class Methods {

    /**
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
     public static ArrayList<Clave1> readSerializado(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
        ArrayList<Clave1>list = new ArrayList<>();
        Clave1 x;
        while((x = (Clave1) reader.readObject())!= null){
               //añadimos los objetos a la lista
            list.add(x);
            
        }
         showArraylist(list);
        return list;
    }
     /**
      * Muestra por consola los valores de la lista
      * @param lista 
      */
     public static void showArraylist(ArrayList<Clave1> lista){
         for (Clave1 clave1 : lista) {
             System.out.println(clave1.getCla1() + "  |  "+ clave1.getNum1());
         }
     }
     
     public static void readDelimitado(String path,int numClave1, int numClave2) throws FileNotFoundException, IOException{
         BufferedReader reader = new BufferedReader(new FileReader(path));
         int suma = numClave1 + numClave2;
            String read;
            while ((read = reader.readLine()) != null) {
                String[] partes = read.split("_");
                int[] numeros = new int[partes.length / 2];
                String letra = "";
                 
                for (int i = 0; i < partes.length; i += 2) {
                    numeros[i / 2] = Integer.parseInt(partes[i]);
                    if (suma == numeros[i / 2]) {
                        letra = partes[i + 1];
                        break;
                    }
                }

                if (!letra.isEmpty()) {
                    //System.out.println(numClave1 + " "+numClave2);
                    System.out.print(letra +" ");
                    
                }
     }
}
     

}
