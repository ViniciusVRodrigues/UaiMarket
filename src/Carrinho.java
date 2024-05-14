import java.util.ArrayList;

public class Carrinho {
    private float valorTotalProdutos;
    private ArrayList<ProdutoCarrinho> produtos;

    public Carrinho(){
        this.valorTotalProdutos = 00.0f;
        this.produtos = new ArrayList<>();
    }
    public Carrinho(ArrayList<ProdutoCarrinho> produtos){
        this.produtos = produtos;
    }
    public float getValorTotalProduto() {
        return valorTotalProdutos;
    }
    public ArrayList<ProdutoCarrinho> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto p){
        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(p,1);
        produtos.add(produtoCarrinho);
        valorTotalProdutos = 0;
        for(ProdutoCarrinho pC : produtos)
            valorTotalProdutos+= pC.getPrecoTotal();
    }
    public void removeProduto(ProdutoCarrinho produtoCarrinho){
        produtos.remove(produtoCarrinho);
        for(ProdutoCarrinho pC : produtos)
            valorTotalProdutos+= pC.getPrecoTotal();
    }
}
