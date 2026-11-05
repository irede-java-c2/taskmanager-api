package br.org.irede.taskmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.irede.taskmanagerapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
