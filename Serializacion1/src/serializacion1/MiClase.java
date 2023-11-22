

package serializacion1;



import java.io.Serializable;


public class MiClase implements Serializable{
     private String nombre;
     //private int numero;
     private transient int numero;
     private double numero2;
   

   
    
    public MiClase(String nombre,int numero,double numero2){
        this.nombre = nombre;
        this.numero = numero;
        this.numero2 = numero2;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getNumero2() {
        return numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }
    

    @Override
    public String toString() {
        return "MiClase{" + "nombre=" + nombre + ", numero=" + numero + ", numero2=" + numero2 + '}';
    }

   
    
   

}
