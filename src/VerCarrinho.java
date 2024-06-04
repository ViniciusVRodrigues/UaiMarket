import java.util.List;
import java.util.Scanner;

public class VerCarrinho {
    private Mercado mercado;
    private Cliente cliente;
    private Carrinho carrinho;
    private Scanner scanner;
    private ProdutoCarrinho pCSelecionado;
    VerCarrinho(Mercado m,Scanner s){
        this.mercado = m;
        this.cliente = mercado.getCliente();
        this.carrinho = cliente.getCarrinho();
        this.scanner = s;
    }

    public int mostrarMenu(){
        while (true){
            System.out.println("\n----- Ver Carrinho -----");
            carrinho.printProdutos();
            System.out.println("1. Continuar Comprando");
            System.out.println("2. Efetuar Compra");
            System.out.println("3. Remover Produto");
            System.out.println("4. Alterar Produto");
            System.out.println("0. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Indo para produtos...");
                    return 2;
                case 2:
                    System.out.println("Tem certeza que deseja efetuar compra?");
                    System.out.println("1. Confirmar");
                    System.out.println("0. Voltar");
                    int confirmacao = scanner.nextInt();
                    scanner.nextLine();
                    if(confirmacao==1) {
                        if(mercado.fazerPedido(scanner)){
                            return 0;
                        }
                    }
                    break;
                case 3:
                    //Removendo produto do carrinho
                    if(selecionarProduto()){
                        System.out.println("Tem certeza que deseja remover esse produto?");
                        System.out.println("1. Confirmar");
                        System.out.println("0. Voltar");
                        int resposta = scanner.nextInt();
                        scanner.nextLine();
                        if(resposta==1)
                            carrinho.removeProduto(pCSelecionado);
                    }
                    break;
                case 4:
                    //Alterando produto no carrinho
                    if(selecionarProduto()){
                        System.out.println("Qual a nova quantidade desse produto que seja colocar no carrinho?");
                        System.out.println("(Quantidade em estoque "+pCSelecionado.getQuantidadeEstoque()+ ")");
                        System.out.println("0. Voltar");
                        int novaQuant = scanner.nextInt();
                        scanner.nextLine();
                        if(novaQuant<=0)
                            break;
                        if(novaQuant>pCSelecionado.getQuantidadeEstoque()){
                            System.out.println("Quantidade excede o estoque atual! Voltando...");
                            break;
                        }
                        pCSelecionado.setQuantidade(novaQuant);
                        carrinho.atualizarValorTotalProdutos();
                        System.out.println("Nova quantidade salva!");
                    }
                    break;
                case 0:
                    System.out.println("Voltando...");
                    return 0;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    private boolean selecionarProduto(){
        System.out.println("Digite o ID do produto que deseja selecionar:");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        List<ProdutoCarrinho> pCList = carrinho.getProdutos();
        if(opcao<0||opcao>=pCList.size())
            return false;
        pCSelecionado = pCList.get(opcao);
        System.out.println("\n"+pCSelecionado);
        return true;
    }
}
