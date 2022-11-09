package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.repository.MotoristaRepository;

@Service
public class MotoristaService {
    @Autowired
    MotoristaRepository repository;

    public Page<Motorista> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Motorista> listAll() {
        return repository.findAll();
    }

    public void save(Motorista motorista) {
        repository.save(motorista);
    }

    public Optional<Motorista> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Motorista findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
