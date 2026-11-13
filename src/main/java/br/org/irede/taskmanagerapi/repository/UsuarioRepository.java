package br.org.irede.taskmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.irede.taskmanagerapi.model.Usuario;
        
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
