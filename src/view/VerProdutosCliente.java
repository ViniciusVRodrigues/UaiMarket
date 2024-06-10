package view;

import javax.swing.*;
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
    private JTextArea displayArea;
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
        setTitle("Ver Produtos model.Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(218, 255, 172));
        getContentPane().add(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(218, 255, 172));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBackground(new Color(255, 255, 255));
        displayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(218, 255, 172));

        listAllButton = createButton("Listar Todos");
        listByTypeButton = createButton("Listar por model.Tipo");
        listTypesButton = createButton("Listar Tipos");
        searchByNameButton = createButton("Pesquisar por Nome");
        searchByTypeButton = createButton("Pesquisar por model.Tipo");
        viewCartButton = createButton("Ver model.Carrinho");
        addToCartButton = createButton("Adicionar ao model.Carrinho"); // Novo botão
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
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        StringBuilder sb = new StringBuilder("Produtos Disponíveis:\n");
        for (Produto produto : produtosDisponiveis) {
            if (produto.getQuantidadeEstoque() > 0) {
                sb.append(produto).append("\n");
            }
        }
        displayArea.setText(sb.toString());
    }

    private void mostrarProdutosPorTipo() {
        List<Produto> produtosDisponiveis = mercado.getProdutos();
        Map<Tipo, List<Produto>> produtosPorTipo = produtosDisponiveis.stream()
                .collect(Collectors.groupingBy(Produto::getTipo));

        StringBuilder sb = new StringBuilder("Produtos por model.Tipo:\n");
        produtosPorTipo.forEach((tipo, produtos) -> {
            sb.append("\n").append(tipo.getNome()).append(":\n");
            produtos.forEach(produto -> sb.append(produto).append("\n"));
        });
        displayArea.setText(sb.toString());
    }

    private void listarTipos() {
        List<Tipo> tipos = mercado.getTipos();
        StringBuilder sb = new StringBuilder("Tipos de Produtos:\n");
        for (Tipo tipo : tipos) {
            sb.append(tipo.getNome()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void pesquisarProdutoPorNome() {
        String nomePesquisa = JOptionPane.showInputDialog(this, "Digite o nome que deseja pesquisar:");
        List<Produto> produtosPesquisa = mercado.buscarProd(nomePesquisa);
        StringBuilder sb = new StringBuilder("Resultados da Pesquisa por Nome:\n");
        for (Produto produto : produtosPesquisa) {
            if (produto.getQuantidadeEstoque() > 0) {
                sb.append(produto).append("\n");
            }
        }
        displayArea.setText(sb.toString());
    }

    private void pesquisarProdutoPorTipo() {
        listarTipos();
        String idTipoStr = JOptionPane.showInputDialog(this, "Digite o id do tipo que deseja pesquisar:");
        int idTipo = Integer.parseInt(idTipoStr);
        Tipo tipoBusca = mercado.getTipos().get(idTipo);
        String nomePesquisa = JOptionPane.showInputDialog(this, "Digite o nome do produto que deseja pesquisar (deixe vazio caso queira todos do tipo):");
        List<Produto> produtosPesquisa = mercado.buscarProdPorTipo(nomePesquisa, tipoBusca);
        StringBuilder sb = new StringBuilder("Resultados da Pesquisa por model.Tipo:\n");
        for (Produto produto : produtosPesquisa) {
            if (produto.getQuantidadeEstoque() > 0) {
                sb.append(produto).append("\n");
            }
        }
        displayArea.setText(sb.toString());
    }

    private void viewCart() {
        VerCarrinho verCarrinho = new VerCarrinho(mercado);
        verCarrinho.setVisible(true);
    }

    private void adicionarAoCarrinho() {
        String nomeProduto = JOptionPane.showInputDialog(this, "Digite o nome do produto que deseja adicionar ao carrinho:");
        List<Produto> produtosPesquisa = mercado.buscarProd(nomeProduto);
        if (produtosPesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "model.Produto não encontrado!");
            return;
        }
        Produto produto = produtosPesquisa.get(0); // Supondo que o primeiro resultado seja o desejado
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite a quantidade que deseja adicionar:"));
        if (quantidade > 0 && quantidade <= produto.getQuantidadeEstoque()) {
            mercado.adicionarAoCarrinho(produto, quantidade);
            JOptionPane.showMessageDialog(this, "model.Produto adicionado ao carrinho!");
        } else {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
        }
    }

    public static void main(String[] args) {
        Mercado mercado = new Mercado();
        VerProdutosCliente frame = new VerProdutosCliente(mercado);
        frame.setVisible(true);
    }
}


