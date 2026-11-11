package br.com.biblioteca.service.observer;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class HistoricoObserver implements EmprestimoObserver {

    private final List<String> registros = new ArrayList<>();

    @Override
    public void aoEmprestar(Livro livro, Pessoa pessoa) {
        registrar("saída", livro, pessoa);
    }

    @Override
    public void aoDevolver(Livro livro, Pessoa pessoa) {
        registrar("retorno", livro, pessoa);
    }

    @Override
    public void aoReservar(Livro livro, Pessoa pessoa) {
        registrar("reserva", livro, pessoa);
    }

    private void registrar(String evento, Livro livro, Pessoa pessoa) {
        registros.add(String.format("%-8s %-24s %s",
                evento, livro.getTitulo(), pessoa.getNome()));
    }

    public List<String> getRegistros() {
        return List.copyOf(registros);
    }
}
