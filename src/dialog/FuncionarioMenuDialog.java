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
        String[] options = {"Atualizar nome", "Atualizar email"};
        int option = JOptionPane.showOptionDialog(this, "Escolha uma opção:", "Atualizar Funcionario",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (option) {
            case 0:
                String nomeStr = JOptionPane.showInputDialog(this, "Digite o novo nome:");
                if (nomeStr != null) {
                    colaborador.setNome(nomeStr);
                    mercado.salvarMercado();
                }
                break;
            case 1:
                String emailStr = JOptionPane.showInputDialog(this, "Digite o novo email:");
                if (emailStr != null) {
                    colaborador.setEmail(emailStr);
                    mercado.salvarMercado();
                }
                break;
        }
        setVisible(false);
    }

    private void deletarFuncionario() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar o funcionário?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            mercado.delColaborador(colaborador);
            setVisible(false);
        }
    }
}

