package br.com.biblioteca.service.observer;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import java.util.LinkedHashMap;
import java.util.Map;

public class EstatisticaObserver implements EmprestimoObserver {

    private final Map<String, Integer> porTipo = new LinkedHashMap<>();

    @Override
    public void aoEmprestar(Livro livro, Pessoa pessoa) {
        porTipo.merge(pessoa.getTipo(), 1, Integer::sum);
    }

    public Map<String, Integer> getPorTipo() {
        return Map.copyOf(porTipo);
    }
}
