package dialog;
import model.Colaborador;
import model.Mercado;

import javax.swing.*;
import java.awt.*;

public class FuncionarioCadastroDialog extends JDialog {

    private Colaborador colaborador;
    private Mercado mercado;
    private boolean cadastroConcluido;

    public FuncionarioCadastroDialog(JFrame parent, Colaborador colaborador, Mercado mercado) {
        super(parent, "Adicionar Funcionario", true);
        this.colaborador = colaborador;
        this.mercado = mercado;
        this.cadastroConcluido = false;
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
        JTextField nomeField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(207, 250, 151));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField emailField = new JTextField(15);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(new Color(207, 250, 151));
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField senhaField = new JTextField(15);

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setForeground(new Color(207, 250, 151));
        cargoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JComboBox<String> cargoComboBox = new JComboBox<>(new String[]{"Admin", "Colaborador"});  // Add options

        JLabel codigoLabel = new JLabel("Código:");
        codigoLabel.setForeground(new Color(207, 250, 151));
        codigoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        JTextField codigoField = new JTextField(15);

        // Add form fields to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(senhaLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(senhaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(cargoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(cargoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(codigoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(codigoField, gbc);

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
            String email = emailField.getText();
            String senha = senhaField.getText();
            String cargo = (String) cargoComboBox.getSelectedItem();
            String codigo = codigoField.getText();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cargo == null || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    colaborador.setNome(nome);
                    colaborador.setEmail(email);
                    colaborador.setSenha(senha);
                    colaborador.setCargo(cargo);
                    int cdg = Integer.parseInt(codigo);
                    colaborador.setCodigo(cdg);
                    cadastroConcluido = true;
                    setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Código inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
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

    public boolean isColaboradorCadastrado() {
        return cadastroConcluido;
    }

    static class Cargo {
        private final String text;
        private final int value;

        public Cargo(String text, int value) {
            this.text = text;
            this.value = value;
        }

        @Override
        public String toString() {
            return text;
        }

        public int getValue() {
            return value;
        }
    }
}
