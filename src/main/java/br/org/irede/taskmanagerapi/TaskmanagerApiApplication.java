package br.org.irede.taskmanagerapi;

import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.repository.TarefaRepository;
import br.org.irede.taskmanagerapi.model.Tarefa;
import br.org.irede.taskmanagerapi.model.Usuario;
import br.org.irede.taskmanagerapi.model.Etiqueta;
import br.org.irede.taskmanagerapi.repository.EtiquetaRepository;
import br.org.irede.taskmanagerapi.repository.UsuarioRepository;
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
        //inicializar e persistir a etiqueta
        Etiqueta estudo = etiquetaRepository.save(new Etiqueta("Estudo"));
        Etiqueta trabalho = etiquetaRepository.save(new Etiqueta("Trabalho"));
        Etiqueta urgente = etiquetaRepository.save(new Etiqueta("Urgente"));
        
        Usuario ana = new Usuario("Ana Souza", "ana@email.com", "123456");
        Usuario bruno = new Usuario("Bruno Lima", "bruno@email.com", "123456");
        
        usuarioRepository.save(ana);
        usuarioRepository.save(bruno);
        
        Tarefa t1 = new Tarefa("Estudar JPA", "Revisar @Entity e @Id", Prioridade.ALTA);
        t1.adicionarEtiqueta(estudo);
        t1.adicionarEtiqueta(urgente);
        ana.adicionarTarefa(t1);
        
        Tarefa t2 = new Tarefa("Configurar PostgreSQL", "Criar Banco TaskManager", Prioridade.MEDIA);
        t2.adicionarEtiqueta(trabalho);
        bruno.adicionarTarefa(t2);
        
        tarefaRepository.save(t1);
        tarefaRepository.save(t2);
        
        System.out.println("====== Buscando todos os registros");
        for(Tarefa t : tarefaRepository.findAll()){
            System.out.println(t);
        }
    }

}

