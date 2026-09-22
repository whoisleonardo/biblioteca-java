public class Professor extends Pessoa {

    public Professor(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "Professor";
    }
}
