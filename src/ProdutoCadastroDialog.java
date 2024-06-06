import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class ProdutoCadastroDialog extends JDialog {

    private Produto produto;
    private Mercado mercado;
    private boolean produtoCadastrado;

    public ProdutoCadastroDialog(JFrame parent, Produto produto, Mercado mercado) {
        super(parent, "Adicionar Produto", true);
        this.produto = produto;
        this.mercado = mercado;
        this.produtoCadastrado = false;
        setupUI();
    }

    private void setupUI() {
        setSize(400, 350);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        // Panel for form fields
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(99, 130, 62));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form fields
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(new Color(207, 250, 151));
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField nomeField = new JTextField();

        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setForeground(new Color(207, 250, 151));
        marcaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField marcaField = new JTextField();

        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setForeground(new Color(207, 250, 151));
        precoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField precoField = new JTextField();

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoLabel.setForeground(new Color(207, 250, 151));
        tipoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JComboBox<Tipo> tipoComboBox = new JComboBox<>(mercado.getTipos().toArray(new Tipo[0]));

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setForeground(new Color(207, 250, 151));
        quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField quantidadeField = new JTextField();

        // Add form fields to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(marcaLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(marcaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(precoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(precoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(tipoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(tipoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(quantidadeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(quantidadeField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        salvarButton.setForeground(new Color(38, 46, 28));
        salvarButton.setBackground(new Color(159, 191, 117));
        salvarButton.setPreferredSize(new Dimension(90, 40));
        configurarBotao(salvarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        cancelarButton.setForeground(new Color(38, 46, 28));
        cancelarButton.setBackground(new Color(159, 191, 117));
        cancelarButton.setPreferredSize(new Dimension(90, 40));
        configurarBotao(cancelarButton);

        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String marca = marcaField.getText();
            String precoStr = precoField.getText();
            String quantidadeStr = quantidadeField.getText();
            Tipo tipo = (Tipo) tipoComboBox.getSelectedItem();

            if (nome.isEmpty() || marca.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty() || tipo == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    float preco = Float.parseFloat(precoStr);
                    int quantidade = Integer.parseInt(quantidadeStr);
                    produto.setNome(nome);
                    produto.setMarca(marca);
                    produto.setPreco(preco);
                    produto.setTipo(tipo);
                    produto.getEstoque().setQntd(quantidade);
                    produtoCadastrado = true;
                    setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(e -> setVisible(false));

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        // Add panels to the main dialog
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(new Color(159, 191, 117));
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(99, 130, 62), 1));
    }

    public boolean isProdutoCadastrado() {
        return produtoCadastrado;
    }
}
