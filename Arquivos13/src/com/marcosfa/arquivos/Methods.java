
package com.marcosfa.arquivos;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author marcosfa
 */
public class Methods {
    private static File y ;
    /**
     * Este método comprueba si una ruta es un directorio o no
     * @param cadena 
     */
    public static void eDirectorio(String cadena){
        
        File x = new File(cadena);
        if(x.isDirectory()){
            System.out.println("la ruta indicada es un directorio");
        }else{
            System.out.println("No es un directorio");
        }
    
    }
    
    public static boolean eFichero(String cadena){
        File x = new File(cadena);
         if(x.isFile()){
            System.out.println("la ruta indicada es un Fichero");
            return true;
        }else{
            System.out.println("No es un Fichero");
            return false;
        }
    }
    
    public static void creaDirectorio(String cadena) {
        File x = new File(cadena);
        
        if(x.exists() ){
            System.out.println("El directorio ya existe");
        }else{
            x.mkdir();
            System.out.println("Se ha creado el directorio");
        }
        
    }
    
    public static void creaFichero(String ruta,String nombre) throws IOException{
        File x = new File(ruta,nombre);
        if(x.exists()){
            System.out.println("El fichero ya existe");
        }else{
            x.createNewFile();
            System.out.println("Se ha creado el fichero");
        }
    }
    
    public static void modoAcceso(String dirname,String fileName){
        y = new File(dirname , fileName);
        if(y.exists()){
            if (y.canRead()) {
            System.out.println("Se puede leer");
        
            }else {
            System.out.println("No se puede leer");
            }
            
            if(y.canWrite()){
                System.out.println("Se puede escribir");
            }
            else{
                System.out.println("No se puede escribir");
            }
        
        
        }

    }
    
    
    public static void calculaLonxitude(String dirname,String filename){
        y = new File(dirname,filename);
        System.out.println("La longitud del fichero es: " + y.length());
        
    }
    
    public static void mLectura(String dirname,String filename){
        y = new File(dirname,filename);
        y.setReadOnly();
        
    }
    
    public static void mEscritura(String dirname,String filename){
        y = new File(dirname,filename);
        y.setWritable(true);
        
    }
    
    public static void borrarFichero(String dirname,String fileName){
        y = new File(dirname,fileName);
   
            if(y.exists()){
                if (eFichero(dirname+"/"+fileName)==true) {
                     if (y.delete()) {
                        System.out.println("Correctamente eliminado");
                    }

                
                }
               
            }else{
                System.out.println("no existe");
            }
        
        
    }
    
    /**
     * Para borrar un directorio debemos borrar RECURSIVAMENTE ya que si tiene carpetas dentro que hacemos?
     * para ello debemos hacer lo siguiente
     * @param dirname 
     */
    
    public static void borrarDirectorio(String dirname){
        y = new File(dirname);
       
                    //Comprobamos que el archivo es un Directorio
                    if(y.isDirectory()){
                        //Si es así debemos guardar en una matriz la lista de directorios(los que están dentro)
                        File[] files = y.listFiles();
                        //Comnprobamos que la lista no esté vacía
                        if (files != null) {
                            //Debemos hacer un bucle for para eliminar los ficheros de dentro
                            for (File file : files) {

                                /**
                                 * Este If comprueba si el archivo actual es un directorio, si no (es un archivo) lo elimina, y si es un directorio
                                 * vuelve a llamar a la funcion actual desde la ruta actual, y de esta manera busca recursivamente desde el ultimo subdirectorio 
                                 * 
                                 */
                                if (file.isDirectory()) {
                                    borrarDirectorio(file.getAbsolutePath()) ;
                                }else{
                                    file.delete();
                                }



                            }

                        }

                        /**
                         * Una vez entramos en el ultimo subdirectori y vemos que no hay ningun archivo, podemos borrar el directorio,
                         * si no hay archivos no entra en el if anterior, asiq pasamos a elimianr el directorio en si
                         */
                        if (y.delete()) {
                            System.out.println("El directorio se ha eliminado correctamente");
                        }else{
                            System.out.println("Error al borrar el directorio");

                        }
            }
            else{
                System.out.println("NO ES UN DIRECTORIO");
            }
        }
        
    
    
    
    
    
    
    
    /**
     * 11) metodo mContido(dirName) que amose arquivos e directorios de primeiro nivel dunha ruta absoluta dada 
     */
    public static void mContido(String dirName){
        y = new File(dirName);
        
        if (y.exists() && y.isDirectory()) {
                    //Primero debemos obtener un listado de los ficheros que hay en el directorio
                   File[] files = y.listFiles();
                   if (files != null) {
                       for (File file : files) {
                           //Preguntamos si lo actual es un directorio
                           if (file.isDirectory()) {
                               System.out.println("D: " + file);
                               //Si es un directorio volvemos a llamar a la funcion
                               mContido(file.getAbsolutePath());
                               
                           }else{
                               System.out.println(file);
                           }
                       }
                   }

                  
        }
       
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

