package br.com.biblioteca.factory;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;

public class ComprovanteImpresso implements Comprovante {

    @Override
    public void emitir(Livro livro, Pessoa pessoa) {
        System.out.println("      comprovante  ticket impresso no balcão");
    }
}
