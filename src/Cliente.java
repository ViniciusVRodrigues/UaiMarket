import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Pessoa {
    private String cpf;
    private Carrinho carrinho;
    private EnderecoEntrega enderecoEntrega;

    public Cliente(){
        super();
        this.cpf = "00000000000";
        this.carrinho = new Carrinho();
        this.enderecoEntrega = new EnderecoEntrega();
    }
    public Cliente(String nome, String email, String senha, String cpf){
        super(nome,email,senha);
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
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

    public String toCSVLine(String sep){
        return nome+sep+email+sep+senha+sep+cpf+sep+enderecoEntrega.getCidade()+sep+enderecoEntrega.getEstado();
    }

    public Pedido fazerPedido(Scanner scanner){
        int frete = 10;
        if(carrinho.getValorTotalProduto()>=100)
            frete=0;
        System.out.println("\n---- Efetuar compra ----");
        carrinho.printProdutos();
        System.out.println("Endereço de entrega: "+enderecoEntrega);
        System.out.println("Frete: R$"+frete+",00");
        Pedido pedido = new Pedido(frete,enderecoEntrega,carrinho,this);
        System.out.println("Valor Total Pedido: R$"+pedido.getValorTotalPedido());
        System.out.println("\nTem certeza que deseja efetuar compra?");
        System.out.println("1. Confirmar");
        System.out.println("0. Voltar");
        int confirmacao = scanner.nextInt();
        scanner.nextLine();
        if(confirmacao==1){
            return pedido;
        }
        return null;
    }

    public int cadastrarCliente(Scanner scanner, Mercado mercado){
        System.out.println("**** Criar conta ( Digite 'Cancelar' para cancelar");

        System.out.println("Digite seu nome: ");
        String input = scanner.nextLine();
        this.nome = input;
        while (true){
            System.out.println("Digite seu email:");
            input = scanner.nextLine();
            this.email = input;
            if(mercado.verificarEmail(email)){
                break;
            }else{
                System.out.println("Email já utilizado!");
            }
        }



        System.out.println("Digite sua senha:");
        input = scanner.nextLine();
        this.senha = input;

        System.out.println("Digite seu cpf:");
        input = scanner.nextLine();
        this.cpf = input;

        System.out.println("ENDEREÇO DE ENTREGA");

        System.out.println("Digite o nome da rua: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setRua(input);

        System.out.println("Digite o número: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setNumero(Integer.parseInt(input));

        System.out.println("Digite o bairro: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setBairro(input);

        System.out.println("Digite o complemento: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setComplemento(input);

        System.out.println("Digite a cidade: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setCidade(input);

        System.out.println("Digite o estado: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setEstado(input);

        System.out.println("Digite o cep: ");
        input = scanner.nextLine();
        this.enderecoEntrega.setCep(input);

        System.out.println("\nCliente cadastrado com sucesso!");
        mercado.cadastrarCliente(this);
        return 1;
    }

    public boolean logar(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void exibirDados(Scanner scanner, Mercado mercado) {

        boolean mostrando = true;

        while (mostrando) {

            System.out.println("\n**** Dados do Cliente ****");
            System.out.println("Nome: " + this.nome);
            System.out.println("Email: " + this.email);
            System.out.println("Senha: " + this.senha);
            System.out.println("CPF: " + this.cpf);

            System.out.println("\n----ENDEREÇO DE ENTREGA---\n");
            System.out.println("Rua: " + this.enderecoEntrega.getRua());
            System.out.println("Numero " + this.enderecoEntrega.getNumero());
            System.out.println("Bairro: " + this.enderecoEntrega.getBairro());
            System.out.println("Complemento: " + this.enderecoEntrega.getComplemento());
            System.out.println("Cidade: " + this.enderecoEntrega.getCidade());
            System.out.println("Estado: " + this.enderecoEntrega.getEstado());
            System.out.println("CEP: " + this.enderecoEntrega.getCep());

            System.out.println("\n1 - Editar dados");
            System.out.println("0 - Voltar");
            System.out.println("Digite a opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                editarDados(scanner, mercado);
            } else if (opcao == 0) {
                mostrando = false;
            } else {
                System.out.println("Opção inválida");
            }
        }

    }

    public void editarDados(Scanner scanner, Mercado mercado) {
        boolean mostrando = true;

        while (mostrando) {
        System.out.println("**** EDITAR DADOS ****");
        System.out.println("1 - Editar nome");
        System.out.println("2 - Editar email");
        System.out.println("3 - Editar CPF");
        System.out.println("4 - Editar senha");
        System.out.println("5 - Editar nome da rua");
        System.out.println("6 - Editar numero");
        System.out.println("7 - Editar bairro");
        System.out.println("8 - Editar complemento");
        System.out.println("9 - Editar cidade");
        System.out.println("10 - Editar estado");
        System.out.println("11 - Editar cep");
        System.out.println("0 - Voltar");
        System.out.println("Digite a opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                this.nome = scanner.nextLine();
                mercado.salvarMercado();
                break;
            case 2:
                System.out.print("Digite o novo email: ");
                this.email = scanner.nextLine();
                mercado.salvarMercado();
                break;
            case 3:
                System.out.print("Digite o novo CPF: ");
                this.cpf = scanner.nextLine();
                mercado.salvarMercado();
                break;
            case 4:
                System.out.print("Digite a nova senha: ");
                this.senha = scanner.nextLine();
                mercado.salvarMercado();
                break;
            case 5:
                System.out.print("Digite o novo nome da rua: ");
                this.enderecoEntrega.setRua(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 6:
                System.out.print("Digite o novo número: ");
                this.enderecoEntrega.setNumero(scanner.nextInt());
                scanner.nextLine();
                mercado.salvarMercado();
                break;
            case 7:
                System.out.print("Digite o novo bairro: ");
                this.enderecoEntrega.setBairro(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 8:
                System.out.print("Digite o novo complemento: ");
                this.enderecoEntrega.setComplemento(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 9:
                System.out.print("Digite a nova cidade: ");
                this.enderecoEntrega.setCidade(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 10:
                System.out.print("Digite o novo estado: ");
                this.enderecoEntrega.setEstado(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 11:
                System.out.print("Digite o novo CEP: ");
                this.enderecoEntrega.setCep(scanner.nextLine());
                mercado.salvarMercado();
                break;
            case 0:
                mostrando = false;
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }


//        private boolean testeSair(String s) {
//            if (s.equals("Cancelar")) {
//                System.out.println("Cancelando...");
//                return true;
//            }
//            return false;
//        }

}

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
