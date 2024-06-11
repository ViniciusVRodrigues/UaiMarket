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
import java.util.function.Consumer;

public class MenuCliente extends JFrame {
    private Mercado mercado;
    private Cliente cliente;
    private VerProdutosCliente verProdutosCliente;
    private VerCarrinho verCarrinho;

    public MenuCliente(Mercado mercado) {
        this.mercado = mercado;
        this.verProdutosCliente = new VerProdutosCliente(mercado);


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
                verCarrinho = new VerCarrinho(mercado);
                if (mercado.getCliente() != null)
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
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Criar/Entrar Conta");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(159, 191, 117));

        JButton criarButton = new JButton("Criar Conta");
        criarButton.setBackground(new Color(245, 245, 245));
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarConta();
                frame.dispose(); // Fecha a janela após criar a conta
            }
        });

        JButton entrarButton = new JButton("Entrar");
        entrarButton.setBackground(new Color(245, 245, 245));
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    entrarConta();
                    frame.dispose(); // Fecha a janela após entrar na conta
                } catch (IncorrectCredentialsException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        panel.add(criarButton);
        panel.add(entrarButton);

        frame.add(panel);
        frame.setVisible(true);
    }


    private void criarConta() {
        // Criação da janela
        JFrame criarContaFrame = new JFrame("Criar Conta");
        criarContaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        criarContaFrame.setLocationRelativeTo(null);

        // Campos para os dados do cliente
        JTextField nomeField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField senhaField = new JPasswordField(20);
        JTextField cpfField = new JTextField(20);
        JTextField ruaField = new JTextField(20);
        JTextField numeroField = new JTextField(20);
        JTextField bairroField = new JTextField(20);
        JTextField complementoField = new JTextField(20);
        JTextField cidadeField = new JTextField(20);
        JTextField estadoField = new JTextField(20);
        JTextField cepField = new JTextField(20);

        JPanel mainPanel = new JPanel(new GridLayout(12, 1));
        JPanel mainPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(159, 191, 117));

        mainPanel.add(new JLabel("Nome:"));
        mainPanel.add(nomeField);
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailField);
        mainPanel.add(new JLabel("Senha:"));
        mainPanel.add(senhaField);
        mainPanel.add(new JLabel("CPF:"));
        mainPanel.add(cpfField);
        mainPanel.add(new JLabel("Rua:"));
        mainPanel.add(ruaField);
        mainPanel.add(new JLabel("Número:"));
        mainPanel.add(numeroField);
        mainPanel.add(new JLabel("Bairro:"));
        mainPanel.add(bairroField);
        mainPanel.add(new JLabel("Complemento:"));
        mainPanel.add(complementoField);
        mainPanel.add(new JLabel("Cidade:"));
        mainPanel.add(cidadeField);
        mainPanel.add(new JLabel("Estado:"));
        mainPanel.add(estadoField);
        mainPanel.add(new JLabel("CEP:"));
        mainPanel.add(cepField);

        // Botão para cadastrar o cliente
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBackground(new Color(218, 255, 172));
        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            if(!mercado.verificarEmail(email)){
                JOptionPane.showMessageDialog(criarContaFrame, "Email já utilizado!");
                return;
            }
            String senha = new String(senhaField.getPassword());
            String cpf = cpfField.getText();
            String rua = ruaField.getText();
            String numero = numeroField.getText();
            String bairro = bairroField.getText();
            String complemento = complementoField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();
            String cep = cepField.getText();

            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setCpf(cpf);

            EnderecoEntrega endereco = new EnderecoEntrega();
            endereco.setRua(rua);
            try {
                endereco.setNumero(Integer.parseInt(numero));
            } catch (NumberFormatException ex) {
                endereco.setNumero(0);
            }
            endereco.setBairro(bairro);
            endereco.setComplemento(complemento);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);

            cliente.setEnderecoEntrega(endereco);
            this.cliente = cliente;
            mercado.cadastrarCliente(cliente);

            JOptionPane.showMessageDialog(criarContaFrame, "Cliente cadastrado com sucesso!");
            criarContaFrame.dispose();
        });

        // Adicionar o botão ao painel principal
        mainPanel.add(new JLabel()); // Placeholder para alinhamento
        mainPanel.add(cadastrarButton);

        // Adicionar o painel principal ao frame
        criarContaFrame.add(mainPanel);
        criarContaFrame.setVisible(true);
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
        // Diálogo para exibir os dados do cliente
        JDialog dadosClienteDialog = new JDialog(this, "Dados do Cliente", true);
        dadosClienteDialog.setSize(500, 400);
        dadosClienteDialog.setLocationRelativeTo(this);
        dadosClienteDialog.setLayout(new BorderLayout());

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(159, 191, 117));

        // Área de texto para exibir os dados do cliente
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));

        // Montando os dados do cliente
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

        // Setando o texto na área de texto
        textArea.setText(dados.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(159, 191, 117));

        // Botão de editar
        JButton editarButton = new JButton("Editar Dados");
        configurarBotao(editarButton);
        editarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        editarButton.addActionListener(e -> editarDadosCliente());

        // Botão de voltar
        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);
        voltarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        voltarButton.addActionListener(e -> dadosClienteDialog.dispose());

        // Adicionando botões ao painel de botões
        buttonPanel.add(editarButton);
        buttonPanel.add(voltarButton);

        // Adicionando componentes ao painel principal
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando o painel principal ao diálogo
        dadosClienteDialog.add(panel);

        // Tornando o diálogo visível
        dadosClienteDialog.setVisible(true);
    }

    private void configurarBotao(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(99, 130, 62));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(207, 250, 151), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }


    private void editarDadosCliente() {
        // Diálogo para edição dos dados do cliente
        JDialog editarDialog = new JDialog(this, "Editar Dados do Cliente", true);
        editarDialog.setSize(500, 400);
        editarDialog.setLocationRelativeTo(this);
        editarDialog.setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(159, 191, 117));

        // Área de texto para exibir as opções de edição
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setText("Escolha uma das opções para editar:\n"
                + "1. Nome\n"
                + "2. Email\n"
                + "3. Senha\n"
                + "4. CPF\n"
                + "5. Endereço - Rua\n"
                + "6. Endereço - Número\n"
                + "7. Endereço - Bairro\n"
                + "8. Endereço - Complemento\n"
                + "9. Endereço - Cidade\n"
                + "10. Endereço - Estado\n"
                + "11. Endereço - CEP\n"
                + "12. Finalizar");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Painel para campo de entrada e botões
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(159, 191, 117));

        // Campo de entrada para a escolha da edição
        JTextField inputField = new JTextField(10);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Botão de confirmar
        JButton confirmarButton = new JButton("Confirmar");
        configurarBotao(confirmarButton);
        confirmarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Botão de voltar
        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);
        voltarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Adicionando componentes ao painel de entrada
        inputPanel.add(new JLabel("Escolha:"));
        inputPanel.add(inputField);
        inputPanel.add(confirmarButton);
        inputPanel.add(voltarButton);

        // Adicionando componentes ao painel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Adicionando painel principal ao diálogo
        editarDialog.add(mainPanel);

        // Configurando ação do botão de voltar
        voltarButton.addActionListener(e -> editarDialog.dispose());

        // Configurando ação do botão de confirmar
        confirmarButton.addActionListener(e -> {
            String escolha = inputField.getText();
            if (escolha == null || escolha.trim().isEmpty()) {
                return;
            }

            switch (escolha) {
                case "1":
                    exibirDialogoEdicao("Nome", cliente.getNome(), novoValor -> cliente.setNome(novoValor));
                    break;

                case "2":
                    editarEmail();
                    break;

                case "3":
                    exibirDialogoEdicao("Senha", cliente.getSenha(), novoValor -> cliente.setSenha(novoValor));
                    break;

                case "4":
                    exibirDialogoEdicao("CPF", cliente.getCpf(), novoValor -> cliente.setCpf(novoValor));
                    break;

                case "5":
                    exibirDialogoEdicao("Rua", cliente.getEnderecoEntrega().getRua(), novoValor -> cliente.getEnderecoEntrega().setRua(novoValor));
                    break;

                case "6":
                    exibirDialogoEdicao("Número", String.valueOf(cliente.getEnderecoEntrega().getNumero()), novoValor -> cliente.getEnderecoEntrega().setNumero(Integer.parseInt(novoValor)));
                    break;

                case "7":
                    exibirDialogoEdicao("Bairro", cliente.getEnderecoEntrega().getBairro(), novoValor -> cliente.getEnderecoEntrega().setBairro(novoValor));
                    break;

                case "8":
                    exibirDialogoEdicao("Complemento", cliente.getEnderecoEntrega().getComplemento(), novoValor -> cliente.getEnderecoEntrega().setComplemento(novoValor));
                    break;

                case "9":
                    exibirDialogoEdicao("Cidade", cliente.getEnderecoEntrega().getCidade(), novoValor -> cliente.getEnderecoEntrega().setCidade(novoValor));
                    break;

                case "10":
                    exibirDialogoEdicao("Estado", cliente.getEnderecoEntrega().getEstado(), novoValor -> cliente.getEnderecoEntrega().setEstado(novoValor));
                    break;

                case "11":
                    exibirDialogoEdicao("CEP", cliente.getEnderecoEntrega().getCep(), novoValor -> cliente.getEnderecoEntrega().setCep(novoValor));
                    break;

                case "12":
                    mercado.salvarMercado();
                    JOptionPane.showMessageDialog(editarDialog, "Dados atualizados com sucesso!");
                    editarDialog.dispose();
                    return;

                default:
                    JOptionPane.showMessageDialog(editarDialog, "Opção inválida!");
                    return;
            }

            mercado.salvarMercado();
            JOptionPane.showMessageDialog(editarDialog, "Dados atualizados com sucesso!");
            inputField.setText("");
        });

        // Tornando o diálogo visível
        editarDialog.setVisible(true);
    }

    private void exibirDialogoEdicao(String campo, String valorAtual, Consumer<String> atualizarValor) {
        JDialog dialog = new JDialog(this, "Editar " + campo, true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(159, 191, 117));

        JTextField textField = new JTextField(valorAtual, 20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton salvarButton = new JButton("Salvar");
        configurarBotao(salvarButton);
        salvarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salvarButton.addActionListener(e -> {
            String novoValor = textField.getText();
            if (novoValor != null && !novoValor.trim().isEmpty()) {
                atualizarValor.accept(novoValor);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Valor inválido!");
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelarButton.addActionListener(e -> dialog.dispose());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(159, 191, 117));
        inputPanel.add(new JLabel("Novo " + campo + ":"));
        inputPanel.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void editarEmail() {
        JDialog dialog = new JDialog(this, "Editar Email", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(159, 191, 117));

        JTextField textField = new JTextField(cliente.getEmail(), 20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton salvarButton = new JButton("Salvar");
        configurarBotao(salvarButton);
        salvarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salvarButton.addActionListener(e -> {
            String novoEmail = textField.getText();
            if (novoEmail != null && !novoEmail.trim().isEmpty()) {
                if (mercado.verificarEmail(novoEmail)) {
                    cliente.setEmail(novoEmail);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Email já utilizado!");
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Email inválido!");
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        configurarBotao(cancelarButton);
        cancelarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelarButton.addActionListener(e -> dialog.dispose());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(159, 191, 117));
        inputPanel.add(new JLabel("Novo Email:"));
        inputPanel.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }



    public static void main(String[] args) {
        Mercado mercado = new Mercado(); // Inicialize seu objeto model.Mercado
        MenuCliente menuCliente = new MenuCliente(mercado);
        menuCliente.setVisible(true);
    }
}


