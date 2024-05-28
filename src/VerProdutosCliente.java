import java.util.List;
import java.util.Scanner;

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
        System.out.println("Deseja selecionar um produto? (1=Sim/0=Não)");
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
    public void mostrarProdutosPorTipo(){

    }
    public void listarTipos(){

    }
    public void pesquisarProdutoPorNome(){

    }
    public void pesquisarProdutoPorTipo(){

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
                    System.out.println("Produto adicionado no carrinho!");
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
}
