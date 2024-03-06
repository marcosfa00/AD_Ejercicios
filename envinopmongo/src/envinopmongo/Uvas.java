package envinopmongo;

public class Uvas {

    private String id;
    private String nome ="";
    private int acidezmin;
    private int acidezMax;

    public Uvas(){

    }

    public Uvas(String id, String nome, int acidezmin, int acidezMax) {
        this.id = id;
        this.nome = nome;
        this.acidezmin = acidezmin;
        this.acidezMax = acidezMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAcidezmin() {
        return acidezmin;
    }


    @Override
    public String toString() {
        return "Uvas{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", acidezmin=" + acidezmin +
                ", acidezMax=" + acidezMax +
                '}';
    }

    public void setAcidezmin(int acidezmin) {
        this.acidezmin = acidezmin;
    }

    public int getAcidezMax() {
        return acidezMax;
    }

    public void setAcidezMax(int acidezMax) {
        this.acidezMax = acidezMax;
    }
}
