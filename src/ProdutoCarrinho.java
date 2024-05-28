public class ProdutoCarrinho extends  Produto{
    private int quantidade;
    private float precoTotal;
    ProdutoCarrinho(Produto p,int quantidade){
       super(p.getId(),p.getTipo(),p.getNome(),p.getMarca(),p.getPreco(),p.getQuantidadeEstoque());
        this.quantidade = quantidade;
        precoTotal = quantidade*p.getPreco();
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarUm(){
        quantidade++;
        precoTotal = getPreco()*quantidade;
    }

    public void removerUm(){
        if(quantidade==0)
            return;
        quantidade--;
        precoTotal = getPreco() * quantidade;
    }
}
