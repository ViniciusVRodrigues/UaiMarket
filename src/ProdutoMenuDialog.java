import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class ProdutoMenuDialog extends JDialog {

    private Produto produto;
    private Mercado mercado;

    public ProdutoMenuDialog(JFrame parent, Produto produto, Mercado mercado) {
        super(parent, "Menu Produto", true);
        this.produto = produto;
        this.mercado = mercado;
        setupUI();
    }

    private void setupUI() {
        setSize(300, 200);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Produto: " + produto.getNome(), SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);

        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        atualizarButton.addActionListener(e -> atualizarProduto());
        deletarButton.addActionListener(e -> deletarProduto());
        voltarButton.addActionListener(e -> setVisible(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(atualizarButton);
        buttonPanel.add(deletarButton);
        buttonPanel.add(voltarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.BLACK);
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(207, 250, 151), 1));
    }

    private void atualizarProduto() {
        String[] options = {"Atualizar estoque", "Atualizar preço"};
        int option = JOptionPane.showOptionDialog(this, "Escolha uma opção:", "Atualizar Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (option) {
            case 0:
                String quantidadeStr = JOptionPane.showInputDialog(this, "Digite a nova quantidade:");
                if (quantidadeStr != null) {
                    try {
                        int quantidade = Integer.parseInt(quantidadeStr);
                        produto.getEstoque().setQntd(quantidade);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case 1:
                String precoStr = JOptionPane.showInputDialog(this, "Digite o novo preço:");
                if (precoStr != null) {
                    try {
                        float preco = Float.parseFloat(precoStr);
                        produto.setPreco(preco);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        }
        setVisible(false);
    }

    private void deletarProduto() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar o produto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            mercado.delProduto(produto);
            setVisible(false);
        }
    }
}
