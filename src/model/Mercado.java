package model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mercado implements Serializable {
    //Criando listas para armanezar todos os dados do programa
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Tipo> tipos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    //Cliente que atualmente esta usando o mercado
    private Boolean clienteAutenticado = false;
    private Cliente cliente;

    private static Mercado instance;

    //Colaborador que atualmente esta usando o mercado
    private Colaborador colaborador;

    private Mercado() {
        produtos = new ArrayList<>();
        tipos = new ArrayList<>();
        clientes = new ArrayList<>();
        colaboradores = new ArrayList<>();
        pedidos = new ArrayList<>();
        //Funções para adicionar dados 'padrão' ao sistema
        cadastrarTipos();
        cadastrarAdmin();
        cadastrarProdutos();
        //Carregando mercado direto de um arquivo
        carregarMercado();
        //Salvando o mercado
        salvarMercado();
    }

    //Singleton
    //metodo para pegar a instancia da classe mercado
    public static Mercado getInstance() {
        if (instance == null) {
            instance = new Mercado();
        }
        return instance;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Produto getProduto(int index){
        return produtos.get(index);
    }

    //Função para encontrar o produto baseado no recebido por parametro
    public Produto getProdutoById(long id){
        return produtos.stream()
                .filter(produto -> produto.getId()==id)
                .findAny()
                .orElse(null);
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }


    //Função para encontrar o tipo baseado no recebido por parametro
    public ArrayList<Produto> buscarProd(String busca) {
        //Retornando os produtos que tem estoque
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(busca.toLowerCase()) && produto.getEstoque().getQntd()>0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //Função para encontrar o tipo baseado no recebido por parametro
    public ArrayList<Produto> buscarProdPorTipo(Tipo tipo) {
        return produtos.stream()
                .filter(produto -> produto.getTipo().getNome().equals(tipo.getNome()) && produto.getEstoque().getQntd()>0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    //Função que encontra os pedidos do cliente atual
    public List<Pedido> getPedidosCliente(){
        return  pedidos.stream()
                .filter(pedido -> pedido.getCliente().equals(cliente))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //Função que vincula o cliente ao mercado
    public void vincularCliente(Cliente cliente){
        this.clienteAutenticado = true;
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

    //Função para adicionar o colaborador ao mercado
    public void addColaborador(Colaborador c){
        colaboradores.add(c);
        salvarMercado();
    }

    public void setColaboradores(ArrayList<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
        salvarMercado();
    }

    //Encontrando o cliente por email e senha
    public Cliente buscarCliente(String email, String senha){
        return clientes.stream()
                .filter(cliente -> Objects.equals(cliente.getEmail(), email) && Objects.equals(cliente.getSenha(), senha))
                .findAny()
                .orElse(null);
    }
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido p) {
        Carrinho carrinho = p.getCarrinho();
        for (ProdutoCarrinho pc : carrinho.getProdutos()) {
            Produto produto = getProdutoById(pc.getId());
            produto.removerQuantidadeEstoque(pc.getQuantidade());
        }
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

//    public boolean fazerPedido(Scanner scanner){
//        model.Pedido pedido = cliente.fazerPedido(scanner);
//        if(pedido==null)
//            return false;
//        List<model.ProdutoCarrinho> pCList= cliente.getCarrinho().getProdutos();
//        for (model.ProdutoCarrinho pC : pCList) {
//            model.Produto produto = getProdutoById(pC.getId());
//            produto.removerQuantidadeEstoque(pC.getQuantidade());
//        }
//        pedidos.add(pedido);
//        salvarMercado();
//        return true;
//    }

    //Adicionando um admin padrão ao sistema
    public void cadastrarAdmin(){
        colaboradores.add(new Colaborador("Admin","admin","admin",1,"Admin"));
    }

    //Função para adicionar um cliente ao sistema
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        vincularCliente(cliente);
        salvarMercado();
    }

    public void setClienteAutenticado(Boolean clienteAutenticado) {
        this.clienteAutenticado = clienteAutenticado;
    }

    public Boolean getClienteAutenticado() {
        return clienteAutenticado;
    }

    //Função para verificar se o email já esta cadastrado
    public Boolean verificarEmail(String email){
        for(Cliente cliente : clientes){
            if(cliente.email.equals(email)){
                return false;
            }
        }
        return true;
    }

    //Cadastrando tipos padrões
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

    //Cadastrando produtos padrões
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

    //Função para salvar o mercado em um arquivo
    public void salvarMercado() {
        String fileName= "Mercado.txt";
        //Tentando salvar o mercado
        try {
            //Salvando o mercado
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            //Salvando o mercado em csv
            salvarListasEmCSV();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar!");
        }
    }

    //Função para carregar o mercado de um arquivo
    public void carregarMercado() {
        //Tentando carregar o mercado
        try {
            String fileName= "Mercado.txt";
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Mercado mercado= (Mercado) ois.readObject();
            ois.close();
            //Carregando as listas
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

    private final String CSV_SEPARATOR = ";";
    //Função para salvar as listas em arquivos csv
    private void salvarListasEmCSV() {
        //Salvando as listas em arquivos csv
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("produtos.csv"), "UTF-8"));
            for (Produto p : produtos) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(p.toCSVLine(CSV_SEPARATOR));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tipos.csv"), "UTF-8"));
            for (Tipo t : tipos) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(t.toCSVLine(CSV_SEPARATOR));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("clientes.csv"), "UTF-8"));
            for (Cliente c : clientes) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(c.toCSVLine(CSV_SEPARATOR));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("colaboradores.csv"), "UTF-8"));
            for (Colaborador c : colaboradores) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(c.toCSVLine(CSV_SEPARATOR));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pedidos.csv"), "UTF-8"));
            for (Pedido p : pedidos) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(p.toCSVLine(CSV_SEPARATOR));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    public void adicionarColaborador(Colaborador novoColaborador) {
        colaboradores.add(new Colaborador("Natalia", "nati@gmail.com" ,"Admin",123,"Administrador"));
    }

    //Função para adicionar um produto ao carrinho do cliente
    public void adicionarAoCarrinho(Produto produto, int quantidade) {
        cliente.getCarrinho().addProduto(produto,quantidade);
    }

    public void atualizarCliente(Cliente cliente) {

    }

    //Função para encontrar o colaborador por email
    public Colaborador getColaboradorByEmail(String email) {
        return colaboradores.stream()
                .filter(colaborador -> Objects.equals(colaborador.getEmail(), email))
                .findAny()
                .orElse(null);
    }
}