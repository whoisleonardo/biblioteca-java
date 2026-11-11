package br.com.biblioteca.model;

public class Ebook extends Livro {

    public Ebook(String titulo) {
        super(titulo);
    }

    @Override
    public String getFormato() {
        return "E-book";
    }
}
