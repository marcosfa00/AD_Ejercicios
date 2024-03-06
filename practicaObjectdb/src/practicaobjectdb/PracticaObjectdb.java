
package practicaobjectdb;

public class PracticaObjectdb {
    public static void main(String[] args) {
        Methods m = new Methods();
        m.conectionODB();
      //  m.insert();
        m.getMediaX();
        m.getTotalElements();
        m.getAllElements();
        m.getPointByID(9);
        m.UpdatePointByID(9);
        m.closingConection();
    }

}
