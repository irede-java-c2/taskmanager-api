package br.org.irede.taskmanagerapi.config;

import br.org.irede.taskmanagerapi.exception.ErroResponse;
import br.org.irede.taskmanagerapi.exception.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> naoEncontrado(RecursoNaoEncontradoException ex,
            HttpServletRequest req) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage(), List.of(), req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> dadosInvalidos(MethodArgumentNotValidException ex,
            HttpServletRequest req) {
        List<String> campos = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return responder(HttpStatus.BAD_REQUEST, "Dados inválidos", campos, req);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> corpoInvalido(HttpMessageNotReadableException ex,
            HttpServletRequest req) {
        return responder(HttpStatus.BAD_REQUEST,
                "Corpo da requisição inválido ou mal formatado", List.of(), req);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,
        MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErroResponse> parametroInvalido(Exception ex, HttpServletRequest req) {
        return responder(HttpStatus.BAD_REQUEST, "Parâmetro ausente ou inválido", List.of(), req);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErroResponse> rotaInexistente(NoResourceFoundException ex,
            HttpServletRequest req) {
        return responder(HttpStatus.NOT_FOUND, "Rota não encontrada", List.of(), req);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponse> metodoNaoSuportado(HttpRequestMethodNotSupportedException ex,
            HttpServletRequest req) {
        return responder(HttpStatus.METHOD_NOT_ALLOWED,
                "Método " + ex.getMethod() + " não é aceito nesta rota", List.of(), req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> erroInesperado(Exception ex, HttpServletRequest req) {
        log.error("Erro inesperado em {}", req.getRequestURI(), ex);
        return responder(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno. Tente novamente mais tarde.", List.of(), req);
    }

    private ResponseEntity<ErroResponse> responder(HttpStatus status, String mensagem,
            List<String> detalhes, HttpServletRequest req) {
        ErroResponse corpo = new ErroResponse(Instant.now(), status.value(),
                status.getReasonPhrase(), mensagem, req.getRequestURI(), detalhes);
        return ResponseEntity.status(status).body(corpo);
    }
}
