package view;

import model.Mercado;

import java.util.List;
import java.util.Scanner;
import model.Colaborador;


public class VerColaboradores {

    private Scanner scanner;
    private Mercado mercado;

    private Colaborador colaboradorSelecionado;
    private Colaborador colaboradorLogado;
    private int idSelecionado;

    public VerColaboradores(Mercado mercado, Colaborador colaboradorLogado) {
        this.scanner = new Scanner(System.in);
        this.mercado = mercado;
        this.colaboradorLogado = colaboradorLogado;
    }

    public void mostrarMenu() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Ver colaboradores ---");

            System.out.println("1. Selecionar");
            System.out.println("2. Adicionar");
            System.out.println("0. Voltar");
            System.out.println("Escolha uma opção: ");

            int opcao = getIntInput();

            switch (opcao) {
                case 1:
                    mostrarColaboradores();
                    break;
                case 2:
                    Colaborador colaborador = new Colaborador();
                    if(colaborador.cadastrarFuncionario(scanner)==1){
                        mercado.addColaborador(colaborador);
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


    private void mostrarColaboradores() {
        System.out.println("\n--- Colaboradores disponíveis ---");
        List<Colaborador> colaboradores = mercado.getColaboradores();
        for (int i = 0; i < colaboradores.size(); i++) {
            System.out.println((i + 1) + ". " + colaboradores.get(i));
        }
        System.out.print("Selecione o ID do colaborador: ");
        int idSelecionado = getIntInput();

        if (idSelecionado > 0 && idSelecionado <= colaboradores.size()) {
            colaboradorSelecionado = colaboradores.get(idSelecionado - 1);
            mostrarMenuColaborador();
        } else {
            System.out.println("ID de colaborador inválido. Tente novamente.");
        }
    }

    private void mostrarMenuColaborador() {
        boolean mostrando = true;
        while (mostrando) {
            System.out.println("\n--- Colaborador selecionado: " + colaboradorSelecionado.getNome() + " ---");
            System.out.println("1. Atualizar");
            System.out.println("2. Deletar");
            System.out.println("0. Voltar ");
            System.out.println("Digite uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    colaboradorSelecionado.atualizarColaborador(scanner);
                    break;
                case 2:
                    mercado.delColaborador(colaboradorSelecionado);
                    System.out.println("Colaborador deletado!");
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
