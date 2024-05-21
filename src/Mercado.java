import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mercado {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Tipo> tipos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    Mercado(){
        cadastrarTipos();
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

    public void cadastrarTipos(){
        tipos.add(new Tipo("Açougue"));
        tipos.add(new Tipo("Alimentos sem gluten"));
        tipos.add(new Tipo("Alimentos sem lactose"));
        tipos.add(new Tipo("Bebidas"));
        tipos.add(new Tipo("Bebidas Destiladas"));
        tipos.add(new Tipo("Congelados"));
        tipos.add(new Tipo("Enlatados"));
        tipos.add(new Tipo("Frutas e Verduras"));
        tipos.add(new Tipo("Frios"));
        tipos.add(new Tipo("Higiene Pessoal"));
        tipos.add(new Tipo("Laticínios"));
        tipos.add(new Tipo("Limpeza"));
        tipos.add(new Tipo("Massas"));
        tipos.add(new Tipo("Mercearia"));
        tipos.add(new Tipo("Padaria"));
        tipos.add(new Tipo("Peixaria"));
        tipos.add(new Tipo("Petiscos"));
        tipos.add(new Tipo("Salgados"));
    }

}
