package br.com.fiap.globalsolution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.Corrida;
import br.com.fiap.globalsolution.model.Motorista;

public interface CorridaRepository extends JpaRepository<Corrida, Long>{

    List<Corrida> findByMotorista(Motorista motorista);

    List<Corrida> findByPassageiro(Passageiro passageiro);
    
}
