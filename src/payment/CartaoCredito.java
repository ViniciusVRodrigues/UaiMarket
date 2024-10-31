package payment;
//utilizado no strategy
public class CartaoCredito implements InterfacePagamento{
    public boolean pagar(Float valor){
        System.out.println("Pagamento de R$"+valor+" efetuado com sucesso via Cartão de Crédito");
        return true;
    }
}
