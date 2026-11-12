package br.org.irede.taskmanagerapi.filtro;

import br.org.irede.taskmanagerapi.model.Prioridade;
import java.time.LocalDate;

public class TarefaFiltro {
    
    private String titulo;
    private Prioridade prioridade;
    private Boolean concluida;
    private Long usuarioId;
    private LocalDate prazoDe;
    private LocalDate prazoAte;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getPrazoDe() {
        return prazoDe;
    }

    public void setPrazoDe(LocalDate prazoDe) {
        this.prazoDe = prazoDe;
    }

    public LocalDate getPrazoAte() {
        return prazoAte;
    }

    public void setPrazoAte(LocalDate prazoAte) {
        this.prazoAte = prazoAte;
    }
    
    
    
}
