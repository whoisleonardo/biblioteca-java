package br.com.biblioteca.factory;

import br.com.biblioteca.model.Ebook;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.Notificador;
import br.com.biblioteca.service.PushNotificador;

public class AcervoDigitalFactory implements AcervoFactory {

    @Override
    public Livro criarLivro(String titulo) {
        return new Ebook(titulo);
    }

    @Override
    public Notificador criarNotificador() {
        return new PushNotificador();
    }

    @Override
    public Comprovante criarComprovante() {
        return new ComprovanteDigital();
    }

    @Override
    public String getNomeDoAcervo() {
        return "Acervo Digital";
    }
}
