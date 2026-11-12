package br.org.irede.taskmanagerapi;

import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.repository.TarefaRepository;
import br.org.irede.taskmanagerapi.model.Tarefa;
import br.org.irede.taskmanagerapi.model.Usuario;
import br.org.irede.taskmanagerapi.model.Etiqueta;
import br.org.irede.taskmanagerapi.repository.EtiquetaRepository;
import br.org.irede.taskmanagerapi.repository.UsuarioRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmanagerApiApplication implements CommandLineRunner {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EtiquetaRepository etiquetaRepository;
    
    public TaskmanagerApiApplication(TarefaRepository tarefaRepository,
            UsuarioRepository usuarioRepository,
            EtiquetaRepository etiquetaRepository){
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
        this.etiquetaRepository = etiquetaRepository;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        carregarDados();
        
        
        
        //usuarioRepository.delete(leo);
        
        System.out.println("====== Buscando todos os registros");
        for(Tarefa t : tarefaRepository.findAll()){
            System.out.println(t);
        }
        
        System.out.println("====== Buscando todos os registros de Leo");
        Usuario leo = new Usuario();
        leo.setId(3L);
        for(Tarefa t : tarefaRepository.findByUsuarioId(leo.getId())){
            System.out.println(t);
        }
        
        System.out.println("====== Buscando todas as tarefas de Leo concluidas");
        for(Tarefa t : tarefaRepository.findByUsuarioIdAndConcluida(leo.getId(), true)){
            System.out.println(t);
        }
        
        System.out.println("====== Buscando todas as tarefas de Leo nao concluidas");
        for(Tarefa t : tarefaRepository.findByUsuarioIdAndConcluida(leo.getId(), false)){
            System.out.println(t);
        }
        
        System.out.println("====== Listar Por Prioridade");
        for(Object[] linha : tarefaRepository.contarPorPrioridade()){
            Prioridade prioridade = (Prioridade) linha[0];
            Long total = (Long) linha[1];
            System.out.println(prioridade + ": "+total);
        }
    }
    
    public void carregarDados(){
        Etiqueta estudo = etiquetaRepository.save(new Etiqueta("Estudo"));
        Etiqueta trabalho = etiquetaRepository.save(new Etiqueta("Trabalho"));
        Etiqueta urgente = etiquetaRepository.save(new Etiqueta("Urgente"));

        Usuario ana = new Usuario("Ana Souza", "ana@email.com", "123456");
        Usuario bruno = new Usuario("Bruno Lima", "bruno@email.com", "123456");

        // Tarefas da Ana (ids 1, 2 e 3)
        Tarefa t1 = new Tarefa("Estudar JPA", "Revisar @Entity e @Id", Prioridade.ALTA);
        t1.setPrazo(LocalDate.of(2026, 11, 5));
        t1.adicionarEtiqueta(estudo);
        t1.adicionarEtiqueta(urgente);
        ana.adicionarTarefa(t1);

        Tarefa t2 = new Tarefa("Escrever consultas JPQL", "Criar as @Query do TarefaRepository", Prioridade.ALTA);
        t2.setPrazo(LocalDate.of(2026, 11, 12));
        t2.adicionarEtiqueta(estudo);
        ana.adicionarTarefa(t2);

        Tarefa t3 = new Tarefa("Revisar relacionamentos", "Conferir fetch e cascade", Prioridade.BAIXA);
        t3.setPrazo(LocalDate.of(2026, 11, 4));
        t3.setConcluida(true);
        t3.adicionarEtiqueta(estudo);
        ana.adicionarTarefa(t3);

        // Tarefas do Bruno (ids 4, 5 e 6)
        Tarefa t4 = new Tarefa("Configurar PostgreSQL", "Criar Banco TaskManager", Prioridade.MEDIA);
        t4.setPrazo(LocalDate.of(2026, 11, 3));
        t4.setConcluida(true);
        t4.adicionarEtiqueta(trabalho);
        bruno.adicionarTarefa(t4);

        Tarefa t5 = new Tarefa("Montar coleção do Postman", "Preparar as requisições da aula 07", Prioridade.BAIXA);
        // t5 fica sem prazo de propósito
        t5.adicionarEtiqueta(trabalho);
        bruno.adicionarTarefa(t5);

        Tarefa t6 = new Tarefa("Preparar apresentação", "Slides do assunto 03", Prioridade.ALTA);
        t6.setPrazo(LocalDate.of(2026, 11, 8));
        t6.adicionarEtiqueta(trabalho);
        t6.adicionarEtiqueta(urgente);
        bruno.adicionarTarefa(t6);

        // Cascade ALL (aula 04): salvar o usuário salva as tarefas dele
        usuarioRepository.save(ana);
        usuarioRepository.save(bruno);
        
        Usuario leo = new Usuario("Leo", "leo@email.com", "123");
        Tarefa t7 = new Tarefa("Configurar Lombok", "Adicionar lib ao projeto", Prioridade.MEDIA);
        t7.adicionarEtiqueta(trabalho);
        leo.adicionarTarefa(t7);
        
        Tarefa t8 = new Tarefa("Spike para Rag", "Estudar a viabilidade para o projeto", Prioridade.ALTA);
        t8.setConcluida(true);
        t8.adicionarEtiqueta(estudo);
        t8.adicionarEtiqueta(urgente);
        leo.adicionarTarefa(t8);
        //salvando tarefa e usuario juntos, sem a necessidade do repository de tarefa
        usuarioRepository.save(leo);
    }

}

