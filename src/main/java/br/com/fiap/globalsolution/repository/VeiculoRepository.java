package br.com.fiap.globalsolution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
   
    List<Veiculo> findByMotorista(Motorista motorista);
}
