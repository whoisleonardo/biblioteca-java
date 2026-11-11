package br.com.biblioteca.service.observer;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.service.Notificador;

public class NotificadorObserver implements EmprestimoObserver {

    private final Notificador notificador;

    public NotificadorObserver(Notificador notificador) {
        this.notificador = notificador;
    }

    @Override
    public void aoEmprestar(Livro livro, Pessoa pessoa) {
        notificador.notificar("Empréstimo para " + pessoa.getNome()
                + ": " + livro.getTitulo());
    }

    @Override
    public void aoDevolver(Livro livro, Pessoa pessoa) {
        notificador.notificar("Devolução de " + pessoa.getNome()
                + ": " + livro.getTitulo());
    }

    @Override
    public void aoReservar(Livro livro, Pessoa pessoa) {
        notificador.notificar("Reserva para " + pessoa.getNome()
                + ": " + livro.getTitulo());
    }
}
