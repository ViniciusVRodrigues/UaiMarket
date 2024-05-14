public class ProdutoCarrinho extends  Produto{
    private int quantidade;
    private double precoTotal;
    ProdutoCarrinho(Produto p,int quantidade){
        super(p.getTipo(),p.getNome(),p.getMarca(),p.getPreco());
        this.quantidade = quantidade;
        precoTotal = quantidade*p.getPreco();
    }

    public double getPrecoTotal() {
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
