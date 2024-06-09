import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente extends JFrame {
    private Mercado mercado;

    public MenuCliente(Mercado mercado) {
        this.mercado = mercado;

        setTitle("Menu Cliente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 191, 117));


        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton criarContaButton = new JButton("Criar/Entrar Conta");
        criarContaButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        criarContaButton.setBackground(new Color(218, 255, 172));
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar funcionalidade para criar ou entrar em uma conta
            }
        });

        JButton verProdutosButton = new JButton("Ver Produtos");
        verProdutosButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verProdutosButton.setBackground(new Color(218, 255, 172));
        verProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar funcionalidade para ver produtos
            }
        });

        JButton verCarrinhoButton = new JButton("Ver Carrinho");
        verCarrinhoButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verCarrinhoButton.setBackground(new Color(218, 255, 172));
        verCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar funcionalidade para ver carrinho
            }
        });

        JButton verDadosButton = new JButton("Ver Dados");
        verDadosButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verDadosButton.setBackground(new Color(218, 255, 172));
        verDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar funcionalidade para ver dados
            }
        });

        JButton sairButton = new JButton("Sair");
        sairButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        sairButton.setBackground(new Color(218, 255, 172));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(criarContaButton);
        buttonPanel.add(verProdutosButton);
        buttonPanel.add(verCarrinhoButton);
        buttonPanel.add(verDadosButton);
        buttonPanel.add(sairButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Mercado mercado = new Mercado(); // Substitua pelo seu construtor real, se houver
                MenuCliente menuCliente = new MenuCliente(mercado);
                menuCliente.setVisible(true);
            }
        });
    }
}

