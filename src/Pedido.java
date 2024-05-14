public class Pedido {
    private float frete;
    private EnderecoEntrega enderecoEntrega;
    private Carrinho carrinho;
    public Pedido(){
        this.frete = 00.0F;
        this.enderecoEntrega = new EnderecoEntrega();
        this.carrinho= new Carrinho();
    }
    public Pedido (float frete, EnderecoEntrega enderecoEntrega, Carrinho carrinho){
        this.frete = frete;
        this.enderecoEntrega = enderecoEntrega;
        this.carrinho = carrinho;
    }
    public float getFrete() {
        return frete;
    }
    public void calcTotal(){
    }
}
