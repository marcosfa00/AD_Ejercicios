

package practicaobjectdb;

import java.util.List;
import javax.persistence.*;



public class Methods {
    //Para trabaajr con object db hay que crear un entity manager y un entity Manager Factory
    EntityManager entityManager;
    
    EntityManagerFactory entityManagerFactory;
    
    //Creamos la conexión con ObjectDB
    public void conectionODB(){
        entityManagerFactory = Persistence.createEntityManagerFactory("$objectdb/db/puntos.odb");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    /**
     * Creamos un insert en nuestra base de datos 
     * (Si esta no esta creada se crea automaticamente)
     * 
     * Insertaremos un Punto
     */
    public void insert(){
        entityManager.getTransaction().begin();
        for (int i = 0; i <10; i++) {
            Point p = new Point(i,i);
            //Tras crear el nuevo punto lo insertamos pro cada iteración
            entityManager.persist(p);
        }
        entityManager.getTransaction().commit(); // hacemos un commit del insert al final de las inserciones
        
            
   }
    
    
    /**
     * Funcion para obtener todos los elementos insertados
     * Si queremos coger Varios elementos utilizaremso 
     *  [TypedQuery] , pero si solo queremos un elemento 
     * nos llega con usar 
     *  [Query]
     */
     public void getMediaX(){
         Query avgPointsX = entityManager.createQuery("SELECT AVG(p.x) FROM Point p"); //Aui estamos obteniento la media de x
         System.out.println("El resultado de la media es: " + avgPointsX.getSingleResult()); //Utilizaremos getSingle Result ya que solo queremos obtener 1 resultado
         
     }
     
     /**
      * Sería exactamente los mismo con un Count
      */
      public void getTotalElements(){
         Query numberOfPoints = entityManager.createQuery("SELECT Count(p) FROM Point p"); //Aui estamos obteniento la media de x
         System.out.println("El total de elementos es: " + numberOfPoints.getSingleResult()); //Utilizaremos getSingle Result ya que solo queremos obtener 1 resultado
         
     }
      
      /**
       * Pero y si queremos obtenet todos los elementos de la lista para mas adelanta trabajar con ellos?
       * Aqui siq ue deberemos usar TYpedQuery
       * 
       */
      public void getAllElements(){
          TypedQuery<Point> selectAll = entityManager.createQuery("Select a from Point a", Point.class);
          //Ahora creamos la lista de elementos que queremso obtener
          List<Point> result = selectAll.getResultList();
          //Mosramos estos elementos
          for(Point elements : result){
              //Así mostramos el elemento entero, pero y si queremso mostrar su id?
            //  System.out.println(elements);
              //Mostrar el id: 
              System.out.println(
                      "id: " + entityManagerFactory.getPersistenceUnitUtil().getIdentifier(elements) //Obtiene el id del elemento
                      +
                              " elemento: --> " + elements
                        
              );
          }
          
      }
      /**
       * Esto se puede hacer de dos maneras, obteniendo todos y luego pasandole el ID con getId de la clase Point , oo con la siguiente
       */
     
      public void getPointByID(int id){
          Point puntoPorId = entityManager.find(Point.class, id);
          if(puntoPorId != null){
              System.out.println("El puntoObtenido con el id: " + id+" es: "+ puntoPorId);
          }else{
              System.out.println("No se ha encontrado");
          }
          
      }
      
      /**
       * Vamos ahora a actualizar un punto Obtenido con ID
       */
      public void UpdatePointByID(int id){
          entityManager.getTransaction().begin();
          //Ahora lanzaremos la query de actualización, si devuelve 1 es que actualizó, si devuelve 0 que no actualizó nada
          int actualizado = entityManager.createQuery("UPDATE Point p SET p.y = p.y + 2 WHERE p.id = :id")
                  .setParameter("y", id)
                  .executeUpdate();
          entityManager.getTransaction().commit();
          
          if (actualizado >0) {
              entityManager.clear();
              getPointByID(id);
              System.out.println("Actualizado");
          }else{
              
          }
          
          
      }
      
      
      /**
       * Ahora en vez de actualizar solo un Punto, vamos a actualizar toda la taba siempre y cuando 
       * Y sea mayor que 1000
       */
      public void massiveUpdate(int y){
          entityManager.getTransaction().begin();
          int actualizado = entityManager.createQuery("UPDATE Point p SET p.y = 1000 WHERE p.y < :y")
                  .setParameter("y", y)
                  .executeUpdate();
          entityManager.getTransaction().commit();
          if (actualizado>0) {
              System.out.println("Se ha actualizado correctamente");
              
          }else{
              System.out.println("Error al actualizar");
          }
          
      }
      
      /*
      Creamos una condicion donde Eliminamos muchos elementos de la tabla mientras cumplan una condicion
      */
      
      public void masiveDelete(int y){
          entityManager.getTransaction().begin();
          //al igual que en un uodate devolvera os eleemntos actualizados (nuemro)
          int delete = entityManager.createQuery("DELETE FROM Point p WHERE p.y < :y")
                  .setParameter("y", y)
                  .executeUpdate();
          if (delete > 0) {
              System.out.println("Registros correctamente eliminados");
          }else{
              System.out.println("no se ha eliminado nada");
          }
          
          
      }
      
      /**
       * Eliminar todos los elementos de la base de datos
       */
      public void eliminarTodo(){
          entityManager.getTransaction().begin();
          //seleccionamos toda la tabla
          TypedQuery<Point> remove = entityManager.createQuery("SELECT p FROM Point p", Point.class);
          
          //Obtenemos una lista de elementos y vamos eliminando uno a uno
          List<Point> listaElementos = remove.getResultList();
          for(Point elements : listaElementos){
              entityManager.remove(elements);
          }
      }
      
      /**
       * Eliminar un solo elementos por un ID
       */
      public void deleteElementByID(int id){
          entityManager.getTransaction().begin();
          
          int deleted = entityManager.createQuery("DELETE FROM Point p WHERE p.id = :id" )
                  .setParameter("id", id)
                  .executeUpdate();
          entityManager.getTransaction().commit();
          
          if (deleted >0){
              System.out.println("Eliminado");
          }else{
              System.out.println("Nada Eliminado");
          }
          
      }
     
     /*
      Despues siempre cerraremso la coenxión
      */
       public void closingConection(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
    
    

}
