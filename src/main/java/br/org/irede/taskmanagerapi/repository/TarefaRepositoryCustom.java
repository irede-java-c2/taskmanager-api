package br.org.irede.taskmanagerapi.repository;


import br.org.irede.taskmanagerapi.filtro.TarefaFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.org.irede.taskmanagerapi.model.Tarefa;

public interface TarefaRepositoryCustom {

    Page<Tarefa> buscarComFiltros(TarefaFiltro filtro, Pageable pageable);
    
}
