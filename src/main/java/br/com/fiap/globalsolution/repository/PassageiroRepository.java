package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long>{

    Passageiro findByEmail(String email);
    
}
