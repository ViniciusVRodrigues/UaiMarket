public class Colaborador extends Pessoa{
    private int codigo;
    private String cargo;
    public Colaborador(){
        super();
        this.codigo = 0000;
        this.cargo = "xxxx";
    }
    public Colaborador(String nome, String email, String senha, int codigo, String cargo){
        super(nome, email, senha);
        this.codigo = codigo;
        this.cargo = cargo;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void atualizarEstoque(){
    }
    public void atualizarPreco(){
    }
    public void addTipo(){
    }
    public void delProduto(){
    }
    public void addProduto(){
    }
    public void cadastrarFuncionario(){
    }
}




