import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroFuncionarioFrame extends JFrame {
    private Mercado mercado;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField codigoField;
    private JComboBox<String> cargoComboBox;
    private DefaultTableModel tableModel;

    public CadastroFuncionarioFrame(Mercado mercado) {
        this.mercado = mercado;

        setTitle("Cadastro de Funcionário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 191, 117));
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(new Color(159, 191, 117));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nomeField = new JTextField();
        emailField = new JTextField();
        senhaField = new JPasswordField();
        codigoField = new JTextField();
        cargoComboBox = new JComboBox<>(new String[]{"Admin", "Colaborador"});

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Senha:"));
        formPanel.add(senhaField);
        formPanel.add(new JLabel("Código:"));
        formPanel.add(codigoField);
        formPanel.add(new JLabel("Cargo:"));
        formPanel.add(cargoComboBox);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBackground(new Color(218, 255, 172));
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setBackground(new Color(218, 255, 172));
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFuncionario();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(atualizarButton);

        String[] columnNames = {"Nome", "Email", "Código", "Cargo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        carregarFuncionarios();
    }

    private void cadastrarFuncionario() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        int codigo = Integer.parseInt(codigoField.getText());
        String cargo = (String) cargoComboBox.getSelectedItem();

        Colaborador novoColaborador = new Colaborador(nome, email, senha, codigo, cargo);
        mercado.adicionarColaborador(novoColaborador);

        carregarFuncionarios();
    }

    private void atualizarFuncionario() {
        // Implementar lógica de atualização de funcionário
    }

    private void carregarFuncionarios() {
        tableModel.setRowCount(0);
        List<Colaborador> colaboradores = mercado.getColaboradores();
        for (Colaborador colaborador : colaboradores) {
            Object[] row = {
                    colaborador.getNome(),
                    colaborador.getEmail(),
                    colaborador.getCodigo(),
                    colaborador.getCargo()
            };
            tableModel.addRow(row);
        }
    }
}
