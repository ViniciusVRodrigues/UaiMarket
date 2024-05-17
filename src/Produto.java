public class Produto {
    private String nome;
    private Tipo tipo;
    private String marca;
    private float preco;
    private Estoque estoque = new Estoque();
    public Produto(){
        super();
        this.nome = "xxxx";
        this.marca = "xxxxx";
        this.preco = 00.0f;
    }
    public Produto(Tipo tipo, String nome, String marca, float preco, int quantidade){
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.estoque.setQuantidade(quantidade);
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
