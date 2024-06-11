package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Mercado;
import model.Produto;
import model.Tipo;

public class VerProdutosCliente extends JFrame {
    private Mercado mercado;
    private Produto produtoSelecionado;

    private JPanel mainPanel;

    private JTextField searchField;
    private JComboBox<String> tipoComboBox;
    private JButton listAllButton;
    private JButton listByTypeButton;
    private JButton listTypesButton;
    private JButton searchByNameButton;
    private JButton searchByTypeButton;
    private JButton viewCartButton;
    private JButton addToCartButton; // Novo botão
    private JButton backButton;

    public VerProdutosCliente(Mercado mercado) {
        this.mercado = mercado;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Ver Produtos Cliente");
        setSize(800, 290);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(218, 255, 172));
        getContentPane().add(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(218, 255, 172));


        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(218, 255, 172));

        listAllButton = createButton("Listar Todos");
        listByTypeButton = createButton("Listar por Tipo");
        listTypesButton = createButton("Listar Tipos");
        searchByNameButton = createButton("Pesquisar por Nome");
        searchByTypeButton = createButton("Pesquisar por Tipo");
        viewCartButton = createButton("Ver Carrinho");
        addToCartButton = createButton("Adicionar ao Carrinho"); // Novo botão
        backButton = createButton("Voltar");

        bottomPanel.add(listAllButton);
        bottomPanel.add(listByTypeButton);
        bottomPanel.add(listTypesButton);
        bottomPanel.add(searchByNameButton);
        bottomPanel.add(searchByTypeButton);
        bottomPanel.add(viewCartButton);
        bottomPanel.add(addToCartButton); // Adiciona o novo botão
        bottomPanel.add(backButton);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(218, 255, 172));
        buttonPanel.add(bottomPanel, BorderLayout.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona Listeners
        listAllButton.addActionListener(e -> mostrarProdutosDisponiveis());
        listByTypeButton.addActionListener(e -> mostrarProdutosPorTipo());
        listTypesButton.addActionListener(e -> listarTipos());
        searchByNameButton.addActionListener(e -> pesquisarProdutoPorNome());
        searchByTypeButton.addActionListener(e -> pesquisarProdutoPorTipo());
        viewCartButton.addActionListener(e -> viewCart());
        addToCartButton.addActionListener(e -> adicionarAoCarrinho()); // Adiciona listener para o novo botão
        backButton.addActionListener(e -> dispose());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(159, 191, 117));
        button.setBorder(BorderFactory.createLineBorder(new Color(207, 250, 151)));
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        return button;
    }

    private void mostrarProdutosDisponiveis() {
        JDialog produtosDialog = new JDialog(this, "Produtos Disponíveis", true);
        produtosDialog.setSize(800, 400);
        produtosDialog.setLocationRelativeTo(this);
        produtosDialog.setLayout(new BorderLayout());

        // Set background color
        produtosDialog.getContentPane().setBackground(new Color(159, 191, 117));

        // Table Model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Nome", "Preço", "Quantidade em Estoque"}, 0);
        JTable tabela = new JTable(tableModel);
        configurarTabela(tabela);

        // Populate Table Model
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        for (Produto produto : produtosDisponiveis) {
            if (produto.getQuantidadeEstoque() > 0) {
                tableModel.addRow(new Object[]{
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidadeEstoque()
                });
            }
        }

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Buttons

        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        // Listeners

        voltarButton.addActionListener(e -> produtosDialog.setVisible(false));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(voltarButton);

        // Add components to dialog
        produtosDialog.add(scrollPane, BorderLayout.CENTER);
        produtosDialog.add(buttonPanel, BorderLayout.SOUTH);

        produtosDialog.setVisible(true);
    }

    private void mostrarMenuProduto(Produto produtoSelecionado) {
    }

    private void configurarTabela(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(99, 130, 62));
        table.getTableHeader().setForeground(Color.WHITE);

        // Center align columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setShowGrid(true);
        table.setGridColor(new Color(207, 250, 151));
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

    private void mostrarProdutosPorTipo() {
        JDialog produtosDialog = new JDialog(this, "Produtos por Tipo", true);
        produtosDialog.setSize(800, 400);
        produtosDialog.setLocationRelativeTo(this);
        produtosDialog.setLayout(new BorderLayout());

        // Set background color
        produtosDialog.getContentPane().setBackground(new Color(159, 191, 117));

        // Table Model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Tipo", "Nome", "Preço", "Quantidade em Estoque"}, 0);
        JTable tabela = new JTable(tableModel);
        configurarTabela(tabela);

        // Populate Table Model
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        Map<Tipo, List<Produto>> produtosPorTipo = produtosDisponiveis.stream()
                .collect(Collectors.groupingBy(Produto::getTipo));

        produtosPorTipo.forEach((tipo, produtos) -> {
            for (Produto produto : produtos) {
                if (produto.getQuantidadeEstoque() > 0) {
                    tableModel.addRow(new Object[]{
                            tipo.getNome(),
                            produto.getNome(),
                            produto.getPreco(),
                            produto.getQuantidadeEstoque()
                    });
                }
            }
        });

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Buttons
        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        // Listeners
        voltarButton.addActionListener(e -> produtosDialog.setVisible(false));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(voltarButton);

        // Add components to dialog
        produtosDialog.add(scrollPane, BorderLayout.CENTER);
        produtosDialog.add(buttonPanel, BorderLayout.SOUTH);

        produtosDialog.setVisible(true);
    }


    private void listarTipos() {
        JDialog tiposDialog = new JDialog(this, "Tipos de Produtos", true);
        tiposDialog.setSize(400, 300);
        tiposDialog.setLocationRelativeTo(this);
        tiposDialog.setLayout(new BorderLayout());

        // Set background color
        tiposDialog.getContentPane().setBackground(new Color(159, 191, 117));

        // Table Model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Tipos de Produtos"}, 0);
        JTable tabela = new JTable(tableModel);
        configurarTabela(tabela);

        // Populate Table Model
        List<Tipo> tipos = mercado.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            Tipo tipo = tipos.get(i);
            tableModel.addRow(new Object[]{i, tipo.getNome()});
        }

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Buttons
        JButton voltarButton = new JButton("Voltar");
        configurarBotao(voltarButton);

        // Listeners
        voltarButton.addActionListener(e -> tiposDialog.setVisible(false));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(159, 191, 117));
        buttonPanel.add(voltarButton);

        // Add components to dialog
        tiposDialog.add(scrollPane, BorderLayout.CENTER);
        tiposDialog.add(buttonPanel, BorderLayout.SOUTH);

        tiposDialog.setVisible(true);
    }




    private void pesquisarProdutoPorNome() {
        JDialog pesquisaDialog = new JDialog(this, "Pesquisar Produto por Nome", true);
        pesquisaDialog.setSize(560, 300);
        pesquisaDialog.setLocationRelativeTo(this);
        pesquisaDialog.setLayout(new BorderLayout());

        // Set background color
        pesquisaDialog.getContentPane().setBackground(new Color(159, 191, 117));

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(159, 191, 117));
        JLabel nameLabel = new JLabel("Digite o nome que deseja pesquisar:");
        JTextField nomeField = new JTextField(20);
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pesquisarButton.setForeground(Color.WHITE);
        pesquisarButton.setBackground(new Color(99, 130, 62));
        pesquisarButton.setFocusPainted(false);
        pesquisarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(207, 250, 151), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        inputPanel.add(nameLabel);
        inputPanel.add(nomeField);
        inputPanel.add(pesquisarButton);

        // Result Panel
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Pesquisar Button Listener
        pesquisarButton.addActionListener(e -> {
            String nomePesquisa = nomeField.getText();
            List<Produto> produtosPesquisa = mercado.buscarProd(nomePesquisa);
            StringBuilder sb = new StringBuilder("Resultados da Pesquisa por Nome:\n");
            for (Produto produto : produtosPesquisa) {
                if (produto.getQuantidadeEstoque() > 0) {
                    sb.append(produto).append("\n");
                }
            }
            resultArea.setText(sb.toString());
        });

        // Add components to dialog
        pesquisaDialog.add(inputPanel, BorderLayout.NORTH);
        pesquisaDialog.add(scrollPane, BorderLayout.CENTER);

        pesquisaDialog.setVisible(true);
    }




    private void pesquisarProdutoPorTipo() {
        // Diálogo para a pesquisa por tipo
        JDialog pesquisaPorTipoDialog = new JDialog(this, "Pesquisar Produto por Tipo", true);
        pesquisaPorTipoDialog.setSize(500, 300);
        pesquisaPorTipoDialog.setLocationRelativeTo(this);
        pesquisaPorTipoDialog.setLayout(new BorderLayout());

        // Painel de entrada para o ID do tipo
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(159, 191, 117));

        // Rótulo e campo de texto para o ID do tipo
        JLabel idLabel = new JLabel("Digite o ID do tipo que deseja pesquisar:");
        JTextField idField = new JTextField(10);

        // Botão de pesquisa
        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pesquisarButton.setForeground(Color.WHITE);
        pesquisarButton.setBackground(new Color(99, 130, 62));
        pesquisarButton.setFocusPainted(false);
        pesquisarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(207, 250, 151), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Adicionando componentes ao painel de entrada
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(pesquisarButton);

        // Área de texto para exibir os resultados da pesquisa
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Listener para o botão de pesquisa
        pesquisarButton.addActionListener(e -> {
            try {
                // Obtendo o ID do tipo
                int idTipo = Integer.parseInt(idField.getText());
                // Obtendo o tipo correspondente ao ID
                Tipo tipoBusca = mercado.getTipos().get(idTipo);
                // Obtendo os produtos correspondentes ao tipo

                List<Produto> produtosPesquisa = mercado.buscarProdPorTipo(tipoBusca);
                System.out.println(produtosPesquisa.size());
                // Construindo a mensagem com os resultados da pesquisa
                StringBuilder sb = new StringBuilder("Resultados da Pesquisa por Tipo:\n");
                for (Produto produto : produtosPesquisa) {
                    sb.append(produto).append("\n");
                }
                // Exibindo os resultados na área de texto
                resultArea.setText(sb.toString());
            } catch (NumberFormatException ex) {
                // Tratamento de exceção para entrada inválida
                JOptionPane.showMessageDialog(pesquisaPorTipoDialog, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IndexOutOfBoundsException ex) {
                // Tratamento de exceção para ID de tipo inválido
                JOptionPane.showMessageDialog(pesquisaPorTipoDialog, "O ID inserido não corresponde a nenhum tipo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Adicionando componentes ao diálogo
        pesquisaPorTipoDialog.add(inputPanel, BorderLayout.NORTH);
        pesquisaPorTipoDialog.add(scrollPane, BorderLayout.CENTER);

        // Tornando o diálogo visível
        pesquisaPorTipoDialog.setVisible(true);
    }


    private void viewCart() {
        VerCarrinho verCarrinho = new VerCarrinho(mercado);
        if (mercado.getCliente() != null)
            verCarrinho.setVisible(true);
    }

    private void adicionarAoCarrinho() {
        JDialog adicionarDialog = new JDialog(this, "Adicionar ao Carrinho", true);
        adicionarDialog.setSize(600, 400);  // Tamanho ajustado do diálogo
        adicionarDialog.setLocationRelativeTo(this);
        adicionarDialog.setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.setBackground(new Color(159, 191, 117));

        // Campo para o nome do produto
        JPanel nomeProdutoPanel = new JPanel(new FlowLayout());
        nomeProdutoPanel.setBackground(new Color(159, 191, 117));
        JLabel nomeProdutoLabel = new JLabel("Nome do Produto:");
        JTextField nomeProdutoField = new JTextField(15);  // Tamanho ajustado do campo de texto
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setPreferredSize(new Dimension(100, 25)); // Tamanho ajustado do botão "Buscar"
        configurarBotao(buscarButton);
        nomeProdutoPanel.add(nomeProdutoLabel);
        nomeProdutoPanel.add(nomeProdutoField);
        nomeProdutoPanel.add(buscarButton);

        // Campo para a quantidade
        JPanel quantidadePanel = new JPanel(new FlowLayout());
        quantidadePanel.setBackground(new Color(159, 191, 117));
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadePanel.setPreferredSize(new Dimension(160, 20));
        JTextField quantidadeField = new JTextField(5);  // Tamanho ajustado do campo de texto
        quantidadePanel.add(quantidadeLabel);
        quantidadePanel.add(quantidadeField);

        // Área para exibir produtos encontrados
        JTextArea produtosTextArea = new JTextArea(100, 40);  // Mantém o tamanho grande para exibição
        produtosTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        produtosTextArea.setEditable(false);
        produtosTextArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(produtosTextArea);

        // Botão de adicionar ao carrinho
        JButton adicionarButton = new JButton("Adicionar ao Carrinho");
        adicionarButton.setPreferredSize(new Dimension(160, 20)); // Tamanho ajustado do botão "Adicionar ao Carrinho"
        configurarBotao(adicionarButton);

        // Adicionando componentes ao painel principal
        mainPanel.add(nomeProdutoPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(quantidadePanel);
        mainPanel.add(adicionarButton);

        // Adicionando painel principal ao diálogo
        adicionarDialog.add(mainPanel);

        // Ação do botão de buscar
        buscarButton.addActionListener(e -> {
            String nomeProduto = nomeProdutoField.getText();
            if (nomeProduto.isEmpty()) {
                JOptionPane.showMessageDialog(adicionarDialog, "Por favor, insira o nome do produto.");
                return;
            }
            List<Produto> produtosPesquisa = mercado.buscarProd(nomeProduto);
            if (produtosPesquisa.isEmpty()) {
                JOptionPane.showMessageDialog(adicionarDialog, "Produto não encontrado!");
                produtosTextArea.setText("");
                return;
            }
            produtosTextArea.setText("");
            for (Produto produto : produtosPesquisa) {
                produtosTextArea.append("Nome: " + produto.getNome() + ", Estoque: " + produto.getQuantidadeEstoque() + "\n");
            }
        });

        // Ação do botão de adicionar ao carrinho
        adicionarButton.addActionListener(e -> {
            String nomeProduto = nomeProdutoField.getText();
            String quantidadeStr = quantidadeField.getText();
            if (nomeProduto.isEmpty() || quantidadeStr.isEmpty()) {
                JOptionPane.showMessageDialog(adicionarDialog, "Por favor, preencha todos os campos.");
                return;
            }
            List<Produto> produtosPesquisa = mercado.buscarProd(nomeProduto);
            if (produtosPesquisa.isEmpty()) {
                JOptionPane.showMessageDialog(adicionarDialog, "Produto não encontrado!");
                return;
            }
            Produto produto = produtosPesquisa.get(0); // Supondo que o primeiro resultado seja o desejado
            int quantidade;
            try {
                quantidade = Integer.parseInt(quantidadeStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adicionarDialog, "Quantidade inválida!");
                return;
            }
            if (quantidade > 0 && quantidade <= produto.getQuantidadeEstoque()) {
                mercado.adicionarAoCarrinho(produto, quantidade);
                JOptionPane.showMessageDialog(adicionarDialog, "Produto adicionado ao carrinho!");
            } else {
                JOptionPane.showMessageDialog(adicionarDialog, "Quantidade inválida!");
            }
        });

        // Tornando o diálogo visível
        adicionarDialog.setVisible(true);
    }


    public static void main(String[] args) {
        Mercado mercado = new Mercado();
        VerProdutosCliente frame = new VerProdutosCliente(mercado);
        frame.setVisible(true);
    }
}


