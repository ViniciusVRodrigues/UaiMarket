public class Produto extends Tipo {
    private String nomeProd;
    private String marca;
    private float preco;
    public Produto(){
        super();
        this.nomeProd = "xxxx";
        this.marca = "xxxxx";
        this.preco = 00.0f;
    }
    public Produto(String nome, String nomeProd, String marca, float preco){
        super(nome);
        this.nomeProd = nomeProd;
        this.marca = marca;
        this.preco = preco;
    }

    public String getNomeProd() {
        return nomeProd;
    }
    public String getMarca() {
        return marca;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
}
