package menu;
import model.Mercado;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private Mercado mercado;
    public MainMenu() {
        mercado = Mercado.getInstance();
        setTitle("Seleção de Menu");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(207, 250, 151));

        // Cria o rótulo do cabeçalho com HTML
        JLabel headerLabel = new JLabel("<html><font color='#FF5757'>Uai</font><font color='#FF914D'>Market</font></html>", JLabel.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // O rótulo ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        // Cria os botões
        JButton clienteButton = createButton("Menu Cliente");
        JButton colaboradorButton = createButton("Menu Colaborador");

        // Adiciona os listeners aos botões
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre o menu.MenuCliente
                MenuCliente menuCliente = new MenuCliente();
                menuCliente.setVisible(true);
            }
        });

        colaboradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre o menu.MenuColaborador
                MenuColaborador menuColaborador = new MenuColaborador();
                menuColaborador.setVisible(true);
            }
        });

        // Configura o layout para os botões
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Cada botão ocupa uma coluna
        gbc.anchor = GridBagConstraints.CENTER;
        add(clienteButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(colaboradorButton, gbc);

        // Adiciona o footer
        JLabel footerLabel = new JLabel("Feito por Helena Carvalho, Natália Molini e Vinícius Veiga", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(38, 46, 28));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(footerLabel, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setForeground(new Color(38, 46, 28));
        button.setBackground(new Color(159, 191, 117));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }
}


