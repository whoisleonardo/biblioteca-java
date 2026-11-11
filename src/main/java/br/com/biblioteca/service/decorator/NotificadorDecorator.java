package br.com.biblioteca.service.decorator;

import br.com.biblioteca.service.Notificador;

public abstract class NotificadorDecorator implements Notificador {

    protected final Notificador wrapped;

    protected NotificadorDecorator(Notificador wrapped) {
        this.wrapped = wrapped;
    }
}
