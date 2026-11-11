package br.com.biblioteca.model.estado;

public abstract class EstadoLivro {

    public abstract String getNome();

    public EstadoLivro emprestar() {
        throw naoPermitido("emprestar");
    }

    public EstadoLivro devolver() {
        throw naoPermitido("devolver");
    }

    public EstadoLivro reservar() {
        throw naoPermitido("reservar");
    }

    public EstadoLivro enviarParaManutencao() {
        throw naoPermitido("enviar para manutenção");
    }

    public EstadoLivro concluirManutencao() {
        throw naoPermitido("concluir a manutenção de");
    }

    protected IllegalStateException naoPermitido(String acao) {
        return new IllegalStateException(
                "Não é possível " + acao + " um livro que está no estado "
                        + getNome().toUpperCase() + "."
        );
    }

    @Override
    public String toString() {
        return getNome();
    }
}
