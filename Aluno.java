public class Aluno extends Pessoa {

    public Aluno(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }
}
