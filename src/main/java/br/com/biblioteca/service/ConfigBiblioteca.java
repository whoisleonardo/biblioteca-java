package br.com.biblioteca.service;

public class ConfigBiblioteca {

    private static ConfigBiblioteca instancia;

    private String versao;

    private ConfigBiblioteca() {
        this.versao = "1.0";
    }

    public static ConfigBiblioteca getInstance() {

        if (instancia == null) {
            instancia = new ConfigBiblioteca();
        }

        return instancia;
    }

    public String getVersao() {
        return versao;
    }
}
