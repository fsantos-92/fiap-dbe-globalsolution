package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Corrida;

public interface CorridaRepository extends JpaRepository<Corrida, Long>{
    
}
