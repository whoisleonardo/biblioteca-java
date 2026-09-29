package br.com.biblioteca.service;

public class EmailNotificador implements Notificador {

    @Override
    public void notificar(String mensagem) {
        System.out.println("[EMAIL] " + mensagem);
    }
}
