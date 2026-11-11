package br.com.biblioteca.model;

import br.com.biblioteca.model.estado.Disponivel;
import br.com.biblioteca.model.estado.EstadoLivro;

public abstract class Livro {

    private final String titulo;
    private EstadoLivro estado;

    protected Livro(String titulo) {
        this.titulo = titulo;
        this.estado = new Disponivel();
    }

    public abstract String getFormato();

    public String getTitulo() {
        return titulo;
    }

    public EstadoLivro getEstado() {
        return estado;
    }

    public void emprestar() {
        this.estado = estado.emprestar();
    }

    public void devolver() {
        this.estado = estado.devolver();
    }

    public void reservar() {
        this.estado = estado.reservar();
    }

    public void enviarParaManutencao() {
        this.estado = estado.enviarParaManutencao();
    }

    public void concluirManutencao() {
        this.estado = estado.concluirManutencao();
    }

    public boolean isDisponivel() {
        return estado instanceof Disponivel;
    }

    @Override
    public String toString() {
        return String.format("%-24s %-10s %s", titulo, getFormato(), estado.getNome());
    }
}
