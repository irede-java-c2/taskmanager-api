package br.org.irede.taskmanagerapi.exception;

public class TarefaNaoEncontradaException extends RecursoNaoEncontradoException {

    public TarefaNaoEncontradaException(Long id) {
        super("Tarefa " + id + " não encontrada");
    }
}
