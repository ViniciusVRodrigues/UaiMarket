import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Mercado implements Serializable{
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Tipo> tipos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private Cliente cliente;

    Mercado(){
        cadastrarTipos();
        cadastrarAdmin();
        cadastrarProdutos();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public Produto getProduto(int index){
        return produtos.get(index);
    }

    public Produto getProdutoById(long id){
        return produtos.stream()
                .filter(produto -> produto.getId()==id)
                .findAny()
                .orElse(null);
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }


    public ArrayList<Produto> buscarProd(String busca) {
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(busca.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Produto> buscarProdPorTipo(String busca, Tipo tipo) {
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(busca.toLowerCase()) && produto.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void vincularCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void addCliente(Cliente c){
        clientes.add(c);
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
        salvarMercado();
    }

    public ArrayList<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void addColaborador(Colaborador c){
        colaboradores.add(c);
        salvarMercado();
    }

    public void setColaboradores(ArrayList<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
        salvarMercado();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido p) {
        pedidos.add(p);
        salvarMercado();
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
        salvarMercado();
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
        salvarMercado();
    }

    public void delProduto(Produto produto) {
        produtos.remove(produto);
        salvarMercado();
    }

    public void delColaborador(Colaborador colaborador){

        colaboradores.remove(colaborador);
        salvarMercado();
    }

    public boolean fazerPedido(Scanner scanner){
        Pedido pedido = cliente.fazerPedido(scanner);
        if(pedido==null)
            return false;
        List<ProdutoCarrinho> pCList= cliente.getCarrinho().getProdutos();
        for (ProdutoCarrinho pC : pCList) {
            Produto produto = getProdutoById(pC.getId());
            produto.removerQuantidadeEstoque(pC.getQuantidade());
        }
        pedidos.add(pedido);
        cliente.confirmarPedido(pedido);
        salvarMercado();
        return true;
    }

    public void cadastrarAdmin(){
        colaboradores.add(new Colaborador("Admin","admin","admin",1,"Admin"));
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

    public void cadastrarProdutos(){
        produtos.add(new Produto(0, tipos.get(3), "Coca Cola", "Coca Cola", 7.50f, 23));
        produtos.add(new Produto(1, tipos.get(0), "Bife de Alcatra", "Açougue do Zé", 39.90f, 10));
        produtos.add(new Produto(2, tipos.get(1), "Pão sem glúten", "Schär", 15.00f, 15));
        produtos.add(new Produto(3, tipos.get(2), "Leite sem lactose", "Piracanjuba", 6.50f, 30));
        produtos.add(new Produto(4, tipos.get(4), "Vodka", "Absolut", 89.90f, 12));
        produtos.add(new Produto(5, tipos.get(5), "Pizza congelada", "Sadia", 19.90f, 20));
        produtos.add(new Produto(6, tipos.get(6), "Milho verde enlatado", "Bonduelle", 4.90f, 50));
        produtos.add(new Produto(7, tipos.get(7), "Maçã", "Fazenda Verde", 3.99f, 100));
        produtos.add(new Produto(8, tipos.get(8), "Queijo Mussarela", "Tirol", 29.90f, 25));
        produtos.add(new Produto(9, tipos.get(9), "Sabonete", "Dove", 2.99f, 200));
        produtos.add(new Produto(10, tipos.get(10), "Iogurte natural", "Vigor", 4.50f, 35));
        produtos.add(new Produto(11, tipos.get(11), "Detergente", "Ypê", 1.99f, 150));
        produtos.add(new Produto(12, tipos.get(12), "Macarrão", "Barilla", 6.00f, 40));
        produtos.add(new Produto(13, tipos.get(13), "Arroz", "Tio João", 15.90f, 80));
        produtos.add(new Produto(14, tipos.get(14), "Pão francês", "Padaria Pão Quente", 0.50f, 300));
        produtos.add(new Produto(15, tipos.get(15), "Salmão", "Peixaria do Mar", 49.90f, 15));
        produtos.add(new Produto(16, tipos.get(16), "Batata frita", "Ruffles", 7.50f, 60));
        produtos.add(new Produto(17, tipos.get(17), "Coxinha", "Casa da Coxinha", 3.00f, 70));
        produtos.add(new Produto(18, tipos.get(3), "Água Mineral", "Crystal", 1.50f, 500));
        produtos.add(new Produto(19, tipos.get(0), "Costela Bovina", "Açougue do Zé", 34.90f, 20));
        produtos.add(new Produto(20, tipos.get(1), "Biscoito sem glúten", "Belive", 10.00f, 40));
        produtos.add(new Produto(21, tipos.get(2), "Queijo sem lactose", "Verde Campo", 12.50f, 25));
        produtos.add(new Produto(22, tipos.get(4), "Whisky", "Johnnie Walker", 129.90f, 10));
        produtos.add(new Produto(23, tipos.get(5), "Hambúrguer congelado", "Seara", 16.90f, 30));
        produtos.add(new Produto(24, tipos.get(6), "Sardinha enlatada", "Gomes da Costa", 7.90f, 45));
        produtos.add(new Produto(25, tipos.get(7), "Banana", "Fazenda Amarela", 2.99f, 120));
        produtos.add(new Produto(26, tipos.get(8), "Presunto", "Sadia", 24.90f, 40));
        produtos.add(new Produto(27, tipos.get(9), "Shampoo", "Pantene", 12.90f, 80));
        produtos.add(new Produto(28, tipos.get(10), "Manteiga", "Itambé", 8.90f, 50));
        produtos.add(new Produto(29, tipos.get(11), "Desinfetante", "Lysol", 9.90f, 60));
    }

    public void salvarMercado() {
        String fileName= "Mercado.txt";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar!");
        }
    }

    public void carregarMercado() {
        try {
            String fileName= "Mercado.txt";
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Mercado mercado= (Mercado) ois.readObject();
            ois.close();
            this.produtos = mercado.getProdutos();
            this.colaboradores = mercado.getColaboradores();
            this.clientes = mercado.getClientes();
            this.pedidos = mercado.getPedidos();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println("Erro ao carregar!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar!");
        }
    }
}
