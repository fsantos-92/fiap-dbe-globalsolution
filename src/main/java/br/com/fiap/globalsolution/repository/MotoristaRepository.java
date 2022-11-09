package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long>{
    public Motorista findByEmail(String email);
}
