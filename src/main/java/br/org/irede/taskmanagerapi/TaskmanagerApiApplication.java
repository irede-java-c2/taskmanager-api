package br.org.irede.taskmanagerapi;

import br.org.irede.taskmanagerapi.filtro.TarefaFiltro;
import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.repository.TarefaRepository;
import br.org.irede.taskmanagerapi.model.Tarefa;
import br.org.irede.taskmanagerapi.model.Usuario;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class TaskmanagerApiApplication implements CommandLineRunner {

    private final TarefaRepository tarefaRepository;

    public TaskmanagerApiApplication(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //usuarioRepository.delete(leo);
        System.out.println("====== Buscando todos os registros");
        for (Tarefa t : tarefaRepository.findAll()) {
            System.out.println(t);
        }

        System.out.println("====== Buscando todos os registros de Leo");
        Usuario leo = new Usuario();
        leo.setId(3L);
        for (Tarefa t : tarefaRepository.findByUsuarioId(leo.getId())) {
            System.out.println(t);
        }

        System.out.println("====== Buscando todas as tarefas de Leo concluidas");
        for (Tarefa t : tarefaRepository.findByUsuarioIdAndConcluida(leo.getId(), true)) {
            System.out.println(t);
        }

        System.out.println("====== Buscando todas as tarefas de Leo nao concluidas");
        for (Tarefa t : tarefaRepository.findByUsuarioIdAndConcluida(leo.getId(), false)) {
            System.out.println(t);
        }

        System.out.println("====== Listar Por Prioridade");
        for (Object[] linha : tarefaRepository.contarPorPrioridade()) {
            Prioridade prioridade = (Prioridade) linha[0];
            Long total = (Long) linha[1];
            System.out.println(prioridade + ": " + total);
        }

        //buscar sem filtro
        TarefaFiltro semFiltro = new TarefaFiltro();
        Page<Tarefa> resultadoSemFiltro = tarefaRepository.buscarComFiltros(semFiltro, PageRequest.of(1, 4));
        imprimirPagina(resultadoSemFiltro);
        //buscar por usuario
        TarefaFiltro filtro = new TarefaFiltro();
        filtro.setUsuarioId(1L);
        Page<Tarefa> resultadoUsuario = tarefaRepository.buscarComFiltros(filtro, PageRequest.of(0, 4));
        imprimirPagina(resultadoUsuario);

    }

    private void imprimirPagina(Page<Tarefa> pagina) {
        System.out.println("Página " + (pagina.getNumber() + 1) + " de " + pagina.getTotalPages()
                + " | " + pagina.getTotalElements() + " tarefa(s) no total");
        imprimir(pagina.getContent());
    }

    private void imprimir(List<Tarefa> tarefas) {
        for (Tarefa t : tarefas) {
            System.out.println("#" + t.getId() + " " + t.getTitulo() + " | " + t.getPrioridade()
                    + " | prazo: " + t.getPrazo() + " | concluída: " + t.isConcluida());
        }
    }
}
