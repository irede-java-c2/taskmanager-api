package br.org.irede.taskmanagerapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private boolean concluida = false;
    
    private LocalDate prazo;
    
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade = Prioridade.MEDIA;
    
    //receber a chave estrangeira de usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToMany
    @JoinTable(name = "tarefa_etiqueta",
        joinColumns = @JoinColumn(name = "id_tarefa"),
        inverseJoinColumns = @JoinColumn(name = "id_etiqueta"))
    private Set<Etiqueta> etiquetas = new HashSet();
    
    public Tarefa(){
    }

    public Tarefa(String titulo, String descricao, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }
    
    public void adicionarEtiqueta(Etiqueta etiqueta){
        etiquetas.add(etiqueta);
        etiqueta.getTarefas().add(this);
    }
    
    public void removerEtiqueta(Etiqueta etiqueta){
        etiquetas.remove(etiqueta);
        etiqueta.getTarefas().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", titulo=" + titulo 
                + ", descricao=" + descricao + ", concluida=" + concluida 
                + ", prazo=" + prazo + ", prioridade=" + prioridade + '}';
    }
    
}
