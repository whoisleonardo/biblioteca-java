package br.com.biblioteca.service;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.service.observer.EmprestimoObserver;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {

    private final List<EmprestimoObserver> observers = new ArrayList<>();

    public void adicionarObserver(EmprestimoObserver observer) {
        observers.add(observer);
    }

    public void removerObserver(EmprestimoObserver observer) {
        observers.remove(observer);
    }

    public int getTotalDeObservers() {
        return observers.size();
    }

    public void emprestar(Livro livro, Pessoa pessoa) {
        livro.emprestar();
        observers.forEach(o -> o.aoEmprestar(livro, pessoa));
    }

    public void devolver(Livro livro, Pessoa pessoa) {
        livro.devolver();
        observers.forEach(o -> o.aoDevolver(livro, pessoa));
    }

    public void reservar(Livro livro, Pessoa pessoa) {
        livro.reservar();
        observers.forEach(o -> o.aoReservar(livro, pessoa));
    }
}
