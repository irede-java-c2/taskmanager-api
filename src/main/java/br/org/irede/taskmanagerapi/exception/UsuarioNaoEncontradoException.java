package br.org.irede.taskmanagerapi.exception;

public class UsuarioNaoEncontradoException extends RecursoNaoEncontradoException {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário " + id + " não encontrado");
    }

}
