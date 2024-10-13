package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dialog.FuncionarioCadastroDialog;
import model.Colaborador;
import model.Mercado;
import model.Produto;
import model.Tipo;
import dialog.FuncionarioMenuDialog;

public class VerFuncionariosColaborador extends JFrame {

    private Mercado mercado;
    private JTable tabela;
    private DefaultTableModel tableModel;

    public VerFuncionariosColaborador() {
        this.mercado = Mercado.getInstance();
        setupUI();
    }

    private void setupUI() {
        setTitle("Ver Funcionários");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(159, 191, 117));

        // Table Model
        tableModel = new DefaultTableModel(new String[]{"Nome", "Email", "Código", "Cargo"}, 0);
        tabela = new JTable(tableModel);
        configurarTabela(tabela);
        atualizarTabela();

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(tabela);
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

    private void atualizarTabela() {
        tableModel.setRowCount(0);  // Clear existing rows
        List<Colaborador> colaboradores = mercado.getColaboradores();
        for (Colaborador c : colaboradores) {
            tableModel.addRow(new Object[]{
                    c.getNome(),
                    c.getEmail(),
                    c.getCodigo(),
                    c.getCargo()
            });
        }
    }

    private void mostrarMenuProduto() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            String email = (String) tableModel.getValueAt(selectedRow, 1);
            Colaborador colaboradorSelecionado = mercado.getColaboradorByEmail(email);
            new FuncionarioMenuDialog(this, colaboradorSelecionado, mercado).setVisible(true);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionario!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarProduto() {
        Colaborador colaborador = new Colaborador();
        FuncionarioCadastroDialog dialog = new FuncionarioCadastroDialog(this, colaborador, mercado);
        dialog.setVisible(true);
        if (dialog.isColaboradorCadastrado()) {
            mercado.addColaborador(colaborador);
            atualizarTabela();
        }
    }

    public static void main(String[] args) {
        // Dummy data for testing
        Mercado mercado = Mercado.getInstance();
        mercado.addProduto(new Produto(1, new Tipo(1, "Bebida"), "Coca-Cola", "Coca-Cola", 5.0f, 10));
        mercado.addProduto(new Produto(2, new Tipo(2, "Comida"), "Arroz", "Tio João", 20.0f, 5));

        SwingUtilities.invokeLater(() -> new VerFuncionariosColaborador().setVisible(true));
    }
}
