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
                    verProdutosCliente.mostrarMenu();
                    break;
                case 3:
                    if(verCarrinho.mostrarMenu()==1)
                        verProdutosCliente.mostrarMenu();
                    break;
                case 4:
                    cliente.exibirDados(scanner);
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
