package br.com.biblioteca.service.decorator;

import br.com.biblioteca.service.Notificador;
import java.util.ArrayList;
import java.util.List;

public class NotificadorComLog extends NotificadorDecorator {

    private final List<String> registros = new ArrayList<>();

    public NotificadorComLog(Notificador wrapped) {
        super(wrapped);
    }

    @Override
    public void notificar(String mensagem) {
        registros.add(mensagem);
        wrapped.notificar(mensagem);
    }

    public List<String> getRegistros() {
        return List.copyOf(registros);
    }

    public int getTotal() {
        return registros.size();
    }
}
