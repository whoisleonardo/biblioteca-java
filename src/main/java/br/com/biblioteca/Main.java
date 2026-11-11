package br.com.biblioteca;

import br.com.biblioteca.factory.AcervoDigitalFactory;
import br.com.biblioteca.factory.AcervoFactory;
import br.com.biblioteca.factory.AcervoFisicoFactory;
import br.com.biblioteca.factory.Comprovante;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Biblioteca;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.service.ConfigBiblioteca;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.Notificador;
import br.com.biblioteca.service.decorator.NotificadorComLog;
import br.com.biblioteca.service.decorator.NotificadorComPrioridade;
import br.com.biblioteca.service.decorator.NotificadorComTimestamp;
import br.com.biblioteca.service.observer.EstatisticaObserver;
import br.com.biblioteca.service.observer.HistoricoObserver;
import br.com.biblioteca.service.observer.NotificadorObserver;

public class Main {

    private static final String LINHA = "─".repeat(62);

    public static void main(String[] args) {

        cabecalho();

        secao("Singleton", "ConfigBiblioteca");

        ConfigBiblioteca config = ConfigBiblioteca.getInstance();
        item("versão", config.getVersao());
        item("mesma instância", String.valueOf(
                config == ConfigBiblioteca.getInstance()));

        secao("Abstract Factory", "famílias de acervo");

        AcervoFactory fisico = new AcervoFisicoFactory();
        AcervoFactory digital = new AcervoDigitalFactory();

        item(fisico.getNomeDoAcervo(),
                "LivroFisico · EmailNotificador · ComprovanteImpresso");
        item(digital.getNomeDoAcervo(),
                "Ebook · PushNotificador · ComprovanteDigital");

        Biblioteca biblioteca = new Biblioteca("Biblioteca Universitária");

        Livro cleanCode = fisico.criarLivro("Clean Code");
        Livro designPatterns = fisico.criarLivro("Design Patterns");
        Livro javaComoProgramar = digital.criarLivro("Java: Como Programar");

        biblioteca.adicionarLivro(cleanCode);
        biblioteca.adicionarLivro(designPatterns);
        biblioteca.adicionarLivro(javaComoProgramar);

        secao("Decorator", "camadas sobre o Notificador da fábrica");

        NotificadorComLog log = new NotificadorComLog(
                new NotificadorComTimestamp(fisico.criarNotificador()));

        Notificador canalFisico = log;
        Notificador canalDigital = new NotificadorComPrioridade(
                new NotificadorComTimestamp(digital.criarNotificador()));

        item("canal físico", "Log( Timestamp( Email ) )");
        item("canal digital", "Prioridade( Timestamp( Push ) )");

        secao("Observer", "inscritos no EmprestimoService");

        EmprestimoService servicoFisico = new EmprestimoService();
        EmprestimoService servicoDigital = new EmprestimoService();

        HistoricoObserver historico = new HistoricoObserver();
        EstatisticaObserver estatistica = new EstatisticaObserver();

        NotificadorObserver avisoFisico = new NotificadorObserver(canalFisico);
        NotificadorObserver avisoDigital = new NotificadorObserver(canalDigital);

        servicoFisico.adicionarObserver(avisoFisico);
        servicoFisico.adicionarObserver(historico);
        servicoFisico.adicionarObserver(estatistica);

        servicoDigital.adicionarObserver(avisoDigital);
        servicoDigital.adicionarObserver(historico);
        servicoDigital.adicionarObserver(estatistica);

        item("observers", servicoFisico.getTotalDeObservers()
                + " por serviço: Notificador · Histórico · Estatística");

        secao("Acervo inicial", biblioteca.getTotalDeLivros() + " títulos");
        biblioteca.getLivros().forEach(l -> System.out.println("   " + l));

        Pessoa leonardo = new Aluno("Leonardo");
        Pessoa marlon = new Professor("Marlon");

        Comprovante comprovanteFisico = fisico.criarComprovante();
        Comprovante comprovanteDigital = digital.criarComprovante();

        secao("Operações", "State muda o livro, Observer propaga o evento");

        acao("empréstimo", cleanCode, leonardo);
        servicoFisico.emprestar(cleanCode, leonardo);
        comprovanteFisico.emitir(cleanCode, leonardo);

        acao("empréstimo", javaComoProgramar, marlon);
        servicoDigital.emprestar(javaComoProgramar, marlon);
        comprovanteDigital.emitir(javaComoProgramar, marlon);

        acao("devolução", cleanCode, leonardo);
        servicoFisico.devolver(cleanCode, leonardo);

        servicoFisico.removerObserver(avisoFisico);
        servicoDigital.removerObserver(avisoDigital);

        secao("State", "ciclo de vida de \"Design Patterns\"");
        item("observers", "NotificadorObserver removido · Histórico e Estatística seguem ativos");

        transicao(designPatterns, "reservar",
                () -> servicoFisico.reservar(designPatterns, marlon));
        transicao(designPatterns, "emprestar",
                () -> servicoFisico.emprestar(designPatterns, marlon));
        transicao(designPatterns, "devolver",
                () -> servicoFisico.devolver(designPatterns, marlon));
        transicao(designPatterns, "enviar p/ manutenção",
                designPatterns::enviarParaManutencao);
        transicao(designPatterns, "concluir manutenção",
                designPatterns::concluirManutencao);

        secao("State", "transições inválidas recusadas pelo próprio estado");

        recusar("emprestar", javaComoProgramar,
                () -> servicoDigital.emprestar(javaComoProgramar, leonardo));
        recusar("devolver", designPatterns,
                () -> servicoFisico.devolver(designPatterns, leonardo));

        designPatterns.enviarParaManutencao();
        recusar("emprestar", designPatterns,
                () -> servicoFisico.emprestar(designPatterns, leonardo));
        designPatterns.concluirManutencao();

        secao("Acervo final", biblioteca.getTotalDeLivros() + " títulos");
        biblioteca.getLivros().forEach(l -> System.out.println("   " + l));

        secao("HistoricoObserver", historico.getRegistros().size() + " eventos");
        historico.getRegistros().forEach(r -> System.out.println("   " + r));

        secao("EstatisticaObserver", "empréstimos por tipo de usuário");
        estatistica.getPorTipo().forEach(Main::item);

        secao("NotificadorComLog", log.getTotal() + " mensagens registradas");

        System.out.println("\n" + LINHA + "\n");
    }

    private static void cabecalho() {
        System.out.println("\n" + LINHA);
        System.out.println("  SISTEMA DE BIBLIOTECA  ·  Padrões de Projeto (GoF)");
        System.out.println(LINHA);
    }

    private static void secao(String padrao, String descricao) {
        System.out.printf("%n%s · %s%n", padrao, descricao);
    }

    private static void item(String rotulo, Object valor) {
        System.out.printf("   %-18s %s%n", rotulo, valor);
    }

    private static void acao(String tipo, Livro livro, Pessoa pessoa) {
        System.out.printf("   %-12s %s → %s (%s)%n",
                tipo, livro.getTitulo(), pessoa.getNome(), pessoa.getTipo());
    }

    private static void transicao(Livro livro, String acao, Runnable operacao) {
        String antes = livro.getEstado().getNome();
        operacao.run();
        System.out.printf("   %-14s %-22s → %s%n",
                antes, acao, livro.getEstado().getNome());
    }

    private static void recusar(String acao, Livro livro, Runnable operacao) {
        String estado = livro.getEstado().getNome();
        try {
            operacao.run();
            System.out.printf("   %-14s %-22s → não deveria ter passado%n",
                    estado, acao);
        } catch (IllegalStateException e) {
            System.out.printf("   %-14s %-22s → recusado%n", estado, acao);
            System.out.println("                  " + e.getMessage());
        }
    }
}
