import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MenuCliente extends JFrame {
    private Mercado mercado;
    private Cliente cliente;
    private VerProdutosCliente verProdutosCliente;
    private VerCarrinho verCarrinho;

    public MenuCliente(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutosCliente = new VerProdutosCliente(mercado); // Scanner para simulação
        this.verCarrinho = new VerCarrinho(mercado); // Scanner para simulação

        setTitle("Menu Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 191, 117));

        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        menuPanel.setBackground(new Color(159, 191, 117));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton criarEntrarContaButton = new JButton("Criar/Entrar Conta");
        criarEntrarContaButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        criarEntrarContaButton.setBackground(new Color(218, 255, 172));
        criarEntrarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarOuEntrarConta();
            }
        });

        JButton verProdutosButton = new JButton("Ver Produtos");
        verProdutosButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verProdutosButton.setBackground(new Color(218, 255, 172));
        verProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProdutosCliente.setVisible(true);
            }
        });

        JButton verCarrinhoButton = new JButton("Ver Carrinho");
        verCarrinhoButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verCarrinhoButton.setBackground(new Color(218, 255, 172));
        verCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCarrinho.setVisible(true);
            }
        });

        JButton verDadosButton = new JButton("Ver Dados");
        verDadosButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        verDadosButton.setBackground(new Color(218, 255, 172));
        verDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cliente != null) {
                    exibirDadosCliente();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, crie ou entre em uma conta primeiro.");
                }
            }
        });

        JButton sairButton = new JButton("Sair");
        sairButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        sairButton.setBackground(new Color(218, 255, 172));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        menuPanel.add(criarEntrarContaButton);
        menuPanel.add(verProdutosButton);
        menuPanel.add(verCarrinhoButton);
        menuPanel.add(verDadosButton);
        menuPanel.add(sairButton);

        add(menuPanel, BorderLayout.CENTER);
    }

    private void criarOuEntrarConta() {
        String[] options = {"Criar Conta", "Entrar"};
        int choice = JOptionPane.showOptionDialog(this, "Deseja criar uma nova conta ou entrar em uma conta existente?",
                "Criar/Entrar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) { // Criar Conta
            criarConta();
        } else if (choice == 1) { // Entrar
            entrarConta();
        }
    }

    private void criarConta() {
        cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);

        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        cliente.setNome(nome);

        while (true) {
            String email = JOptionPane.showInputDialog("Digite seu email:");
            cliente.setEmail(email);
            if (mercado.verificarEmail(email)) {
                break;
            } else {
                JOptionPane.showMessageDialog(this, "Email já utilizado!");
            }
        }

        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        cliente.setSenha(senha);

        String cpf = JOptionPane.showInputDialog("Digite seu CPF:");
        cliente.setCpf(cpf);

        EnderecoEntrega endereco = new EnderecoEntrega();
        String rua = JOptionPane.showInputDialog("Digite o nome da rua:");
        endereco.setRua(rua);

        String numero = JOptionPane.showInputDialog("Digite o número:");
        endereco.setNumero(Integer.parseInt(numero));

        String bairro = JOptionPane.showInputDialog("Digite o bairro:");
        endereco.setBairro(bairro);

        String complemento = JOptionPane.showInputDialog("Digite o complemento:");
        endereco.setComplemento(complemento);

        String cidade = JOptionPane.showInputDialog("Digite a cidade:");
        endereco.setCidade(cidade);

        String estado = JOptionPane.showInputDialog("Digite o estado:");
        endereco.setEstado(estado);

        String cep = JOptionPane.showInputDialog("Digite o CEP:");
        endereco.setCep(cep);

        cliente.setEnderecoEntrega(endereco);

        mercado.cadastrarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
    }

    private void entrarConta() {
        String email = JOptionPane.showInputDialog("Digite seu email:");
        String senha = JOptionPane.showInputDialog("Digite sua senha:");

        Cliente clienteExistente = mercado.buscarCliente(email, senha);
        System.out.println(clienteExistente);
        if (clienteExistente != null && clienteExistente.logar(email, senha)) {
            cliente = clienteExistente;
            mercado.vincularCliente(cliente);
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
        }
    }

    private void exibirDadosCliente() {
        StringBuilder dados = new StringBuilder("Dados do Cliente:\n");
        dados.append("Nome: ").append(cliente.getNome()).append("\n");
        dados.append("Email: ").append(cliente.getEmail()).append("\n");
        dados.append("Senha: ").append(cliente.getSenha()).append("\n");
        dados.append("CPF: ").append(cliente.getCpf()).append("\n\n");

        EnderecoEntrega endereco = cliente.getEnderecoEntrega();
        dados.append("Endereço de Entrega:\n");
        dados.append("Rua: ").append(endereco.getRua()).append("\n");
        dados.append("Número: ").append(endereco.getNumero()).append("\n");
        dados.append("Bairro: ").append(endereco.getBairro()).append("\n");
        dados.append("Complemento: ").append(endereco.getComplemento()).append("\n");
        dados.append("Cidade: ").append(endereco.getCidade()).append("\n");
        dados.append("Estado: ").append(endereco.getEstado()).append("\n");
        dados.append("CEP: ").append(endereco.getCep()).append("\n");

        JOptionPane.showMessageDialog(this, dados.toString(), "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Mercado mercado = new Mercado(); // Inicialize seu objeto Mercado
        MenuCliente menuCliente = new MenuCliente(mercado);
        menuCliente.setVisible(true);
    }
}




