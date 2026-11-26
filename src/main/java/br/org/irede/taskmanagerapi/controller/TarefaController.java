package br.org.irede.taskmanagerapi.controller;

import br.org.irede.taskmanagerapi.exception.TarefaNaoEncontradaException;
import br.org.irede.taskmanagerapi.exception.UsuarioNaoEncontradoException;
import br.org.irede.taskmanagerapi.repository.TarefaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.org.irede.taskmanagerapi.model.Usuario;
import br.org.irede.taskmanagerapi.model.Tarefa;
import br.org.irede.taskmanagerapi.repository.UsuarioRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public TarefaController(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id) {
        return tarefaRepository.findById(id).orElseThrow(
                () -> new TarefaNaoEncontradaException(id)
        );
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(
            @Valid @RequestBody Tarefa tarefa,
            @RequestParam Long usuarioId
    ) {
        Usuario dono = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioNaoEncontradoException(usuarioId)
        );
        tarefa.setUsuario(dono);
        Tarefa salva = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa dados) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(
                () -> new TarefaNaoEncontradaException(id)
        );
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setDescricao(dados.getDescricao());
        tarefa.setPrioridade(dados.getPrioridade());
        tarefa.setPrazo(dados.getPrazo());
        tarefa.setConcluida(dados.isConcluida());
        return tarefaRepository.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNaoEncontradaException(id);
        }

        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
