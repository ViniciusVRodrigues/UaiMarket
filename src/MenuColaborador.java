import java.util.ArrayList;
import java.util.Scanner;

public class MenuColaborador {
    private Mercado mercado;
    private Scanner scanner;

    private VerProdutos verProdutos;

    private VerColaboradores verColaboradores;

    private Colaborador colaboradorLogado;

    public MenuColaborador(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutos = new VerProdutos(mercado);
        this.verColaboradores = new VerColaboradores(mercado,colaboradorLogado);
        this.scanner = new Scanner(System.in);
    }



    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Menu Colaborador ---");
            System.out.println("1. Ver produtos");
            if(colaboradorLogado.getCargo().equals("Admin")) System.out.println("2. Gerenciar funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    verProdutos.mostrarMenu();
                    break;
                case 2:
                    if(colaboradorLogado.getCargo().equals("Admin")) verColaboradores.mostrarMenu();
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

    public boolean login(){
        int tentarLogar = 1;
        while (tentarLogar==1){
            System.out.println("\n--- Login Colaborador ---");
            System.out.println("Digite seu email:");
            String email = scanner.nextLine();
            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();
            for (Colaborador colaborador: mercado.getColaboradores()){
                if(colaborador.logar(email,senha)){
                    colaboradorLogado = colaborador;
                    return true;
                };
            }
            System.out.println("\nLogin errado! Tentar novamente?\nSim = 1\nNão = 0");
            tentarLogar = scanner.nextInt();
            scanner.nextLine();
        }
        return false;
    }


}