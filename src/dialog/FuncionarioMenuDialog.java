package dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.Colaborador;
import model.Mercado;


public class FuncionarioMenuDialog extends JDialog {

    private Colaborador colaborador;
    private Mercado mercado;

    public FuncionarioMenuDialog(JFrame parent, Colaborador colaborador, Mercado mercado) {
        super(parent, "Menu Funcionario", true);
        this.colaborador = colaborador;
        this.mercado = mercado;
        setupUI();
    }

    private void setupUI() {
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel label = new JLabel("Funcionario: " + colaborador.getNome(), SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(new Color(99, 130, 62));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        buttonPanel.add(atualizarButton);

        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);
        buttonPanel.add(deletarButton);

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);
        buttonPanel.add(voltarButton);

        atualizarButton.addActionListener(e -> atualizarFuncionario());
        deletarButton.addActionListener(e -> deletarFuncionario());
        voltarButton.addActionListener(e -> setVisible(false));

        add(buttonPanel, BorderLayout.CENTER);
    }

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

    private void atualizarFuncionario() {
        JDialog updateDialog = new JDialog(this, "Atualizar Funcionario", true);
        updateDialog.setSize(300, 200);
        updateDialog.setLocationRelativeTo(this);
        updateDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JButton atualizarNomeButton = new JButton("Atualizar Nome");
        configurarBotao(atualizarNomeButton);
        atualizarNomeButton.addActionListener(e -> {
            updateDialog.setVisible(false);
            showUpdateNomeDialog();
        });

        JButton atualizarEmailButton = new JButton("Atualizar Email");
        configurarBotao(atualizarEmailButton);
        atualizarEmailButton.addActionListener(e -> {
            updateDialog.setVisible(false);
            showUpdateEmailDialog();
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> updateDialog.setVisible(false));

        updateDialog.add(atualizarNomeButton);
        updateDialog.add(atualizarEmailButton);
        updateDialog.add(cancelarButton);

        updateDialog.setVisible(true);
    }

    private void showUpdateNomeDialog() {
        JDialog nomeDialog = new JDialog(this, "Atualizar Nome", true);
        nomeDialog.setSize(300, 200);
        nomeDialog.setLocationRelativeTo(this);
        nomeDialog.setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Digite o novo nome:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nomeDialog.add(label, BorderLayout.NORTH);

        JTextField nomeField = new JTextField();
        nomeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nomeDialog.add(nomeField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        atualizarButton.addActionListener(e -> {
            String nomeStr = nomeField.getText();
            if (!nomeStr.isEmpty()) {
                colaborador.setNome(nomeStr);
                mercado.salvarMercado();
                JOptionPane.showMessageDialog(this, "Nome atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                nomeDialog.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Nome inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> nomeDialog.setVisible(false));

        buttonPanel.add(atualizarButton);
        buttonPanel.add(cancelarButton);
        nomeDialog.add(buttonPanel, BorderLayout.SOUTH);

        nomeDialog.setVisible(true);
    }

    private void showUpdateEmailDialog() {
        JDialog emailDialog = new JDialog(this, "Atualizar Email", true);
        emailDialog.setSize(300, 200);
        emailDialog.setLocationRelativeTo(this);
        emailDialog.setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Digite o novo email:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailDialog.add(label, BorderLayout.NORTH);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailDialog.add(emailField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);
        atualizarButton.addActionListener(e -> {
            String emailStr = emailField.getText();
            if (!emailStr.isEmpty()) {
                colaborador.setEmail(emailStr);
                mercado.salvarMercado();
                JOptionPane.showMessageDialog(this, "Email atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                emailDialog.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Email inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> emailDialog.setVisible(false));

        buttonPanel.add(atualizarButton);
        buttonPanel.add(cancelarButton);
        emailDialog.add(buttonPanel, BorderLayout.SOUTH);

        emailDialog.setVisible(true);
    }


    private void deletarFuncionario() {
        JDialog deleteDialog = new JDialog(this, "Deletar Funcionário", true);
        deleteDialog.setSize(350, 150);
        deleteDialog.setLocationRelativeTo(this);
        deleteDialog.setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Tem certeza que deseja deletar o funcionário?", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteDialog.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);
        deletarButton.addActionListener(e -> {
            mercado.delColaborador(colaborador);
            deleteDialog.setVisible(false);
            setVisible(false);
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.addActionListener(e -> deleteDialog.setVisible(false));

        buttonPanel.add(deletarButton);
        buttonPanel.add(cancelarButton);
        deleteDialog.add(buttonPanel, BorderLayout.SOUTH);

        deleteDialog.setVisible(true);
    }

}

