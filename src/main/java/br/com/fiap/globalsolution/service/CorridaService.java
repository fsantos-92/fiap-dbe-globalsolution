package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Corrida;
import br.com.fiap.globalsolution.repository.CorridaRepository;

@Service
public class CorridaService {
    @Autowired
    CorridaRepository repository;

    public Page<Corrida> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Corrida> listAll() {
        return repository.findAll();
    }

    public void save(Corrida corrida) {
        repository.save(corrida);
    }

    public Optional<Corrida> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
