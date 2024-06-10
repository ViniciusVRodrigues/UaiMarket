package dialog;

import javax.swing.*;
import java.awt.*;
import model.Produto;
import model.Mercado;
import model.Tipo;

public class ProdutoCadastroDialog extends JDialog {

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

    //Configurando o layout da tela
    private void setupUI() {
        // Configurações da janela
        setSize(400, 350);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        // Painel para o formulário
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(99, 130, 62));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos do formulário
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

        JLabel tipoLabel = new JLabel("model.Tipo:");
        tipoLabel.setForeground(new Color(207, 250, 151));
        tipoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JComboBox<Tipo> tipoComboBox = new JComboBox<>(mercado.getTipos().toArray(new Tipo[0]));

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setForeground(new Color(207, 250, 151));
        quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField quantidadeField = new JTextField();

        // Adicionando os campos ao formulário
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

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões
        // Botão para salvar o produto
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        salvarButton.setForeground(new Color(38, 46, 28));
        salvarButton.setBackground(new Color(159, 191, 117));
        salvarButton.setPreferredSize(new Dimension(90, 40));
        configurarBotao(salvarButton);

        // Botão para cancelar
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(new Font("Arial", Font.PLAIN, 12));
        cancelarButton.setForeground(new Color(38, 46, 28));
        cancelarButton.setBackground(new Color(159, 191, 117));
        cancelarButton.setPreferredSize(new Dimension(90, 40));
        configurarBotao(cancelarButton);

        // Adicionando ação aos botões
        // Salva o produto
        salvarButton.addActionListener(e -> {
            // Obtém os valores dos campos
            String nome = nomeField.getText();
            String marca = marcaField.getText();
            String precoStr = precoField.getText();
            String quantidadeStr = quantidadeField.getText();
            Tipo tipo = (Tipo) tipoComboBox.getSelectedItem();

            // Verifica se os campos estão preenchidos
            if (nome.isEmpty() || marca.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty() || tipo == null) {
                // Exibe uma mensagem de erro
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Tenta converter o preço e a quantidade para float e int, respectivamente
                try {
                    // Cria um novo produto com os valores dos campos
                    float preco = Float.parseFloat(precoStr);
                    int quantidade = Integer.parseInt(quantidadeStr);
                    produto.setId(mercado.getProdutos().size());
                    produto.setNome(nome);
                    produto.setMarca(marca);
                    produto.setPreco(preco);
                    produto.setTipo(tipo);
                    produto.getEstoque().setQntd(quantidade);
                    // Define que o produto foi cadastrado
                    produtoCadastrado = true;
                    System.out.println(produto);
                    setVisible(false);
                } catch (NumberFormatException ex) {
                    // Exibe uma mensagem de erro se o preço ou a quantidade não forem números
                    JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Fecha a janela
        cancelarButton.addActionListener(e -> setVisible(false));

        // Adiciona os botões ao painel
        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        // Adiciona os painéis à janela
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Configuração do botão
    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(new Color(159, 191, 117));
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(99, 130, 62), 1));
    }

    // Verifica se o produto foi cadastrado
    public boolean isProdutoCadastrado() {
        return produtoCadastrado;
    }
}
