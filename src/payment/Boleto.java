package payment;
//utilizado no strategy
public class Boleto implements InterfacePagamento{
    public boolean pagar(Float valor){
        System.out.println("Pagamento de R$"+valor+" efetuado com sucesso via Boleto");
        return true;
    }
}
