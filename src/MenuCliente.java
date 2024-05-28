import java.util.Scanner;

public class MenuCliente {

    private Scanner scanner;
    private Cliente cliente;

    public MenuCliente(Cliente cliente) {
        this.scanner = new Scanner(System.in);
        this.cliente = cliente;
    }

    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Menu Cliente ---");
            System.out.println("1. Criar conta");
            System.out.println("2. Ver produtos");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Ver dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cliente.cadastrarCliente(scanner);
                    break;
                case 2:
                    System.out.println("Vendo produtos");
                    break;
                case 3:
                    System.out.println("Vendo carrinho");
                    break;
                case 4:
                    cliente.exibirDados();
                    break;
                case 0:
                    mostrando = false;
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }


}
