package br.org.irede.taskmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.irede.taskmanagerapi.model.Tarefa;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, TarefaRepositoryCustom {
    
    @Query("SELECT t FROM Tarefa t WHERE t.usuario.id = :usuarioId ORDER BY t.id")
    List<Tarefa> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT t FROM Tarefa t WHERE t.usuario.id = :usuarioId AND t.concluida = :concluida ORDER BY t.id")
    List<Tarefa> findByUsuarioIdAndConcluida(@Param("usuarioId") Long usuarioId, @Param("concluida") boolean concluida);
    
    @Query("SELECT t.prioridade, COUNT(t) FROM Tarefa t GROUP BY t.prioridade ORDER BY t.prioridade")
    List<Object[]> contarPorPrioridade();
    
}
