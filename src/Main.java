import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                MenuCliente menu = new MenuCliente(cliente);
                menu.mostrarMenu();
            } else if (opcao == 2) {
                Mercado mercado = new Mercado();
                MenuColaborador menu = new MenuColaborador(mercado);
                if (menu.login()) {
                    menu.mostrarMenu();
                } else {}
                System.out.println("\nEncerrando...");
            } else if (opcao == 0) {
                mostrando = false;
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida");
            }

        }

    }
}