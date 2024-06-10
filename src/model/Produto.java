package model;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Produto implements Serializable {

    private long id;
    private String nome;
    private Tipo tipo;
    private String marca;
    private float preco;
    private Estoque estoque = new Estoque();

    public Produto() {
        super();
        this.nome = "xxxx";
        this.marca = "xxxxx";
        this.preco = 00.0f;
    }

    public Produto(long id, Tipo tipo, String nome, String marca, float preco, int quantidade) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.estoque.setQntd(quantidade);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Estoque getEstoque() {
        return estoque;
    }


    public int getQuantidadeEstoque(){
        return estoque.getQntd();
    }


    public void removerQuantidadeEstoque(int quantidade){
        estoque.removeQntd(quantidade);
    }

    @Override
    public String toString() {
        return "Id= " + id + ", Nome= " + nome + ", Marca= " + marca + ", Preço= R$" + preco + ", Tipo= " + (tipo != null ? tipo.getNome() : "N/A") + ", Quantidade= " + estoque.getQntd();
    }

    public String toCSVLine(String sep){
        return id+sep+nome+sep+marca+sep+preco+sep+(tipo != null ? tipo.getNome() : "N/A")+sep+estoque.getQntd();
    }

    public int cadastrarProduto(Scanner scanner, List<Tipo> tipos,long idProduto) {
        System.out.println("---- Adicionar Produto(Digite 'Cancelar' para cancelar) ----");
        this.id=idProduto;
        System.out.println("Digite o nome:");
        String input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.nome = input;

        System.out.println("Digite a marca:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.marca = input;

        System.out.println("Digite o preço:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        this.preco = Float.parseFloat(input);

        System.out.println("Digite a quantidade:");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        int quantidade = Integer.parseInt(input);
        this.estoque.setQntd(quantidade);
        for (int i = 0; i < tipos.size(); i++) {
            Tipo tipo = tipos.get(i);
            System.out.println(i + " - " + tipo.getNome());
        }

        System.out.println("Selecione o tipo (Digite o ID):");
        input = scanner.nextLine();
        if (testeSair(input)) return 0;
        int id = Integer.parseInt(input);
        if (id >= 0 && id < tipos.size()) {
            this.tipo = tipos.get(id);
        } else {
            System.out.println("ID de tipo inválido. Cancelando...");
            return 0;
        }

        System.out.println("\nProduto Cadastrado com sucesso!");
        return 1;
    }

    public void atualizarProduto(Scanner scanner) {
        boolean mostrar = true;
        while (mostrar) {
            System.out.println("---- ATUALIZAR ----");
            System.out.println("1 - Atualizar estoque");
            System.out.println("2 - Atualizar preço");
            System.out.println("0 - Voltar");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    System.out.println("\n---- model.Estoque ----");
                    System.out.println("Estoque atual: " + estoque.getQntd());
                    System.out.println("Digite a nova quantidade:");
                    int qtde = scanner.nextInt();
                    scanner.nextLine();
                    estoque.setQntd(qtde);
                    break;
                case 2:
                    System.out.println("\n---- Preço ----");
                    System.out.println("Preço atual: R$" + this.preco);
                    System.out.println("Digite o novo preço:");
                    float preco = scanner.nextFloat();
                    scanner.nextLine();
                    this.preco = preco;
                    break;
                case 0:
                    System.out.println("Voltando...");
                    mostrar = false;
                    break;
                default:
                    System.out.println("Valor inválido!");
                    break;
            }
        }
    }

    private boolean testeSair(String s) {
        if (s.equalsIgnoreCase("Cancelar")) {
            System.out.println("Cancelando...");
            return true;
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }


}
