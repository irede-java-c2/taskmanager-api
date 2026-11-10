package br.org.irede.taskmanagerapi.repository;

import br.org.irede.taskmanagerapi.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository  extends JpaRepository<Etiqueta, Long>{
    
}
