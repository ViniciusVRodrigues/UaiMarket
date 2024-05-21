import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class VerProdutos {

    private Scanner scanner;
    private Mercado mercado;

    public VerProdutos(Mercado mercado) {
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

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarProdutos();
                    break;
                case 2:
                    Produto produto = new Produto();
                    if(produto.cadastrarProduto(scanner,mercado.getTipos())==1){
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
        System.out.println("Selecione o ID do produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        if (idProduto > 0 && idProduto <= produtos.size()) {
            mostrarMenuProduto(idProduto);
        } else {
            System.out.println("ID de produto inválido. Tente novamente.");
        }
    }

    private void mostrarMenuProduto(int idProduto) {
        boolean mostrando = true;
        while (mostrando) {
            List<Produto> produtos = mercado.getProdutos();
            System.out.println("\n--- Produto selecionado: " + produtos.get(idProduto - 1) + " ---");
            System.out.println("1. Atualizar");
            System.out.println("2. Deletar");
            System.out.println("3. Editar");
            System.out.println("0. Voltar ");
            System.out.println("Digite uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Atualizando produto...");
                    break;
                case 2:
                    System.out.println("Deletando produto... ");
                    break;
                case 3:
                    System.out.println("Editando produto...");
                case 0:
                    mostrando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

}
