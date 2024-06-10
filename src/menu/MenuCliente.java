package menu;

import exception.IncorrectCredentialsException;
import model.Cliente;
import model.EnderecoEntrega;
import model.Mercado;
import view.VerCarrinho;
import view.VerProdutosCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente extends JFrame {
    private Mercado mercado;
    private Cliente cliente;
    private VerProdutosCliente verProdutosCliente;
    private VerCarrinho verCarrinho;

    public MenuCliente(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutosCliente = new VerProdutosCliente(mercado);
        this.verCarrinho = new VerCarrinho(mercado);

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
            try {
                entrarConta();
            } catch (IncorrectCredentialsException e) {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        }
    }

    private void criarConta() {
        cliente = new Cliente();

        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        if (nome == null) {return;}
        cliente.setNome(nome);

        while (true) {
            String email = JOptionPane.showInputDialog("Digite seu email:");
            if (mercado.verificarEmail(email)) {
                if (email == null) {return;}
                cliente.setEmail(email);
                break;
            } else {
                JOptionPane.showMessageDialog(this, "Email já utilizado!");
            }
        }

        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if (senha == null) {return;}
        cliente.setSenha(senha);

        String cpf = JOptionPane.showInputDialog("Digite seu CPF:");
        if (cpf == null) {return;}
        cliente.setCpf(cpf);

        EnderecoEntrega endereco = new EnderecoEntrega();
        String rua = JOptionPane.showInputDialog("Digite o nome da rua:");
        if (rua == null) {return;}
        endereco.setRua(rua);

        String numero = JOptionPane.showInputDialog("Digite o número:");
        if (numero == null) {return;}
        endereco.setNumero(Integer.parseInt(numero));

        String bairro = JOptionPane.showInputDialog("Digite o bairro:");
        if (bairro == null) {return;}
        endereco.setBairro(bairro);

        String complemento = JOptionPane.showInputDialog("Digite o complemento:");
        if (complemento == null) {return;}
        endereco.setComplemento(complemento);

        String cidade = JOptionPane.showInputDialog("Digite a cidade:");
        if (cidade == null) {return;}
        endereco.setCidade(cidade);

        String estado = JOptionPane.showInputDialog("Digite o estado:");
        if (estado == null) {return;}
        endereco.setEstado(estado);

        String cep = JOptionPane.showInputDialog("Digite o CEP:");
        if (cep == null) {return;}
        endereco.setCep(cep);

        cliente.setEnderecoEntrega(endereco);

        mercado.cadastrarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
    }

    private void entrarConta() throws IncorrectCredentialsException {
        String email = JOptionPane.showInputDialog("Digite seu email:");
        String senha = JOptionPane.showInputDialog("Digite sua senha:");

        Cliente clienteExistente = mercado.buscarCliente(email, senha);
        if (clienteExistente == null) {
            throw new IncorrectCredentialsException("Senha ou email inválidos!");
        }
        clienteExistente.logar(email, senha);
        cliente = clienteExistente;
        mercado.vincularCliente(cliente);
        JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");


    }

    private void exibirDadosCliente() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
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

        JTextArea textArea = new JTextArea(dados.toString());
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea));

        JButton editarButton = new JButton("Editar Dados");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarDadosCliente();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(panel);
                window.dispose();
            }
        });

        panel.add(editarButton);
        panel.add(voltarButton);

        JOptionPane.showMessageDialog(this, panel, "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarDadosCliente() {
        String[] opcoes = {
                "Nome", "Email", "Senha", "CPF",
                "Endereço - Rua", "Endereço - Número", "Endereço - Bairro",
                "Endereço - Complemento", "Endereço - Cidade",
                "Endereço - Estado", "Endereço - CEP", "Finalizar"
        };

        boolean continuar = true;
        while (continuar) {
            String escolha = (String) JOptionPane.showInputDialog(
                    this,
                    "Qual informação deseja editar?",
                    "Editar Dados do Cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == null || escolha.equals("Finalizar")) {
                continuar = false;
                break;
            }

            switch (escolha) {
                case "Nome":
                    String nome = JOptionPane.showInputDialog(this, "Digite seu nome:", cliente.getNome());
                    cliente.setNome(nome);
                    break;

                case "Email":
                    String email = cliente.getEmail();
                    while (true) {
                        email = JOptionPane.showInputDialog(this, "Digite seu email:", cliente.getEmail());
                        if (mercado.verificarEmail(email)) {
                            cliente.setEmail(email);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(this, "Email já utilizado!");
                        }
                    }
                    break;

                case "Senha":
                    String senha = JOptionPane.showInputDialog(this, "Digite sua senha:", cliente.getSenha());
                    cliente.setSenha(senha);
                    break;

                case "CPF":
                    String cpf = JOptionPane.showInputDialog(this, "Digite seu CPF:", cliente.getCpf());
                    cliente.setCpf(cpf);
                    break;

                case "Endereço - Rua":
                    EnderecoEntrega endereco = cliente.getEnderecoEntrega();
                    String rua = JOptionPane.showInputDialog(this, "Digite o nome da rua:", endereco.getRua());
                    endereco.setRua(rua);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - Número":
                    endereco = cliente.getEnderecoEntrega();
                    String numero = JOptionPane.showInputDialog(this, "Digite o número:", String.valueOf(endereco.getNumero()));
                    endereco.setNumero(Integer.parseInt(numero));
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - Bairro":
                    endereco = cliente.getEnderecoEntrega();
                    String bairro = JOptionPane.showInputDialog(this, "Digite o bairro:", endereco.getBairro());
                    endereco.setBairro(bairro);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - Complemento":
                    endereco = cliente.getEnderecoEntrega();
                    String complemento = JOptionPane.showInputDialog(this, "Digite o complemento:", endereco.getComplemento());
                    endereco.setComplemento(complemento);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - Cidade":
                    endereco = cliente.getEnderecoEntrega();
                    String cidade = JOptionPane.showInputDialog(this, "Digite a cidade:", endereco.getCidade());
                    endereco.setCidade(cidade);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - Estado":
                    endereco = cliente.getEnderecoEntrega();
                    String estado = JOptionPane.showInputDialog(this, "Digite o estado:", endereco.getEstado());
                    endereco.setEstado(estado);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                case "Endereço - CEP":
                    endereco = cliente.getEnderecoEntrega();
                    String cep = JOptionPane.showInputDialog(this, "Digite o CEP:", endereco.getCep());
                    endereco.setCep(cep);
                    cliente.setEnderecoEntrega(endereco);
                    break;

                default:
                    continuar = false;
                    break;
            }
        }

        mercado.salvarMercado();
        JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
    }

    public static void main(String[] args) {
        Mercado mercado = new Mercado(); // Inicialize seu objeto model.Mercado
        MenuCliente menuCliente = new MenuCliente(mercado);
        menuCliente.setVisible(true);
    }
}


