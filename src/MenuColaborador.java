import java.util.ArrayList;
import java.util.Scanner;

public class MenuColaborador {
    private Mercado mercado;
    private Scanner scanner;

    private VerProdutos verProdutos;

    public MenuColaborador(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutos = new VerProdutos(mercado);
        this.scanner = new Scanner(System.in);
    }



    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Menu Colaborador ---");
            System.out.println("1. Ver produtos");
            System.out.println("2. Cadastrar funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    verProdutos.mostrarMenu();
                    break;
                case 2:
                    System.out.println("Cadastrando funcionários...");
                    break;
                case 0:
                    mostrando = false;
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


}