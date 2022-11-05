package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Telefone;
import br.com.fiap.globalsolution.repository.TelefoneRepository;

@Service
public class TelefoneService {
    @Autowired
    TelefoneRepository repository;

    public Page<Telefone> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Telefone> listAll() {
        return repository.findAll();
    }

    public void save(Telefone telefone) {
        repository.save(telefone);
    }

    public Optional<Telefone> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Telefone> findByMotorista(Motorista motorista) {
        return repository.findByMotorista(motorista);
    }
}
