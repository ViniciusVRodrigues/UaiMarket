import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuColaborador extends JFrame {
    private Mercado mercado;
    private JTextField emailField;
    private JPasswordField passwordField;

    private VerProdutosColaborador verProdutosColaborador;
    private VerColaboradores verColaboradores;
    private Colaborador colaboradorLogado;


    public MenuColaborador(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutosColaborador = new VerProdutosColaborador(mercado);
        this.verColaboradores = new VerColaboradores(mercado, colaboradorLogado);

        setTitle("Menu Colaborador");
        setSize(350, 225);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 255, 255)); // cor de fundo branca

        JLabel label = new JLabel("Menu Colaborador", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

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

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);
    }

    public void mostrarMenu() {
        JOptionPane.showMessageDialog(this, "Menu do Colaborador será exibido aqui");
    }

    public boolean login() {
        String email = emailField.getText();
        String senha = new String(passwordField.getPassword());

        for (Colaborador colaborador : mercado.getColaboradores()) {
            if (colaborador.logar(email, senha)) {
                colaboradorLogado = colaborador;
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
