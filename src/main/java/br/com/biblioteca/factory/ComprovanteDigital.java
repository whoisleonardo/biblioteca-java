package br.com.biblioteca.factory;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;

public class ComprovanteDigital implements Comprovante {

    @Override
    public void emitir(Livro livro, Pessoa pessoa) {
        System.out.println("      comprovante  PDF assinado enviado por e-mail");
    }
}
