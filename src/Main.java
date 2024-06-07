import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Mercado mercado = new Mercado();
        mercado.carregarMercado();
        mercado.salvarMercado();
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n**** SELECIONE O MENU DESEJADO ****");
            System.out.println("1. Menu Cliente");
            System.out.println("2. Menu Colaborador");
            System.out.println("0. Sair");
            System.out.println("Digite a opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                Cliente cliente = new Cliente();
                MenuCliente menu = new MenuCliente(mercado,cliente);
                menu.mostrarMenu();
            } else if (opcao == 2) {
                MenuColaborador menu = new MenuColaborador(mercado);
                if (menu.login()) {
                    menu.mostrarMenu();
                } else {}
                System.out.println("\nEncerrando...");
            } else if (opcao == 0) {
                mercado.salvarMercado();
                mostrando = false;
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida");
            }

        }

    }
}