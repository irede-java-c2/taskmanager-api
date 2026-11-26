package br.org.irede.taskmanagerapi.dto;

import br.org.irede.taskmanagerapi.model.Etiqueta;
import br.org.irede.taskmanagerapi.model.Prioridade;
import br.org.irede.taskmanagerapi.model.Tarefa;
import java.util.List;
import java.time.LocalDate;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Prioridade prioridade,
        LocalDate prazo,
        boolean concluida,
        Long usuarioId,
        List<String> etiquetas
        ) {

    public static TarefaResponseDTO de(Tarefa tarefa) {
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrioridade(),
                tarefa.getPrazo(),
                tarefa.isConcluida(),
                tarefa.getUsuario().getId(),
                tarefa.getEtiquetas().stream()
                        .map(Etiqueta::getNome).sorted().toList()
        );
    }
}
