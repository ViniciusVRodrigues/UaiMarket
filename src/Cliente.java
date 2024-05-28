import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Pessoa{
    private String cpf;
    private Carrinho carrinho;
    private EnderecoEntrega enderecoEntrega;
    private ArrayList<Pedido> pedidos;

    public Cliente(){
        super();
        this.cpf = "00000000000";
        this.carrinho = new Carrinho();
        this.enderecoEntrega = new EnderecoEntrega();
        this.pedidos =  new ArrayList<>();
    }
    public Cliente(String nome, String email, String senha, String cpf){
        super(nome,email,senha);
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    public ArrayList<Pedido> getPedido() {
        return pedidos;
    }
    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public Carrinho getCarrinho() {
        return carrinho;
    }
    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
    public void criarCarrinho(){
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public int cadastrarCliente(Scanner scanner){
        System.out.println("**** Criar conta ( Digite 'Cancelar' para cancelar");

        System.out.println("Digite seu nome: ");
        String input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.nome = input;

        System.out.println("Digite seu email:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.email = input;

        System.out.println("Digite sua senha:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.senha = input;

        System.out.println("Digite seu cpf:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.cpf = input;

        System.out.println("\nCliente cadastrado com sucesso!");
        return 1;
    }

    public void exibirDados() {
        System.out.println("\n**** Dados do Cliente ****");
        System.out.println("Nome: " + this.nome);
        System.out.println("Email: " + this.email);
        System.out.println("Senha: " + this.senha);
        System.out.println("CPF: " + this.cpf);

    }

    private boolean testeSair(String s) {
        if (s.equals("Cancelar")) {
            System.out.println("Cancelando...");
            return true;
        }
        return false;
    }

}
