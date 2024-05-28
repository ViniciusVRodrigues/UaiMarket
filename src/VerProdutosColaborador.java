import java.util.Scanner;
import java.util.List;

public class VerProdutosColaborador {

    private Scanner scanner;
    private Mercado mercado;

    private Produto produtoSelecionado;
    private int idSelecionado;

    public VerProdutosColaborador(Mercado mercado) {
        this.scanner = new Scanner(System.in);
        this.mercado = mercado;
    }

    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Ver produtos ---");

            System.out.println("1. Selecionar");
            System.out.println("2. Adicionar");
            System.out.println("0. Voltar");
            System.out.println("Escolha uma opção: ");

            int opcao = getIntInput();

            switch (opcao) {
                case 1:
                    mostrarProdutos();
                    break;
                case 2:
                    Produto produto = new Produto();
                    if(produto.cadastrarProduto(scanner,mercado.getTipos(),mercado.getProdutos().size())==1){
                        mercado.addProduto(produto);
                    }
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


    private void mostrarProdutos() {
        System.out.println("\n--- Produtos disponíveis ---");
        List<Produto> produtos = mercado.getProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + ". " + produtos.get(i));
        }
        System.out.print("Selecione o ID do produto: ");
        int idSelecionado = getIntInput();

        if (idSelecionado > 0 && idSelecionado <= produtos.size()) {
            Produto produtoSelecionado = produtos.get(idSelecionado - 1);
            mostrarMenuProduto(produtoSelecionado);
        } else {
            System.out.println("ID de produto inválido. Tente novamente.");
        }
    }

    private void mostrarMenuProduto(Produto produtoSelecionado) {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Produto selecionado: " + produtoSelecionado.getNome() + " ---");
            System.out.println("1. Atualizar");
            System.out.println("2. Deletar");
            System.out.println("0. Voltar ");
            System.out.println("Digite uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    produtoSelecionado.atualizarProduto(scanner);
                    break;
                case 2:
                    mercado.delProduto(produtoSelecionado);
                    System.out.println("Produto deletado ");
                    return;
                case 0:
                    mostrando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

}
