package envinopmongo;

public class Analisis {
    private String idAnalisis ="";
    private int acidez =0;
    private int grao= 0;
    private String tipodeuva ="";
    private int cantidade = 0;
    private String dni = "";

    public Analisis(String idAnalisis, int acidez, int grao, String tipodeuva, int cantidade, String dni) {
        this.idAnalisis = idAnalisis;
        this.acidez = acidez;
        this.grao = grao;
        this.tipodeuva = tipodeuva;
        this.cantidade = cantidade;
        this.dni = dni;
    }

    public Analisis(){
        this.idAnalisis = "";
    }

    public String getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(String idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public int getAcidez() {
        return acidez;
    }

    public void setAcidez(int acidez) {
        this.acidez = acidez;
    }

    public int getGrao() {
        return grao;
    }

    public void setGrao(int grao) {
        this.grao = grao;
    }

    public String getTipodeuva() {
        return tipodeuva;
    }

    public void setTipodeuva(String tipodeuva) {
        this.tipodeuva = tipodeuva;
    }

    public int getCantidade() {
        return cantidade;
    }

    public void setCantidade(int cantidade) {
        this.cantidade = cantidade;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Analisis{" +
                "idAnalisis='" + idAnalisis + '\'' +
                ", acidez=" + acidez +
                ", grao=" + grao +
                ", tipodeuva='" + tipodeuva + '\'' +
                ", cantidade=" + cantidade +
                ", dni='" + dni + '\'' +
                '}';
    }
}
