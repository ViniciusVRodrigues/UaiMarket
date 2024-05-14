public class Produto{
    private String nome;
    private Tipo tipo;
    private String marca;
    private float preco;
    public Produto(){
        super();
        this.nome = "xxxx";
        this.marca = "xxxxx";
        this.preco = 00.0f;
    }
    public Produto(Tipo tipo, String nome, String marca, float preco){
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }
    public String getMarca() {
        return marca;
    }
    public float getPreco() {
        return preco;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
}
