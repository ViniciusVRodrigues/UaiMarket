package model;

import java.io.Serializable;

public class Tipo  implements Serializable {
    protected String nome;

    // Construtor padrão que inicializa o nome com um valor padrão
    public Tipo() {
        this.nome = "xxxx";
    }

    // Construtor que inicializa o nome com o valor fornecido
    public Tipo(String nome) {
        this.nome = nome;
    }

    public Tipo(int i, String bebida) {
    }

    // Método getter para obter o nome
    public String getNome() {
        return nome;
    }

    // Método toString para retornar o nome
    @Override
    public String toString() {
        return nome;
    }
    public String toCSVLine(String sep){
        return nome;
    }
}
