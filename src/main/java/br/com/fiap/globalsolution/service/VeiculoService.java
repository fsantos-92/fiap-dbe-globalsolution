package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Veiculo;
import br.com.fiap.globalsolution.repository.VeiculoRepository;

@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository repository;

    public Page<Veiculo> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Veiculo> listAll() {
        return repository.findAll();
    }

    public void save(Veiculo veiculo) {
        repository.save(veiculo);
    }

    public Optional<Veiculo> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
