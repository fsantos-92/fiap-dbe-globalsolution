package br.com.fiap.globalsolution.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Telefone;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.TelefoneService;

@RestController
@RequestMapping("/api/telefone")
public class TelefoneController {
    @Autowired
    TelefoneService service;

    @Autowired
    MotoristaService motoristaService;
    
    @GetMapping
    public Page<Telefone> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Telefone> create(@RequestBody @Valid Telefone telefone){
        service.save(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefone);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Telefone> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Telefone> show(@PathVariable Long id){
        Optional<Telefone> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Telefone telefone = optional.get();
        return ResponseEntity.ok(telefone);
    }



    @PutMapping("{id}")
    public ResponseEntity<Telefone> update(@PathVariable Long id, @RequestBody @Valid Telefone newTelefone){
        Optional<Telefone> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var telefone = optional.get();
        BeanUtils.copyProperties(newTelefone, telefone, new String[]{"id"});
        service.save(telefone);
        return ResponseEntity.ok(telefone);
    }

    @GetMapping("motorista/{id}")
    public ResponseEntity<List<Telefone>> listByMotorista(@PathVariable Long id){
        Optional<Motorista> optional = motoristaService.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Motorista motorista = optional.get();
        List<Telefone> veiculos = service.findByMotorista(motorista);
        return ResponseEntity.ok(veiculos);
    }
}
