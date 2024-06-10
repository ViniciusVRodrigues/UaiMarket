import javax.swing.*;
import java.awt.*;

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
        setSize(300, 200);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Funcionario: " + colaborador.getNome(), SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        JButton atualizarButton = new JButton("Atualizar");
        configurarBotao(atualizarButton);

        JButton deletarButton = new JButton("Deletar");
        configurarBotao(deletarButton);

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        atualizarButton.addActionListener(e -> atualizarFuncionario());
        deletarButton.addActionListener(e -> deletarFuncionario());
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
