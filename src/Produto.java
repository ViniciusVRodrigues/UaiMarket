import java.util.List;
import java.util.Scanner;

public class Produto {
    private String nome;
    private Tipo tipo;
    private String marca;
    private float preco;
    private Estoque estoque = new Estoque();
    public Produto(){
        super();
        this.nome = "xxxx";
        this.marca = "xxxxx";
        this.preco = 00.0f;
    }
    public Produto(Tipo tipo, String nome, String marca, float preco, int quantidade){
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
//        this.estoque.setQuantidade(quantidade);
    }

    public String getNome() {
        return nome;
    }
    public String getMarca() {
        return marca;
    }
    public float getPreco() {
        return preco;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int cadastrarProduto(Scanner scanner, List<Tipo> tipos){
        System.out.println("---- Adicionar Produto(Digite 'Cancelar' para cancelar) ----");
        System.out.println("Digite o nome:");
        String input = scanner.nextLine();
        if(testeSair(input)) return 0;
        this.nome = input;
        System.out.println("Digite a marca:");
        input = scanner.nextLine();
        if(testeSair(input)) return 0;
        this.marca = input;
        System.out.println("Digite o preço:");
        input = scanner.nextLine();
        if(testeSair(input)) return 0;
        this.preco = Float.parseFloat(input);
        System.out.println("Digite a quantidade:");
        input = scanner.nextLine();
        if(testeSair(input)) return 0;
        int quantidade = Integer.parseInt(input);
        this.estoque.setQntd(quantidade);
        scanner.nextLine();
        for (int i = 0; i < tipos.size(); i++) {
            Tipo tipo = tipos.get(i);
            System.out.println(i+" - "+tipo.getNome());
        }
        System.out.println("Selecione o tipo (Digite o ID):");
        input = scanner.nextLine();
        if(testeSair(input)) return 0;
        int id = Integer.parseInt(input);
        tipos.get(id);
        System.out.println("\nProduto Cadastrado com sucesso!");
        return 1;
    }

    private boolean testeSair(String s){
        if(s.equals("Cancelar")) return true;
        return false;
    }
}
