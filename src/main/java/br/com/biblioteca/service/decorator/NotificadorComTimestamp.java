package br.com.biblioteca.service.decorator;

import br.com.biblioteca.service.Notificador;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NotificadorComTimestamp extends NotificadorDecorator {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("HH:mm");

    public NotificadorComTimestamp(Notificador wrapped) {
        super(wrapped);
    }

    @Override
    public void notificar(String mensagem) {
        wrapped.notificar(LocalTime.now().format(FORMATO) + "  " + mensagem);
    }
}
