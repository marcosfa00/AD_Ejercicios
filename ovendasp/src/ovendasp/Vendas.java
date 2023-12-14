
package ovendasp;

import java.io.Serializable;
import java.util.logging.Logger;


public class Vendas implements Serializable{
  int nv;
  String codp;
  int cantv;
  String des;

    public Vendas() {
    }

    public Vendas(int nv, String codp, int cantv, String des) {
        this.nv = nv;
        this.codp = codp;
        this.cantv = cantv;
        this.des = des;
    }

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public String getCodp() {
        return codp;
    }

    public void setCodp(String codp) {
        this.codp = codp;
    }

    public int getCantv() {
        return cantv;
    }

    public void setCantv(int cantv) {
        this.cantv = cantv;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
  
    
}
