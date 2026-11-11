package br.com.biblioteca.factory;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;

public interface Comprovante {

    void emitir(Livro livro, Pessoa pessoa);
}
