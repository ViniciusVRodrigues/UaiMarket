package notification;

import model.Mercado;

import java.util.ArrayList;
import java.util.List;

public class ControleEstoqueNotificador {
    private List<InterfaceNotificacao> notificacoes;
    private static ControleEstoqueNotificador instance;

    private ControleEstoqueNotificador(){
        Mercado mercado = Mercado.getInstance();
        notificacoes = new ArrayList<>();
        notificacoes.addAll(mercado.getColaboradores());
    }

    public static ControleEstoqueNotificador getInstance(){
        if(instance == null){
            instance = new ControleEstoqueNotificador();
        }
        return instance;
    }

    public void addNotificacao(InterfaceNotificacao notificacao){
        notificacoes.add(notificacao);
    }

    public void removeNotificacao(InterfaceNotificacao notificacao){
        notificacoes.remove(notificacao);
    }

    public void notificar(String mensagem){
        for(InterfaceNotificacao notificacao : notificacoes){
            notificacao.update(mensagem);
        }
    }
}
