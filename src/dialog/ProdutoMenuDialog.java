package dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.Produto;
import model.Mercado;
import notification.ControleEstoqueNotificador;

public class ProdutoMenuDialog extends JDialog {

    private Produto produto;
    private Mercado mercado;
    private ControleEstoqueNotificador controleEstoqueNotificador;

    public ProdutoMenuDialog(JFrame parent, Produto produto, Mercado mercado) {
        super(parent, "Menu Produto", true);
        this.produto = produto;
        this.mercado = mercado;
        setupUI();
        this.controleEstoqueNotificador = ControleEstoqueNotificador.getInstance();
    }

    //Configurando o layout da tela
    private void setupUI() {
        // Configurações da janela
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Título
        JLabel label = new JLabel("Produto: " + produto.getNome(), SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(new Color(99, 130, 62));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(label, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Botões
        // Adiciona botões
        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        buttonPanel.add(atualizarButton);

        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);
        buttonPanel.add(deletarButton);

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);
        buttonPanel.add(voltarButton);

        // Adiciona ação aos botões
        atualizarButton.addActionListener(e -> showUpdateOptions());
        deletarButton.addActionListener(e -> showDeleteConfirmation());
        voltarButton.addActionListener(e -> setVisible(false));

        // Adiciona o painel de botões ao centro da tela
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Configuração dos botões
    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(207, 250, 151), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
    }

    // Mostra as opções de atualização
    private void showUpdateOptions() {
        // Cria a janela de diálogo
        JDialog updateDialog = new JDialog(this, "Atualizar Produto", true);
        updateDialog.setSize(300, 200);
        updateDialog.setLocationRelativeTo(this);
        updateDialog.setLayout(new GridLayout(3, 1, 10, 10));

        // Botões
        JButton atualizarEstoqueButton = new JButton("Atualizar Estoque");
        configurarBotao(atualizarEstoqueButton);
        atualizarEstoqueButton.addActionListener(e -> {
            updateDialog.setVisible(false);
            showUpdateEstoqueDialog();
        });

        JButton atualizarPrecoButton = new JButton("Atualizar Preço");
        configurarBotao(atualizarPrecoButton);
        atualizarPrecoButton.addActionListener(e -> {
            updateDialog.setVisible(false);
            showUpdatePrecoDialog();
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> updateDialog.setVisible(false));

        // Adiciona os botões à janela
        updateDialog.add(atualizarEstoqueButton);
        updateDialog.add(atualizarPrecoButton);
        updateDialog.add(cancelarButton);

        // Exibe a janela
        updateDialog.setVisible(true);
    }

    // Mostra a janela de atualização de estoque
    private void showUpdateEstoqueDialog() {

        // Cria a janela de diálogo
        JDialog estoqueDialog = new JDialog(this, "Atualizar Estoque", true);
        estoqueDialog.setSize(300, 200);
        estoqueDialog.setLocationRelativeTo(this);
        estoqueDialog.setLayout(new BorderLayout(10, 10));

        // Label
        JLabel label = new JLabel("Digite a nova quantidade:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        estoqueDialog.add(label, BorderLayout.NORTH);

        // Campo de texto
        JTextField quantidadeField = new JTextField();
        quantidadeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        estoqueDialog.add(quantidadeField, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botões
        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        atualizarButton.addActionListener(e -> {
            try {
                int quantidade = Integer.parseInt(quantidadeField.getText());
                produto.getEstoque().setQntd(quantidade);
                mercado.salvarMercado();
                controleEstoqueNotificador.notificar("Estoque de "+produto.getNome()+" atualizado para " + quantidade + " unidades.");
                JOptionPane.showMessageDialog(this, "Estoque atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                estoqueDialog.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> estoqueDialog.setVisible(false));

        // Adiciona os botões ao painel
        buttonPanel.add(atualizarButton);
        buttonPanel.add(cancelarButton);
        estoqueDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Exibe a janela
        estoqueDialog.setVisible(true);
    }

    // Mostra a janela de confirmação de exclusão
    private void showUpdatePrecoDialog() {
        // Cria a janela de diálogo
        JDialog precoDialog = new JDialog(this, "Atualizar Preço", true);
        precoDialog.setSize(300, 200);
        precoDialog.setLocationRelativeTo(this);
        precoDialog.setLayout(new BorderLayout(10, 10));

        // Label
        JLabel label = new JLabel("Digite o novo preço:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        precoDialog.add(label, BorderLayout.NORTH);

        // Campo de texto
        JTextField precoField = new JTextField();
        precoField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        precoDialog.add(precoField, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botões
        // Botão de atualizar
        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        atualizarButton.addActionListener(e -> {
            // Tenta atualizar o preço
            try {
                float preco = Float.parseFloat(precoField.getText());
                // Atualiza o preço do produto
                produto.setPreco(preco);
                mercado.salvarMercado();
                controleEstoqueNotificador.notificar("Preço de "+produto.getNome()+" atualizado para R$ " + preco);
                JOptionPane.showMessageDialog(this, "Preço atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                precoDialog.setVisible(false);
            } catch (NumberFormatException ex) {
                // Exibe uma mensagem de erro se o preço não for um número
                JOptionPane.showMessageDialog(this, "Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botão de cancelar
        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> precoDialog.setVisible(false));

        // Adiciona os botões ao painel
        buttonPanel.add(atualizarButton);
        buttonPanel.add(cancelarButton);
        precoDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Exibe a janela
        precoDialog.setVisible(true);
    }

    // Mostra a janela de confirmação de exclusão
    private void showDeleteConfirmation() {
        // Cria a janela de diálogo
        JDialog deleteDialog = new JDialog(this, "Deletar Produto", true);
        deleteDialog.setSize(300, 150);
        deleteDialog.setLocationRelativeTo(this);
        deleteDialog.setLayout(new BorderLayout(10, 10));

        // Label
        JLabel label = new JLabel("Tem certeza que deseja deletar o produto?", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteDialog.add(label, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botões
        // Botão de deletar
        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);
        deletarButton.addActionListener(e -> {
            // Remove o produto do mercado
            controleEstoqueNotificador.notificar("Produto "+produto.getNome()+" deletado.");
            mercado.delProduto(produto);
            deleteDialog.setVisible(false);
            setVisible(false);
        });

        // Botão de cancelar
        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> deleteDialog.setVisible(false));

        // Adiciona os botões ao painel
        buttonPanel.add(deletarButton);
        buttonPanel.add(cancelarButton);
        deleteDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Exibe a janela
        deleteDialog.setVisible(true);
    }
}

