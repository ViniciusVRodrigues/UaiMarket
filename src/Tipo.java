import java.io.Serializable;

public class Tipo  implements Serializable {
    protected String nome;

    public Tipo(){
        this.nome = "xxxx";
    }
    public Tipo(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public String toString(){
        return nome;
    }
    public String toCSVLine(String sep){
        return nome;
    }
}
