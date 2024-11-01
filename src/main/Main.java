package main;
import exception.IncorrectCredentialsException;
import menu.MenuCliente;
import menu.MenuColaborador;
import model.Cliente;
import model.Mercado;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Mercado mercado = Mercado.getInstance();
        mercado.carregarMercado();
        mercado.salvarMercado();
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n**** SELECIONE O MENU DESEJADO ****");
            System.out.println("1. Menu model.Cliente");
            System.out.println("2. Menu model.Colaborador");
            System.out.println("0. Sair");
            System.out.println("Digite a opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                Cliente cliente = new Cliente();
                MenuCliente menu = new MenuCliente();

            } else if (opcao == 2) {
                MenuColaborador menu = new MenuColaborador();
                try {
                    if (menu.login()) {
                        menu.mostrarMenu();
                    }
                } catch (IncorrectCredentialsException e) {
                    System.out.println(e.getMessage());
                }
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