package model;
import java.io.Serializable;
import java.util.Date;

public class Estoque  implements Serializable {
    private int qntd;
    private Date dataUltimaAlteracao;

    // Construtor que inicializa a quantidade e a data da última alteração
    public Estoque(int qntd, Date dataUltimaAlteracao) {
        this.qntd = qntd;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    // Construtor padrão que inicializa a quantidade a 0 e a data da última alteração para a data atual
    public Estoque() {
        this.qntd = 0;
        this.dataUltimaAlteracao = new Date();
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
        atualizarDataUltimaAlteracao();
    }

    public void removeQntd(int qntd) {
        if (this.qntd >= qntd) {
            this.qntd -= qntd;
            atualizarDataUltimaAlteracao();
        } else {
            // Lançar uma exceção ou lidar com a situação onde a quantidade a remover é maior que a disponível
            throw new IllegalArgumentException("Quantidade a remover é maior que a quantidade disponível no estoque.");
        }
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    // Método privado para atualizar a data da última alteração para a data atual
    private void atualizarDataUltimaAlteracao() {
        this.dataUltimaAlteracao = new Date();
    }
}

