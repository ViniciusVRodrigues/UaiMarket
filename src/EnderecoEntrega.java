import java.io.Serializable;

public class EnderecoEntrega  implements Serializable {
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private int cep;

    public EnderecoEntrega(){
        this.rua = "Rua xxxx";
        this.numero = 000;
        this.bairro = "xxxxxx";
        this.complemento = "casa x ou ap xx";
        this.cidade = "xxxxx";
        this.estado = "xxxx";
        this.cep = 00000000;
    }
    public EnderecoEntrega(String rua, int numero, String bairro, String complemento, String cidade, String estado, int cep){
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public int getCep() {
        return cep;
    }
    public int getNumero() {
        return numero;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getRua() {
        return rua;
    }
    public String getEstado() {
        return estado;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setCep(int cep) {
        this.cep = cep;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
}
