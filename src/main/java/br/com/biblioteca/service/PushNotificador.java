package br.com.biblioteca.service;

public class PushNotificador implements Notificador {

    @Override
    public void notificar(String mensagem) {
        System.out.println("      push         " + mensagem);
    }
}
