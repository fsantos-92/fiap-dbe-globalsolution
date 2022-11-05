package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Cliente;
import br.com.fiap.globalsolution.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public Page<Cliente> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Cliente> listAll() {
        return repository.findAll();
    }

    public void save(Cliente cliente) {
        repository.save(cliente);
    }

    public Optional<Cliente> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
