package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.*;

public class VerCarrinho extends JFrame {
    private Mercado mercado;
    private Cliente cliente;
    private Carrinho carrinho;
    private ProdutoCarrinho pCSelecionado;
    private JTextArea carrinhoTextArea;

    public VerCarrinho() {
        this.mercado = Mercado.getInstance();
        this.cliente = mercado.getCliente();

        if (this.cliente == null){
            JOptionPane.showMessageDialog(this, "Você não fez o login, vá para 'Criar/Entrar Conta'", "Erro", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        this.carrinho = cliente.getCarrinho();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Ver Carrinho");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        carrinhoTextArea = new JTextArea();
        carrinhoTextArea.setEditable(false);
        carrinhoTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        carrinhoTextArea.setBackground(new Color(255, 255, 255));
        carrinhoTextArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane(carrinhoTextArea);
        updateCarrinhoText();

        JButton continuarComprandoButton = createButton("Continuar Comprando");
        JButton efetuarCompraButton = createButton("Efetuar Compra");
        JButton removerProdutoButton = createButton("Remover Produto");
        JButton alterarProdutoButton = createButton("Alterar Produto");

        continuarComprandoButton.addActionListener(e -> dispose());
        efetuarCompraButton.addActionListener(e -> efetuarCompra());
        removerProdutoButton.addActionListener(e -> removerProduto());
        alterarProdutoButton.addActionListener(e -> alterarProduto());


        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(218, 255, 172));

        buttonPanel.add(continuarComprandoButton);
        buttonPanel.add(efetuarCompraButton);
        buttonPanel.add(removerProdutoButton);
        buttonPanel.add(alterarProdutoButton);


        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateCarrinhoText() {
        StringBuilder sb = new StringBuilder("Carrinho:\n");
        List<ProdutoCarrinho> produtos = carrinho.getProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            sb.append(i).append(". ").append(produtos.get(i)).append("\n");
        }
        carrinhoTextArea.setText(sb.toString());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(159, 191, 117));
        button.setBorder(BorderFactory.createLineBorder(new Color(207, 250, 151)));
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        return button;
    }

    private void efetuarCompra() {
        if (!mercado.getClienteAutenticado()) {
            JOptionPane.showMessageDialog(this, "Cria ou entre em uma conta para efetuar compra!");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja efetuar compra?", "Confirmar Compra", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Pedido pedido = cliente.fazerPedido(true);
            if (pedido != null) {
                mercado.addPedido(pedido);
                JOptionPane.showMessageDialog(this, "Pedido realizado com sucesso! Valor total: R$" + pedido.getValorTotalPedido());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar o pedido.");
            }
        }
    }

    private void removerProduto() {
        if (selecionarProduto()) {
            int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover esse produto?", "Remover Produto", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                carrinho.removeProduto(pCSelecionado);
                updateCarrinhoText();
            }
        }
    }

    private void alterarProduto() {
        if (selecionarProduto()) {
            String novaQuantStr = JOptionPane.showInputDialog(this, "Qual a nova quantidade desse produto que seja colocar no carrinho?\n(Quantidade em estoque " + pCSelecionado.getQuantidadeEstoque() + ")", "Alterar Quantidade", JOptionPane.PLAIN_MESSAGE);
            if (novaQuantStr != null && !novaQuantStr.isEmpty()) {
                int novaQuant = Integer.parseInt(novaQuantStr);
                if (novaQuant > 0 && novaQuant <= pCSelecionado.getQuantidadeEstoque()) {
                    pCSelecionado.setQuantidade(novaQuant);
                    carrinho.atualizarValorTotalProdutos();
                    updateCarrinhoText();
                    JOptionPane.showMessageDialog(this, "Nova quantidade salva!");
                } else {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida!");
                }
            }
        }
    }

    private boolean selecionarProduto() {
        String opcaoStr = JOptionPane.showInputDialog(this, "Digite o ID do produto que deseja selecionar:", "Selecionar Produto", JOptionPane.PLAIN_MESSAGE);
        if (opcaoStr != null && !opcaoStr.isEmpty()) {
            int opcao = Integer.parseInt(opcaoStr);
            List<ProdutoCarrinho> pCList = carrinho.getProdutos();
            if (opcao >= 0 && opcao < pCList.size()) {
                pCSelecionado = pCList.get(opcao);
                JOptionPane.showMessageDialog(this, "Produto selecionado:\n" + pCSelecionado);
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Produto não encontrado!");
        return false;
    }

    public static void main(String[] args) {
        VerCarrinho verCarrinho = new VerCarrinho();
        verCarrinho.setVisible(true);
    }
}


