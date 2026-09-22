public class EmailNotificador implements Notificador {

    @Override
    public void notificar(String mensagem) {
        System.out.println("[EMAIL] " + mensagem);
    }
}
