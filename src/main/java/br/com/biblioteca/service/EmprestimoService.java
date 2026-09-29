package br.com.biblioteca.service;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;

public class EmprestimoService {

    private Notificador notificador;

    public EmprestimoService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void emprestar(Livro livro, Pessoa pessoa) {

        if (!livro.isDisponivel()) {
            throw new IllegalArgumentException(
                    "O livro \"" + livro.getTitulo() + "\" não está disponível."
            );
        }

        livro.setDisponivel(false);

        System.out.println(
                pessoa.getTipo()
                        + " " + pessoa.getNome()
                        + " pegou o livro \""
                        + livro.getTitulo() + "\"."
        );

        notificador.notificar(
                "Empréstimo realizado para "
                        + pessoa.getNome()
                        + ": " + livro.getTitulo()
        );
    }

    public void devolver(Livro livro, Pessoa pessoa) {

        if (livro.isDisponivel()) {
            throw new IllegalArgumentException(
                    "O livro \"" + livro.getTitulo() + "\" não está emprestado."
            );
        }

        livro.setDisponivel(true);

        System.out.println(
                pessoa.getNome()
                        + " devolveu o livro \""
                        + livro.getTitulo() + "\"."
        );
    }
}
