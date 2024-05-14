import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mercado {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Tipo> tipos = new ArrayList<>();

    Mercado(){
        tipos.add()
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }
    public ArrayList<Produto> buscarProd(String busca){
        ArrayList<Produto> produtosBusca = produtos;
        return produtosBusca;
    }
    public ArrayList<Produto> BuscarProdPorTipo(String busca, Tipo tipo){
        ArrayList<Produto> produtosBusca = produtos;
        return produtosBusca;
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public void addTipo(String nome){
        tipos.add(new Tipo(nome));
    }

}
