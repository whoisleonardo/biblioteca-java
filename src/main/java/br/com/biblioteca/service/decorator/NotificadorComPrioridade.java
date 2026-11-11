package br.com.biblioteca.service.decorator;

import br.com.biblioteca.service.Notificador;

public class NotificadorComPrioridade extends NotificadorDecorator {

    public NotificadorComPrioridade(Notificador wrapped) {
        super(wrapped);
    }

    @Override
    public void notificar(String mensagem) {
        wrapped.notificar("** URGENTE ** " + mensagem);
    }
}
