package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Colaborador extends Pessoa {
    private int codigo;
    private String cargo;
    public Colaborador(){
        super();
        this.codigo = 0000;
        this.cargo = "xxxx";
    }
    public Colaborador(String nome, String email, String senha, int codigo, String cargo){
        super(nome, email, senha);
        this.codigo = codigo;
        this.cargo = cargo;
    }

    public String toCSVLine(String sep){
        return nome+sep+email+sep+senha+sep+codigo+sep+cargo;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean logar(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public int cadastrarFuncionario(Scanner scanner) {
        System.out.println("---- Adicionar model.Colaborador(Digite 'Cancelar' para cancelar) ----");

        System.out.println("Digite o nome:");
        String input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.nome = input;

        System.out.println("Digite o email:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.email = input;

        System.out.println("Digite a senha:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.senha = input;

        System.out.println("Digite a codigo:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.codigo = Integer.parseInt(input);

        System.out.println("Digite a cargo (1=Admin,2=model.Colaborador):");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.cargo = input.equals("1") ? "Admin" : "model.Colaborador";

        System.out.println("\nmodel.Colaborador cadastrado com sucesso!");
        return 1;
    }

    public void atualizarColaborador(Scanner scanner) {
        boolean mostrar = true;
        while (mostrar) {
            System.out.println("---- ATUALIZAR ----");
            System.out.println("1 - Atualizar nome");
            System.out.println("2 - Atualizar email");
            System.out.println("3 - Atualizar senha");
            System.out.println("4 - Atualizar codigo");
            System.out.println("5 - Atualizar cargo");
            System.out.println("0 - Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    System.out.println("\n---- Nome ----");
                    System.out.println("Nome atual: " + this.nome);
                    System.out.println("Digite o novo nome:");
                    nome = scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n---- Email ----");
                    System.out.println("Email atual: " + this.email);
                    System.out.println("Digite o novo email:");
                    email = scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n---- Senha ----");
                    System.out.println("Senha atual: " + this.senha);
                    System.out.println("Digite a nova senha:");
                    senha = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\n---- Código ----");
                    System.out.println("Código atual: " + this.codigo);
                    System.out.println("Digite o novo codigo:");
                    senha = scanner.nextLine();
                    break;
                case 5:
                    System.out.println("\n---- Cargo ----");
                    System.out.println("Cargo atual: " + this.cargo);
                    System.out.println("Digite o novo cargo (1=Admin,2=model.Colaborador):");
                    cargo = scanner.nextLine().equals("1")? "Admin": "model.Colaborador";
                    break;
                case 0:
                    System.out.println("Voltando...");
                    mostrar = false;
                    break;
                default:
                    System.out.println("Valor inválido!");
                    break;
            }
        }
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setSenha(String senha) {
        super.setSenha(senha);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private boolean testeSair(String s) {
        if (s.equals("Cancelar")) {
            System.out.println("Cancelando...");
            return true;
        }
        return false;
    }

    public String toString() {
        return "Nome=" + nome + ", Email= " + email + ", Código= " + codigo + " ,Cargo= " + cargo;
    }

}




