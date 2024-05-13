import java.util.ArrayList;

public class Carrinho {
    private float valorTotalProduto;
    private ArrayList<Produto> listProdutos;

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
