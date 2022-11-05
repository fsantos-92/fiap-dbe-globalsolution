package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.repository.PassageiroRepository;

@Service
public class PassageiroService {
    @Autowired
    PassageiroRepository repository;

    public Page<Passageiro> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Passageiro> listAll() {
        return repository.findAll();
    }

    public void save(Passageiro passageiro) {
        repository.save(passageiro);
    }

    public Optional<Passageiro> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
