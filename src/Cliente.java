import java.util.ArrayList;

public class Cliente extends Pessoa{
    private long cpf;
    private Carrinho carrinho;
    private EnderecoEntrega enderecoEntrega;
    private ArrayList<Pedido> pedidos;

    public Cliente(){
        super();
        this.cpf = 00000000000;
        this.carrinho = new Carrinho();
        this.enderecoEntrega = new EnderecoEntrega();
        this.pedidos =  new ArrayList<>();
    }
    public Cliente(String nome, String email, String senha, long cpf){
        super(nome,email,senha);
        this.cpf = cpf;
    }
    public long getCpf() {
        return cpf;
    }
    public ArrayList<Pedido> getPedido() {
        return pedidos;
    }
    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public Carrinho getCarrinho() {
        return carrinho;
    }
    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
    public void criarCarrinho(){
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }
}
