package br.org.irede.taskmanagerapi;

import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.repository.TarefaRepository;
import br.org.irede.taskmanagerapi.model.Tarefa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmanagerApiApplication implements CommandLineRunner {

    private final TarefaRepository tarefaRepository;
    
    public TaskmanagerApiApplication(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tarefa t1 = new Tarefa("Estudar JPA", "Revisar @Entity e @Id", Prioridade.ALTA);
        Tarefa t2 = new Tarefa("Configurar PostgreSQL", "Criar Banco TaskManager", Prioridade.MEDIA);
        
        tarefaRepository.save(t1);
        tarefaRepository.save(t2);
        
        System.out.println("====== Buscando todos os registros");
        for(Tarefa t : tarefaRepository.findAll()){
            System.out.println(t);
        }
    }

}

