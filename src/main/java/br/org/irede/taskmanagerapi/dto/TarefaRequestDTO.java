package br.org.irede.taskmanagerapi.dto;

import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.model.Tarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TarefaRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 100)
        String titulo,
        String descricao,
        @NotNull(message = "A prioridade é obrigatória")
        Prioridade prioridade,
        LocalDate prazo
        ) {
    
    public void copiarPara(Tarefa tarefa) {
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setPrioridade(prioridade);
        tarefa.setPrazo(prazo);
    }
}
