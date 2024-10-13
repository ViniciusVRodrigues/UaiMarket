package payment;

public class Pix implements InterfacePagamento{
    public boolean pagar(Float valor){
        System.out.println("Pagamento de R$"+valor+" efetuado com sucesso via Pix");
        return true;
    }
}
