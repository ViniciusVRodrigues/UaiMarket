import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    protected String nome;
    protected String email;
    protected String senha;

    public Pessoa(){
        this.nome = "xxxx xxxx";
        this.email = "xxxx@uaimarket.com";
        this.senha = "Xxxxx123#$";
    }
    public Pessoa(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //criar um método abstrato
}
