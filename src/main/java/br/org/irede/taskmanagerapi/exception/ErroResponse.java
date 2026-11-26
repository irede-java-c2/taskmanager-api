package br.org.irede.taskmanagerapi.exception;

import java.util.List;
import java.time.Instant;

public record ErroResponse(Instant timestamp, int status, String erro,
        String mensagem, String caminho, List<String> detalhes) {

}
