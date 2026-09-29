package br.com.biblioteca;

import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Biblioteca;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.service.ConfigBiblioteca;
import br.com.biblioteca.service.EmailNotificador;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.Notificador;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("      SISTEMA DE BIBLIOTECA");
        System.out.println("================================");

        ConfigBiblioteca config1 =
                ConfigBiblioteca.getInstance();

        ConfigBiblioteca config2 =
                ConfigBiblioteca.getInstance();

        System.out.println(
                "Versão do sistema: " + config1.getVersao()
        );

        System.out.println(
                "Singleton funcionando: "
                        + (config1 == config2)
        );

        Biblioteca biblioteca =
                new Biblioteca("Biblioteca Universitária");

        Livro livro1 =
                new Livro("Clean Code");

        Livro livro2 =
                new Livro("Design Patterns");

        Livro livro3 =
                new Livro("Java: Como Programar");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);

        biblioteca.listarLivros();

        Pessoa pessoa1 =
                new Aluno("Leonardo");

        Pessoa pessoa2 =
                new Professor("Marlon");

        System.out.println("\n--- USUÁRIOS ---");

        System.out.println(
                pessoa1.getNome()
                        + " - "
                        + pessoa1.getTipo()
        );

        System.out.println(
                pessoa2.getNome()
                        + " - "
                        + pessoa2.getTipo()
        );

        Notificador notificador =
                new EmailNotificador();

        EmprestimoService emprestimoService =
                new EmprestimoService(notificador);

        System.out.println("\n--- EMPRÉSTIMOS ---");

        try {

            emprestimoService.emprestar(
                    livro1,
                    pessoa1
            );

            emprestimoService.emprestar(
                    livro2,
                    pessoa2
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }

        biblioteca.listarLivros();

        System.out.println(
                "\n--- TESTE DE EXCEÇÃO ---"
        );

        try {

            emprestimoService.emprestar(
                    livro1,
                    pessoa2
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erro tratado: " + e.getMessage()
            );
        }

        System.out.println(
                "\n--- DEVOLUÇÃO ---"
        );

        try {

            emprestimoService.devolver(
                    livro1,
                    pessoa1
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }

        biblioteca.listarLivros();

        System.out.println(
                "\nSistema finalizado."
        );
    }
}
