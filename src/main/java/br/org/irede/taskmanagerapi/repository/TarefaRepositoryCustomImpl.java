package br.org.irede.taskmanagerapi.repository;

import br.org.irede.taskmanagerapi.filtro.TarefaFiltro;
import br.org.irede.taskmanagerapi.model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class TarefaRepositoryCustomImpl implements TarefaRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Tarefa> buscarComFiltros(TarefaFiltro filtro, Pageable pageable) {
        
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        
        //Consulta de dados da pagina
        CriteriaQuery<Tarefa> consulta = cb.createQuery(Tarefa.class);
        Root<Tarefa> tarefa = consulta.from(Tarefa.class);
        consulta.select(tarefa);
        consulta.where(montarCondicoes(cb, tarefa, filtro));
        consulta.orderBy(montarOrdenacao(cb, tarefa, pageable.getSort()));
        
        TypedQuery<Tarefa> query = entityManager.createQuery(consulta);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Tarefa> conteudo = query.getResultList();

        // 2) Contagem: mesmos filtros, sem ordenação e sem paginação
        CriteriaQuery<Long> contagem = cb.createQuery(Long.class);
        Root<Tarefa> tarefaContada = contagem.from(Tarefa.class);
        contagem.select(cb.count(tarefaContada));
        contagem.where(montarCondicoes(cb, tarefaContada, filtro));
        Long total = entityManager.createQuery(contagem).getSingleResult();

        return new PageImpl<>(conteudo, pageable, total);
        
    }

    private Predicate[] montarCondicoes(CriteriaBuilder cb, Root<Tarefa> tarefa, TarefaFiltro filtro) {
        List<Predicate> condicoes = new ArrayList<>();
        
        if(filtro.getPrioridade() != null){
            condicoes.add(cb.equal(tarefa.get("prioridade"), filtro.getPrioridade()));
        }
        
        if(filtro.getUsuarioId() != null){
            condicoes.add(cb.equal(tarefa.get("usuario").get("id"), filtro.getUsuarioId()));
        }
        
        return condicoes.toArray(new Predicate[0]);
    }

    private List<Order> montarOrdenacao(CriteriaBuilder cb, Root<Tarefa> tarefa, Sort sort) {
        List<Order> ordens = new ArrayList<>();
        for (Sort.Order ordem : sort) {
            if (ordem.isAscending()) {
                ordens.add(cb.asc(tarefa.get(ordem.getProperty())));
            } else {
                ordens.add(cb.desc(tarefa.get(ordem.getProperty())));
            }
        }
        // Sem ordenação pedida, ordena por id para a página ser sempre a mesma
        if (ordens.isEmpty()) {
            ordens.add(cb.asc(tarefa.get("id")));
        }
        return ordens;
    }
    
}
