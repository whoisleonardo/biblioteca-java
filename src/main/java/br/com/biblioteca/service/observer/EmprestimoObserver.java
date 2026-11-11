package br.com.biblioteca.service.observer;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;

public interface EmprestimoObserver {

    default void aoEmprestar(Livro livro, Pessoa pessoa) { }

    default void aoDevolver(Livro livro, Pessoa pessoa) { }

    default void aoReservar(Livro livro, Pessoa pessoa) { }
}
