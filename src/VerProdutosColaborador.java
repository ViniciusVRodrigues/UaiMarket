import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class VerProdutosColaborador extends JFrame {

    private Mercado mercado;
    private JTable produtoTable;
    private DefaultTableModel tableModel;

    public VerProdutosColaborador(Mercado mercado) {
        this.mercado = mercado;
        setupUI();
    }

    private void setupUI() {
        setTitle("Ver Produtos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(159, 191, 117));

        // Table Model
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Marca", "Preço", "Tipo", "Quantidade"}, 0);
        produtoTable = new JTable(tableModel);
        configurarTabela(produtoTable);
        atualizarTabelaProdutos();

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(produtoTable);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Buttons
        JButton selecionarButton = new JButton("Selecionar");
        configurarBotao(selecionarButton);

        JButton adicionarButton = new JButton("Adicionar");
        configurarBotao(adicionarButton);

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        // Listeners
        selecionarButton.addActionListener(e -> mostrarMenuProduto());
        adicionarButton.addActionListener(e -> adicionarProduto());
        voltarButton.addActionListener(e -> setVisible(false));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(selecionarButton);
        buttonPanel.add(adicionarButton);
        buttonPanel.add(voltarButton);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void configurarTabela(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(99, 130, 62));
        table.getTableHeader().setForeground(Color.WHITE);

        // Center align columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setShowGrid(true);
        table.setGridColor(new Color(207, 250, 151));
    }

    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.BLACK);
        botao.setPreferredSize(new Dimension(90, 40));
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(207, 250, 151), 1));
    }

    private void atualizarTabelaProdutos() {
        tableModel.setRowCount(0);  // Clear existing rows
        List<Produto> produtos = mercado.getProdutos();
        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getMarca(),
                    produto.getPreco(),
                    produto.getTipo(),
                    produto.getEstoque().getQntd()
            });
        }
    }

    private void mostrarMenuProduto() {
        int selectedRow = produtoTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Produto produtoSelecionado = mercado.getProdutoById(id);
            new ProdutoMenuDialog(this, produtoSelecionado, mercado).setVisible(true);
            atualizarTabelaProdutos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarProduto() {
        Produto produto = new Produto();
        if (produto.cadastrarProduto(new Scanner(System.in), mercado.getTipos(), mercado.getProdutos().size()) == 1) {
            mercado.addProduto(produto);
            atualizarTabelaProdutos();
        }
    }
}

class ProdutoMenuDialog extends JDialog {

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
        botao.setForeground(new Color(255, 255, 255));
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(207, 250, 151), 1));
    }

    private void atualizarProduto() {
        produto.atualizarProduto(new Scanner(System.in));
        setVisible(false);
    }

    private void deletarProduto() {
        mercado.delProduto(produto);
        setVisible(false);
    }
}
