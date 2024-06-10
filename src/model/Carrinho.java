package model;
import java.io.Serializable;
import java.util.ArrayList;


import java.io.Serializable;
import java.util.ArrayList;

public class Carrinho  implements Serializable {
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
    public void printProdutos(){
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i+" - "+produtos.get(i));
        }
        System.out.println("----");
        System.out.println("Total: R$"+String.format("%.2f", valorTotalProdutos));
    }
    public void addProduto(Produto p,int quantidade){
        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(p,quantidade);
        produtos.add(produtoCarrinho);
        atualizarValorTotalProdutos();
    }
    public void removeProduto(ProdutoCarrinho produtoCarrinho){
        produtos.remove(produtoCarrinho);
        atualizarValorTotalProdutos();
    }
    public void atualizarValorTotalProdutos(){
        valorTotalProdutos = 0;
        for(ProdutoCarrinho pC : produtos)
            valorTotalProdutos+= pC.getPrecoTotal();
    }
}
