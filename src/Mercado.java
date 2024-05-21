import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mercado {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Tipo> tipos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    Mercado(){
        tipos.add(new Tipo("Padaria"));
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }
    public ArrayList<Produto> buscarProd(String busca){
        ArrayList<Produto> produtosBusca = produtos;
        return produtosBusca;
    }
    public ArrayList<Produto> BuscarProdPorTipo(String busca, Tipo tipo){
        ArrayList<Produto> produtosBusca = produtos;
        return produtosBusca;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente c){
        clientes.add(c);
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void addColaborador(Colaborador c){
        colaboradores.add(c);
    }

    public void setColaboradores(ArrayList<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido p) {
        pedidos.add(p);
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public void addTipo(String nome){
        tipos.add(new Tipo(nome));
    }

}
