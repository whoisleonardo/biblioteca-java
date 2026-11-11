package br.com.biblioteca.model.estado;

public class Disponivel extends EstadoLivro {

    @Override
    public String getNome() {
        return "Disponível";
    }

    @Override
    public EstadoLivro emprestar() {
        return new Emprestado();
    }

    @Override
    public EstadoLivro reservar() {
        return new Reservado();
    }

    @Override
    public EstadoLivro enviarParaManutencao() {
        return new EmManutencao();
    }
}
