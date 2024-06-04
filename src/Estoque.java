import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Estoque  implements Serializable {
    private int qntd;
    private Date dataUltimaAlteracao;
    public Estoque(int qnts, Date dataUltimaAlteracao){
        this.qntd = qntd;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Estoque() {
        this.qntd = 0;
        this.dataUltimaAlteracao = new Date();
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public void removeQntd(int  qntd){
        this.qntd -= qntd;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
