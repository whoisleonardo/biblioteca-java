package br.com.biblioteca.model.estado;

public class EmManutencao extends EstadoLivro {

    @Override
    public String getNome() {
        return "Em manutenção";
    }

    @Override
    public EstadoLivro concluirManutencao() {
        return new Disponivel();
    }
}
