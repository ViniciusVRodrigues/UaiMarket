import java.util.ArrayList;

public class Carrinho {
    private float valorTotalProduto;
    private ArrayList<Produto> listProdutos;

    public Carrinho(){
        this.valorTotalProduto = 00.0f;
        this.listProdutos = new ArrayList<>();
    }
    public Carrinho(float valorTotalProduto, ArrayList<Produto> listProdutos){
        this.valorTotalProduto = valorTotalProduto;
        this.listProdutos = listProdutos;
    }
    public float getValorTotalProduto() {
        return valorTotalProduto;
    }
    public ArrayList<Produto> getListProdutos() {
        return listProdutos;
    }

    public void addProduto(){
    }
    public void delProduto(){
    }
}
