package br.com.biblioteca.factory;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.LivroFisico;
import br.com.biblioteca.service.EmailNotificador;
import br.com.biblioteca.service.Notificador;

public class AcervoFisicoFactory implements AcervoFactory {

    @Override
    public Livro criarLivro(String titulo) {
        return new LivroFisico(titulo);
    }

    @Override
    public Notificador criarNotificador() {
        return new EmailNotificador();
    }

    @Override
    public Comprovante criarComprovante() {
        return new ComprovanteImpresso();
    }

    @Override
    public String getNomeDoAcervo() {
        return "Acervo Físico";
    }
}
