package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
    
}
