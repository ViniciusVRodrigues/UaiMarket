import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuCliente extends JFrame {

    private Scanner scanner;
    private Cliente cliente;

    private Mercado mercado;

    private VerProdutosCliente verProdutosCliente;

    private VerCarrinho verCarrinho;
    public MenuCliente(Mercado mercado, Cliente cliente) {
        this.scanner = new Scanner(System.in);
        this.cliente = cliente;
        this.mercado = mercado;
        mercado.vincularCliente(cliente);
        verProdutosCliente = new VerProdutosCliente(mercado,scanner);
        verCarrinho = new VerCarrinho(mercado,scanner);
    }
    public MenuCliente() {
        setTitle("Menu Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(218, 255, 172)); // cor de fundo branca

        JLabel label = new JLabel("Menu Cliente", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);
    }
    public void mostrarMenu() {
        boolean mostrando = true;
        int atalho = 0;
        while (mostrando) {
            int opcao = 0;
            if(atalho==0){
                System.out.println("\n--- Menu Cliente ---");
                if(mercado.getClienteAutenticado()==false){
                    System.out.println("1. Criar/Entrar em uma conta");
                }else{
                    System.out.println("Bem vindo, "+mercado.getCliente().nome);
                }


                System.out.println("2. Ver produtos");
                System.out.println("3. Ver carrinho");
                System.out.println("4. Ver dados");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
            }else{
                opcao=atalho;
                atalho=0;
            }

            switch (opcao) {
                case 1:
                    if(mercado.getClienteAutenticado()) break;
                    System.out.println("\n--- Criar/Entrar em uma conta ---");
                    System.out.println("1. Criar uma conta");
                    System.out.println("2. Entrar em sua conta");
                    System.out.println("0. Sair");
                    int opcaoCadastro = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcaoCadastro){
                        case 1:
                            cliente.cadastrarCliente(scanner, mercado);
                            break;
                        case 2:
                            System.out.println("\n--- Entrar em uma conta ---");
                            System.out.println("Digite seu email: ");
                            String email = scanner.nextLine();
                            System.out.println("Digite sua senha: ");
                            String senha = scanner.nextLine();
                            for (Cliente cliente1 : mercado.getClientes()) {
                                if (cliente1.logar(email, senha)) {
                                    mercado.vincularCliente(cliente1);
                                    mercado.setClienteAutenticado(true);
                                    System.out.println("Entrou com sucesso!");
                                }else {
                                    System.out.println("Dados incorretos/inválidos!");
                                }
                            }
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }

                    break;
                case 2:
                    atalho = verProdutosCliente.mostrarMenu();
                    break;
                case 3:
                    atalho = verCarrinho.mostrarMenu();
                    break;
                case 4:
                    cliente.exibirDados(scanner, mercado);
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
