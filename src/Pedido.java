import java.io.Serializable;

public class Pedido implements Serializable {
    private float frete;
    private EnderecoEntrega enderecoEntrega;
    private Carrinho carrinho;
    private Cliente cliente;
    private float valorTotalPedido;

    public Pedido(){
        this.frete = 00.0F;
        this.enderecoEntrega = new EnderecoEntrega();
        this.carrinho= new Carrinho();
        this.valorTotalPedido=0f;
    }
    public Pedido (float frete, EnderecoEntrega enderecoEntrega, Carrinho carrinho,Cliente cliente){
        this.frete = frete;
        this.enderecoEntrega = enderecoEntrega;
        this.carrinho = carrinho;
        calcTotal();
    }
    public float getFrete() {
        return frete;
    }
    public void calcTotal(){
        valorTotalPedido= carrinho.getValorTotalProduto()+frete;
    }

    public float getValorTotalPedido() {
        return valorTotalPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String toCSVLine(String sep){
        return cliente.getNome()+sep+cliente.getCpf()+sep+cliente.getEmail()+sep+valorTotalPedido;
    }
}
