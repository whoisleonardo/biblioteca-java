package br.com.biblioteca.model;

public class LivroFisico extends Livro {

    public LivroFisico(String titulo) {
        super(titulo);
    }

    @Override
    public String getFormato() {
        return "Impresso";
    }
}
