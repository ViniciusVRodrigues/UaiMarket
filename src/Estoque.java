import java.time.LocalDateTime;
public class Estoque {
    private int qntd;
    private LocalDateTime dataUltimaAlteracao;
    public Estoque(int qnts, LocalDateTime dataUltimaAlteracao){
        this.qntd = qntd;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
