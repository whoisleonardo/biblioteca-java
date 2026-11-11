package br.com.biblioteca.model.estado;

public class Reservado extends EstadoLivro {

    @Override
    public String getNome() {
        return "Reservado";
    }

    @Override
    public EstadoLivro emprestar() {
        return new Emprestado();
    }

    @Override
    public EstadoLivro devolver() {
        return new Disponivel();
    }
}
