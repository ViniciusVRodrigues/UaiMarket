import java.util.List;
import java.util.Scanner;

public class Colaborador extends Pessoa{
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
        System.out.println("---- Adicionar Colaborador(Digite 'Cancelar' para cancelar) ----");

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

        System.out.println("Digite a cargo (1=Admin,2=Colaborador):");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.cargo = input.equals("1") ? "Admin" : "Colaborador";

        System.out.println("\nColaborador Cadastrado com sucesso!");
        return 1;
    }

    public void atualizarColaborador(Scanner scanner){
        
    }

    private boolean testeSair(String s) {
        if (s.equals("Cancelar")) {
            System.out.println("Cancelando...");
            return true;
        }
        return false;
    }
}




