package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
    
}
