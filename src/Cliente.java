public class Cliente extends Pessoa{
    private int cpf;

    public Cliente(){
        super();
        this.cpf = 00000000000;
    }
    public Cliente(String nome, String email, String senha, int cpf){
        super(nome,email,senha);
        this.cpf = cpf;
    }
    public int getCpf() {
        return cpf;
    }
    public void criarCarrinho(){
    }
}
