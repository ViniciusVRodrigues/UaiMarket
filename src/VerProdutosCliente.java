import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VerProdutosCliente {
    private Mercado mercado;

    private Produto produtoSelecionado;
    private Scanner scanner;

    public VerProdutosCliente(Mercado m, Scanner s) {
        mercado = m;
        scanner = s;
    }

    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n---- Ver Produtos ----");
            System.out.println("1. Lista de produtos disponíveis");
            System.out.println("2. Lista de produtos por tipo");
            System.out.println("3. Lista de tipos");
            System.out.println("4. Pesquisar produto por nome");
            System.out.println("5. Pesquisar produto por tipo");
            System.out.println("0. Voltar");
            System.out.println("Digite uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    mostrarProdutosDisponiveis();
                    break;
                case 2:
                    mostrarProdutosPorTipo();
                    break;
                case 3:
                    listarTipos();
                    break;
                case 4:
                    pesquisarProdutoPorNome();
                    break;
                case 5:
                    pesquisarProdutoPorTipo();
                    break;
                case 0:
                    mostrando = false;
                    System.out.println("Saindo..");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void mostrarProdutosDisponiveis(){
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        for (int i = 0; i < produtosDisponiveis.size(); i++) {
            Produto produto = produtosDisponiveis.get(i);
            if(produto.getQuantidadeEstoque()>0){
                System.out.println(produto);
            }
        }
        selecionarProduto();
    }
    public void mostrarProdutosPorTipo(){
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        Map<Tipo, List<Produto>> produtosPorTipo = produtosDisponiveis.stream()
                .collect(Collectors.groupingBy(Produto::getTipo));

        // Imprimindo os produtos agrupados por tipo
        produtosPorTipo.forEach((tipo, produtos) -> {
            System.out.println("\n" + tipo.getNome()+":");
            produtos.forEach(System.out::println);
        });
        selecionarProduto();
    }
    public void listarTipos(){
        List<Tipo> tipos = mercado.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println(i+". "+tipos.get(i));
        }
    }
    public void pesquisarProdutoPorNome(){
        System.out.println("\nDigite o nome que deseja pesquisar:");
        String nomePesquisa = scanner.nextLine();
        List<Produto> produtosPesquisa = mercado.buscarProd(nomePesquisa);
        for (int i = 0; i < produtosPesquisa.size(); i++) {
            Produto produto = produtosPesquisa.get(i);
            if(produto.getQuantidadeEstoque()>0){
                System.out.println(produto);
            }
        }
        selecionarProduto();
    }
    public void pesquisarProdutoPorTipo(){
        listarTipos();
        System.out.println("\nDigite o id do tipo que deseja pesquisar:");
        int idTipo = scanner.nextInt();
        scanner.nextLine();
        Tipo tipoBusca = mercado.getTipos().get(idTipo);
        System.out.println("\nDigite o nome do produto que deseja pesquisar (deixe vazio caso queira todos do tipo):");
        String nomePesquisa = scanner.nextLine();
        List<Produto> produtosPesquisa = mercado.buscarProdPorTipo(nomePesquisa,tipoBusca);
        for (int i = 0; i < produtosPesquisa.size(); i++) {
            Produto produto = produtosPesquisa.get(i);
            if(produto.getQuantidadeEstoque()>0){
                System.out.println(produto);
            }
        }
        selecionarProduto();
    }

    public void opcoesProduto(){
        System.out.println("\n---- Ver Produto ----");
        System.out.println(produtoSelecionado);
        System.out.println("1. Adicionar produto ao carrinho");
        System.out.println("0. Voltar");
        System.out.println("Digite uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao){
            case 1:
                while(true){
                    int quantEstoque = produtoSelecionado.getQuantidadeEstoque();
                    System.out.println("Digite a quantidade desejada ("+quantEstoque+" em estoque): ");
                    System.out.println("0=Voltar");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    if(quantidade<=0){
                        System.out.println("Voltando...");
                        break;
                    }
                    if(quantidade>quantEstoque){
                        System.out.println("Quantidade indisponível no estoque!");
                        continue;
                    }
                    Cliente cliente = mercado.getCliente();
                    cliente.getCarrinho().addProduto(produtoSelecionado,quantidade);
                    float totalProdutos = produtoSelecionado.getPreco()*quantidade;
                    System.out.printf("\nProduto %s no valor de R$%.2f (%d*R$%.2f) adicionado ao carrinho, novo total do carrinho: R$%.2f%n",produtoSelecionado.getNome(),totalProdutos,quantidade,produtoSelecionado.getPreco(),cliente.getCarrinho().getValorTotalProduto());
                    return;
                }
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void selecionarProduto(){
        System.out.println("\nDeseja selecionar um produto?");
        System.out.println("1. Sim");
        System.out.println("0. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        if(opcao==1){
            System.out.println("Digite o id:");
            long idProduto = scanner.nextLong();
            scanner.nextLine();
            produtoSelecionado = mercado.getProdutoById(idProduto);
            if(produtoSelecionado==null){
                System.out.println("Produto não encontrado!");
                return;
            }
            opcoesProduto();
            return;
        }
        System.out.println("Ok, retornando!");
    }
}
