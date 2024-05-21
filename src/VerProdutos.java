import java.util.Scanner;

public class VerProdutos {

    private Scanner scanner;

    public VerProdutos() {
//        this.produtos =
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Ver produtos ---");
            System.out.println("1. Selecionar");
            System.out.println("0. Voltar");
            System.out.println("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    mostrando = false;
                    System.out.println("Saindo..");
                    break;
            }

        }


    }



}
