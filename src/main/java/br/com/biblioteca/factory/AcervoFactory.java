package br.com.biblioteca.factory;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.Notificador;

public interface AcervoFactory {

    Livro criarLivro(String titulo);

    Notificador criarNotificador();

    Comprovante criarComprovante();

    String getNomeDoAcervo();
}
