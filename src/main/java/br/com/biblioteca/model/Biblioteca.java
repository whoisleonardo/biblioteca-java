package br.com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private final String nome;
    private final List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public int getTotalDeLivros() {
        return livros.size();
    }

    public List<Livro> getLivros() {
        return List.copyOf(livros);
    }
}
