package br.com.fiap.globalsolution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
    List<Telefone> findByMotorista(Motorista motorista);
}
