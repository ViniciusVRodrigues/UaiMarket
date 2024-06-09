import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuColaborador extends JFrame {
    private Mercado mercado;
    private JTextField emailField;
    private JPasswordField passwordField;

    private VerProdutosColaborador verProdutosColaborador;
    private VerFuncionariosColaborador verFuncionariosColaborador;
    private Colaborador colaboradorLogado;

    public MenuColaborador(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutosColaborador = new VerProdutosColaborador(mercado);
        this.verFuncionariosColaborador = new VerFuncionariosColaborador(mercado);

        setTitle("Login Colaborador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 191, 117));

        JLabel label = new JLabel("Login Colaborador", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setForeground(new Color(207, 250, 151));
        add(label, BorderLayout.NORTH);
        label.setPreferredSize(new Dimension(150, 70));

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(218, 255, 172));
        loginPanel.setPreferredSize(new Dimension(70, 20));

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2));
        fieldsPanel.setBackground(new Color(218, 255, 172));

        JLabel emailLabel = new JLabel("Email:", JLabel.CENTER);
        emailLabel.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        emailLabel.setBorder(BorderFactory.createLineBorder(new Color(180, 189, 169)));
        emailLabel.setPreferredSize(new Dimension(70, 20));
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:", JLabel.CENTER);
        passwordLabel.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        passwordLabel.setBorder(BorderFactory.createLineBorder(new Color(180, 189, 169)));
        passwordLabel.setPreferredSize(new Dimension(70, 20));
        passwordField = new JPasswordField();

        fieldsPanel.add(emailLabel);
        fieldsPanel.add(emailField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        loginButton.setBackground(new Color(159, 191, 117));
        loginButton.setPreferredSize(new Dimension(70, 40));
        loginButton.setForeground(new Color(218, 255, 172));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (login()) {
                    mostrarMenu();
                } else {
                    JOptionPane.showMessageDialog(MenuColaborador.this, "Credenciais inválidas", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginPanel.add(fieldsPanel, BorderLayout.CENTER);
        loginPanel.add(loginButton, BorderLayout.SOUTH);

        add(loginPanel, BorderLayout.CENTER);
    }

    public void mostrarMenu() {
        JFrame menuFrame = new JFrame("Menu do Colaborador");
        menuFrame.setSize(500, 400);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.getContentPane().setBackground(new Color(159, 191, 117));
        menuFrame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton verProdutosButton = new JButton("Ver Produtos");
        verProdutosButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verProdutosButton.setBackground(new Color(218, 255, 172));
        verProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProdutosColaborador.setVisible(true);
            }
        });

        JButton cadastrarFuncionarioButton = new JButton("Ver Funcionários");
        cadastrarFuncionarioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        cadastrarFuncionarioButton.setBackground(new Color(218, 255, 172));
        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verFuncionariosColaborador.setVisible(true);
            }
        });

        JButton sairButton = new JButton("Sair");
        sairButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        sairButton.setBackground(new Color(218, 255, 172));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
            }
        });

        buttonPanel.add(verProdutosButton);
        if (mercado.getColaborador().getCargo().equals("Admin"))
            buttonPanel.add(cadastrarFuncionarioButton);

        buttonPanel.add(sairButton);

        menuFrame.add(buttonPanel, BorderLayout.CENTER);
        menuFrame.setVisible(true);
    }

    public boolean login() {
        String email = emailField.getText();
        String senha = new String(passwordField.getPassword());

        for (Colaborador colaborador : mercado.getColaboradores()) {
            if (colaborador.logar(email, senha)) {
                mercado.setColaborador(colaborador);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Mercado mercado = new Mercado(); // Substitua pelo seu construtor real, se houver
                MenuColaborador menuColaborador = new MenuColaborador(mercado);
                menuColaborador.setVisible(true);
            }
        });
    }
}

