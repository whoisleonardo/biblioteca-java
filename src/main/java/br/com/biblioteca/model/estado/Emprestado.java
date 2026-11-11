package br.com.biblioteca.model.estado;

public class Emprestado extends EstadoLivro {

    @Override
    public String getNome() {
        return "Emprestado";
    }

    @Override
    public EstadoLivro devolver() {
        return new Disponivel();
    }
}
